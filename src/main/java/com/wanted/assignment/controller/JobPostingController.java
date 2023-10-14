package com.wanted.assignment.controller;

import com.wanted.assignment.common.ApiResponse;
import com.wanted.assignment.common.ApiUtils;
import com.wanted.assignment.controller.reqeust.JobPostingCreateReq;
import com.wanted.assignment.controller.reqeust.JobPostingUpdateReq;
import com.wanted.assignment.domain.dto.JobPostingDto;
import com.wanted.assignment.domain.entity.JobPosting;
import com.wanted.assignment.domain.mapper.CompanyMapper;
import com.wanted.assignment.domain.mapper.JobPostingMapper;
import com.wanted.assignment.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/posting")
public class JobPostingController {
    private final JobPostingService jobPostingService;
    private final JobPostingMapper jobPostingMapper;
    private final CompanyMapper companyMapper;
    @PostMapping(value = "/create")
    public ApiResponse<JobPostingDto> create(@Validated @RequestBody JobPostingCreateReq req) throws Exception {
        JobPosting result = jobPostingService.create(req);
        return ApiUtils.createSuccessWithDataResponse(jobPostingMapper.entityToDto(result,
                companyMapper.entityToDto(result.getCompany())));
    }

    @PutMapping("/{postId}")
    public ApiResponse<Void> update(@PathVariable Long postId, @Validated @RequestBody JobPostingUpdateReq req) throws Exception {
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
        List<JobPosting> result =  jobPostingService.getAllPostings(pageNo);
        List<JobPostingDto> postings = new LinkedList<>();
        for (JobPosting posting : result) {
            postings.add(jobPostingMapper.entityToDto(posting,
                    companyMapper.entityToDto(posting.getCompany())));
        }
        return ApiUtils.createSuccessWithDataResponse(postings);
    }
}
