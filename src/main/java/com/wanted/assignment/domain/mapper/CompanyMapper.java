package com.wanted.assignment.domain.mapper;

import com.wanted.assignment.domain.dto.CompanyDto;
import com.wanted.assignment.domain.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public Company dtoToEntity(CompanyDto companyDto) {
        return Company.builder()
                .id(companyDto.getId())
                .country(companyDto.getCountry())
                .region(companyDto.getRegion())
                .name(companyDto.getName())
                .build();
    }

    public CompanyDto entityToDto(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .country(company.getCountry())
                .name(company.getName())
                .region(company.getRegion())
                .build();
    }
}
