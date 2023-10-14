package com.wanted.assignment.controller;

import com.wanted.assignment.common.ApiResponse;
import com.wanted.assignment.common.ApiUtils;
import com.wanted.assignment.controller.reqeust.JobPostingCreateReq;
import com.wanted.assignment.controller.reqeust.JobPostingUpdateReq;
import com.wanted.assignment.domain.dto.JobPostingDetailRes;
import com.wanted.assignment.domain.dto.JobPostingsRes;
import com.wanted.assignment.domain.entity.Company;
import com.wanted.assignment.domain.entity.JobPosting;
import com.wanted.assignment.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/posting")
public class JobPostingController {
    private final JobPostingService jobPostingService;
    @PostMapping(value = "/create")
    public ApiResponse<Long> create(@Validated @RequestBody JobPostingCreateReq req) throws Exception {
        JobPosting result = jobPostingService.create(req);
        return ApiUtils.createSuccessWithDataResponse(result.getId());
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
    public ApiResponse<List<JobPostingsRes>> getPostings(@RequestParam int pageNo) throws Exception {
        List<JobPosting> result =  jobPostingService.getAllPostings(pageNo);
        return getListApiResponse(result);
    }

    @GetMapping("/search")
    public ApiResponse<List<JobPostingsRes>> searchPosting(@RequestParam int pageNo, @RequestParam String keyword) throws Exception {
        List<JobPosting> result =  jobPostingService.searchPosting(keyword, pageNo);
        return getListApiResponse(result);
    }

    private ApiResponse<List<JobPostingsRes>> getListApiResponse(List<JobPosting> result) {
        List<JobPostingsRes> postings = new LinkedList<>();

        for (JobPosting posting : result) {
            Company company = posting.getCompany();
            postings.add(JobPostingsRes.builder()
                    .id(posting.getId())
                    .reward(posting.getReward())
                    .position(posting.getPosition())
                    .skillSet(posting.getSkillSet())
                    .companyName(company.getName())
                    .country(company.getCountry())
                    .region(company.getRegion())
                    .build());
        }
        return ApiUtils.createSuccessWithDataResponse(postings);
    }

    @GetMapping("/{postId}")
    public ApiResponse<JobPostingDetailRes> getPostingDetail(@PathVariable Long postId) throws Exception {
        JobPosting result = jobPostingService.getDetails(postId);
        Company company = result.getCompany();
        return ApiUtils.createSuccessWithDataResponse(JobPostingDetailRes.builder()
                        .id(postId)
                        .reward(result.getReward())
                        .skillSet(result.getSkillSet())
                        .position(result.getPosition())
                        .description(result.getDescription())
                        .companyName(company.getName())
                        .country(company.getCountry())
                        .region(company.getRegion())
                        .anotherPosting(company.getJobPostings().stream().map(JobPosting::getId).collect(Collectors.toList()))
                .build());
    }
}
