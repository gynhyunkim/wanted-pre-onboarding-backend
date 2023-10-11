package com.wanted.assignment.service.Impl;

import com.wanted.assignment.controller.reqeust.JobPostingCreateReq;
import com.wanted.assignment.controller.reqeust.JobPostingUpdateReq;
import com.wanted.assignment.domain.dto.CompanyDto;
import com.wanted.assignment.domain.dto.JobPostingDto;
import com.wanted.assignment.repository.CompanyRepository;
import com.wanted.assignment.repository.JobPostingRepository;
import com.wanted.assignment.service.JobPostingService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobPostingServiceImpl implements JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;
    private final Integer PAGE_SIZE = 30;

    @Override
    public JobPostingDto create(JobPostingCreateReq req) throws Exception {
        CompanyDto companyDto = companyRepository.findById(req.getCompanyId())
                .orElseThrow(() -> new Exception("Invalid company Id"));
        return jobPostingRepository.save(JobPostingDto.builder()
                .companyDto(companyDto)
                .position(req.getPosition())
                .reward(req.getReward())
                .description(req.getDescription())
                .skillSet(req.getSkillSet())
                .build());
    }

    @Override
    @Transactional
    public void update(Long id, JobPostingUpdateReq req) throws Exception {
        JobPostingDto posting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 채용공고입니다."));
        posting.setDescription(req.getDescription());
        posting.setPosition(req.getPosition());
        posting.setReward(req.getReward());
        posting.setSkillSet(req.getSkillSet());
    }

    @Override
    public void delete(Long id) throws Exception {
        JobPostingDto posting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 채용공고입니다."));
        jobPostingRepository.delete(posting);
    }

    @Override
    public List<JobPostingDto> getAllPostings(int pageNo) throws Exception {
        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE);
        Page<JobPostingDto> postings = jobPostingRepository.findAll(pageable);
        return postings.getContent();
    }
}
