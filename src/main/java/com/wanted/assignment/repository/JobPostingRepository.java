package com.wanted.assignment.repository;

import com.wanted.assignment.domain.dto.JobPostingDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostingRepository extends JpaRepository<JobPostingDto, Long> {
}
