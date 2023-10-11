package com.wanted.assignment.repository;

import com.wanted.assignment.domain.dto.CompanyDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyDto, Long> {
}
