package com.wanted.assignment.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyDto {
    private Long id;
    private String name;
    private String country;
    private String region;
}
