package com.wanted.assignment.service;

import com.wanted.assignment.controller.reqeust.JobPostingCreateReq;
import com.wanted.assignment.controller.reqeust.JobPostingUpdateReq;
import com.wanted.assignment.domain.dto.JobPostingDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface JobPostingService {
    JobPostingDto create(JobPostingCreateReq req) throws Exception;
    @Transactional
    void update(Long id, JobPostingUpdateReq req) throws Exception;
    @Transactional
    void delete(Long id) throws Exception;
    List<JobPostingDto> getAllPostings(int pageNo) throws Exception;
}
