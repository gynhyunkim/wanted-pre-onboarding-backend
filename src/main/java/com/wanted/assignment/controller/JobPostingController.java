package com.wanted.assignment.controller;

import com.wanted.assignment.common.ApiResponse;
import com.wanted.assignment.common.ApiUtils;
import com.wanted.assignment.controller.reqeust.JobPostingCreateReq;
import com.wanted.assignment.controller.reqeust.JobPostingUpdateReq;
import com.wanted.assignment.domain.dto.JobPostingDto;
import com.wanted.assignment.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/posting")
public class JobPostingController {
    private final JobPostingService jobPostingService;

    @PostMapping(value = "/create")
    public ApiResponse<JobPostingDto> create(@RequestBody JobPostingCreateReq req) throws Exception {
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

    @GetMapping("")
    public ApiResponse<List<JobPostingDto>> getPostings(@RequestParam int pageNo) throws Exception {
        List<JobPostingDto> postings =  jobPostingService.getAllPostings(pageNo);
        return ApiUtils.createSuccessWithDataResponse(postings);
    }
}
