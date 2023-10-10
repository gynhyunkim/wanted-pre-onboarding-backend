package com.wanted.assignment.service;

import com.wanted.assignment.controller.reqeust.JobPostingCreateReq;
import com.wanted.assignment.controller.reqeust.JobPostingUpdateReq;
import com.wanted.assignment.domain.entity.JobPosting;
import jakarta.transaction.Transactional;

public interface JobPostingService {
    JobPosting create(JobPostingCreateReq req) throws Exception;
    @Transactional
    void update(Long id, JobPostingUpdateReq req) throws Exception;
}
