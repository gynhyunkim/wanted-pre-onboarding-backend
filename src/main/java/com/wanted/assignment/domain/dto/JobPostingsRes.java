package com.wanted.assignment.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JobPostingsRes {
    private Long id;
    private String position;
    private Integer reward;
    private String skillSet;
    private String companyName;
    private String country;
    private String region;
}

