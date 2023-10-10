package com.wanted.assignment.controller;

import com.wanted.assignment.common.ApiResponse;
import com.wanted.assignment.common.ApiUtils;
import com.wanted.assignment.controller.reqeust.JobPostingCreateReq;
import com.wanted.assignment.controller.reqeust.JobPostingUpdateReq;
import com.wanted.assignment.domain.entity.JobPosting;
import com.wanted.assignment.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/posting")
public class JobPostingController {
    private final JobPostingService jobPostingService;

    @PostMapping(value = "/create")
    public ApiResponse<JobPosting> create(@RequestBody JobPostingCreateReq req) throws Exception {
        if (req.getReward() < 0) {
            throw new IllegalArgumentException("reward는 1이상의 숫자여야합니다.");
        }
        return ApiUtils.createSuccessWithDataResponse(jobPostingService.create(req));
    }

    @PutMapping("/{postId}")
    public ApiResponse<Void> update(@PathVariable Long postId, @RequestBody JobPostingUpdateReq req) throws Exception {
        if (req.getReward() < 0) {
            throw new IllegalArgumentException("reward는 1이상의 숫자여야합니다.");
        }
        jobPostingService.update(postId, req);
        return ApiUtils.successCreateWithEmptyResponse();
    }

    @DeleteMapping("/{postId}")
    public ApiResponse<Void> delete(@PathVariable Long postId) throws Exception {
        jobPostingService.delete(postId);
        return ApiUtils.successCreateWithEmptyResponse();
    }
}
