package com.wanted.assignment.service;

import com.wanted.assignment.controller.reqeust.JobPostingCreateReq;
import com.wanted.assignment.domain.entity.JobPosting;

public interface JobPostingService {
    JobPosting createJobPosting(JobPostingCreateReq req) throws Exception;
}
