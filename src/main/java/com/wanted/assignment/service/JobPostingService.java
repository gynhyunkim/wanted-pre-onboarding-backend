package com.wanted.assignment.service;

import com.wanted.assignment.controller.reqeust.ApplyReq;
import com.wanted.assignment.controller.reqeust.JobPostingCreateReq;
import com.wanted.assignment.controller.reqeust.JobPostingUpdateReq;
import com.wanted.assignment.domain.entity.JobPosting;
import jakarta.transaction.Transactional;

import java.util.List;

public interface JobPostingService {
    JobPosting create(JobPostingCreateReq req) throws Exception;
    @Transactional
    void update(Long id, JobPostingUpdateReq req) throws Exception;
    @Transactional
    void delete(Long id) throws Exception;
    List<JobPosting> getAllPostings(int pageNo) throws Exception;
    List<JobPosting> searchPosting(String keyword, int pageNo) throws Exception;
    JobPosting getDetails(Long id) throws Exception;
    Long apply(ApplyReq req) throws Exception;
}
