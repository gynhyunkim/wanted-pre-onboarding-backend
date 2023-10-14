package com.wanted.assignment.repository;

import com.wanted.assignment.domain.entity.ApplicationHistory;
import com.wanted.assignment.domain.entity.JobPosting;
import com.wanted.assignment.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationHistoryRepository extends JpaRepository<ApplicationHistory, Long> {
    List<ApplicationHistory> findByUserAndPosting(User user, JobPosting posting);
}
