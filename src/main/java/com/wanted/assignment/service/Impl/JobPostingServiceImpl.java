package com.wanted.assignment.service.Impl;

import com.wanted.assignment.controller.reqeust.ApplyReq;
import com.wanted.assignment.controller.reqeust.JobPostingCreateReq;
import com.wanted.assignment.controller.reqeust.JobPostingUpdateReq;
import com.wanted.assignment.domain.entity.ApplicationHistory;
import com.wanted.assignment.domain.entity.Company;
import com.wanted.assignment.domain.entity.JobPosting;
import com.wanted.assignment.domain.entity.User;
import com.wanted.assignment.repository.ApplicationHistoryRepository;
import com.wanted.assignment.repository.CompanyRepository;
import com.wanted.assignment.repository.JobPostingRepository;
import com.wanted.assignment.repository.UserRepository;
import com.wanted.assignment.service.JobPostingService;
import com.wanted.assignment.service.JobPostingSpecification;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobPostingServiceImpl implements JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final ApplicationHistoryRepository applicationHistoryRepository;
    private final Integer PAGE_SIZE = 30;

    @Override
    public JobPosting create(JobPostingCreateReq req) throws Exception {
        Company company = companyRepository.findById(req.getCompanyId())
                .orElseThrow(() -> new Exception("Invalid company Id"));
        return jobPostingRepository.save(JobPosting.builder()
                .company(company)
                .position(req.getPosition())
                .reward(req.getReward())
                .description(req.getDescription())
                .skillSet(req.getSkillSet())
                .build());
    }

    @Override
    @Transactional
    public void update(Long id, JobPostingUpdateReq req) throws Exception {
        JobPosting posting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 채용공고입니다."));
        posting.setDescription(req.getDescription());
        posting.setPosition(req.getPosition());
        posting.setReward(req.getReward());
        posting.setSkillSet(req.getSkillSet());
    }

    @Override
    public void delete(Long id) throws Exception {
        JobPosting posting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 채용공고입니다."));
        jobPostingRepository.delete(posting);
    }

    @Override
    public List<JobPosting> getAllPostings(int pageNo) throws Exception {
        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE);
        Page<JobPosting> postings = jobPostingRepository.findAll(pageable);
        return postings.getContent();
    }

    @Override
    public List<JobPosting> searchPosting(String keyword, int pageNo) throws Exception {
        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE);
        Map<String, String> keys = new HashMap<>();
        keys.put("name", keyword);
        keys.put("position", keyword);
        keys.put("skillSet", keyword);
        Page<JobPosting> postings = jobPostingRepository.findAll(JobPostingSpecification.searchPosting(keys),
                pageable);
        return postings.getContent();
    }

    @Override
    public JobPosting getDetails(Long id) throws Exception {
        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용공고입니다."));
        return jobPosting;
    }

    @Override
    @Transactional
    public Long apply(ApplyReq req) throws Exception {
        JobPosting jobPosting = jobPostingRepository.findById(req.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용공고입니다."));
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        List<ApplicationHistory> applicationHistory = applicationHistoryRepository.findByUserAndPosting(user, jobPosting);
        if (!applicationHistory.isEmpty()) {
            throw new IllegalStateException("한 공고에는 한 번만 지원할 수 있습니다.");
        }
        return applicationHistoryRepository.save(ApplicationHistory.builder()
                .posting(jobPosting)
                .user(user)
                .build()).getId();
    }
}
