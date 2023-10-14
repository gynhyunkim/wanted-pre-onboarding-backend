package com.wanted.assignment.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobPostingDto {
    private Long id;
    private String position;
    private Integer reward;
    private String skillSet;
    private String description;
    private CompanyDto company;
    private String companyName;
    private String country;
    private String region;
}

