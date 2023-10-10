package com.wanted.assignment.service.Impl;

import com.wanted.assignment.controller.reqeust.JobPostingCreateReq;
import com.wanted.assignment.controller.reqeust.JobPostingUpdateReq;
import com.wanted.assignment.domain.entity.Company;
import com.wanted.assignment.domain.entity.JobPosting;
import com.wanted.assignment.repository.CompanyRepository;
import com.wanted.assignment.repository.JobPostingRepository;
import com.wanted.assignment.service.JobPostingService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobPostingServiceImpl implements JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;

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
}
