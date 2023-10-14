package com.wanted.assignment.domain.mapper;

import com.wanted.assignment.domain.dto.CompanyDto;
import com.wanted.assignment.domain.dto.JobPostingDto;
import com.wanted.assignment.domain.entity.Company;
import com.wanted.assignment.domain.entity.JobPosting;
import org.springframework.stereotype.Component;

@Component
public class JobPostingMapper {
    public JobPosting dtoToEntity(JobPostingDto jobPostingDto, Company company) {
        return JobPosting.builder()
                .id(jobPostingDto.getId())
                .reward(jobPostingDto.getReward())
                .description(jobPostingDto.getDescription())
                .skillSet(jobPostingDto.getSkillSet())
                .company(company)
                .position(jobPostingDto.getPosition())
                .build();
    }

    public JobPostingDto entityToDto(JobPosting jobPosting, CompanyDto companyDto) {
        return JobPostingDto.builder()
                .id(jobPosting.getId())
                .company(companyDto)
                .position(jobPosting.getPosition())
                .description(jobPosting.getDescription())
                .reward(jobPosting.getReward())
                .skillSet(jobPosting.getSkillSet())
                .build();
    }
}
