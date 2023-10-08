package com.wanted.assignment.service.Impl;

import com.wanted.assignment.controller.reqeust.JobPostingCreateReq;
import com.wanted.assignment.domain.entity.Company;
import com.wanted.assignment.domain.entity.JobPosting;
import com.wanted.assignment.repository.CompanyRepository;
import com.wanted.assignment.repository.JobPostingRepository;
import com.wanted.assignment.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobPostingServiceImpl implements JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;

    @Override
    public JobPosting createJobPosting(JobPostingCreateReq req) throws Exception {
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
}
