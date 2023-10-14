package com.wanted.assignment.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.wanted.assignment.domain.entity.JobPosting;
import lombok.Builder;
import lombok.Getter;
import java.util.List;
@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JobPostingDetailRes {
    private Long id;
    private String position;
    private Integer reward;
    private String skillSet;
    private String description;
    private String companyName;
    private String country;
    private String region;
    private List<Long> anotherPosting;
}
