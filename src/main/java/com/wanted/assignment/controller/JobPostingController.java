package com.wanted.assignment.controller;

import com.wanted.assignment.controller.reqeust.JobPostingCreateReq;
import com.wanted.assignment.domain.entity.JobPosting;
import com.wanted.assignment.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/posting")
public class JobPostingController {
    private final JobPostingService jobPostingService;

    @PostMapping(value = "/create")
    public ResponseEntity<JobPosting> create(@RequestBody JobPostingCreateReq req) throws Exception {
        if (req.getReward() < 0) {
        }
        return ResponseEntity.status(HttpStatus.OK).body(jobPostingService.createJobPosting(req));
    }
}
