package com.wanted.assignment.controller;

import com.wanted.assignment.common.BasicResponse;
import com.wanted.assignment.common.ApiUtils;
import com.wanted.assignment.controller.reqeust.ApplyReq;
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
    public BasicResponse<Long> create(@Validated @RequestBody JobPostingCreateReq req) throws Exception {
        JobPosting result = jobPostingService.create(req);
        return ApiUtils.successWithDataResponse(result.getId());
    }

    @PutMapping("/{postId}")
    public BasicResponse<Void> update(@PathVariable Long postId, @Validated @RequestBody JobPostingUpdateReq req) throws Exception {
        jobPostingService.update(postId, req);
        return ApiUtils.successWithEmptyResponse();
    }

    @DeleteMapping("/{postId}")
    public BasicResponse<Void> delete(@PathVariable Long postId) throws Exception {
        jobPostingService.delete(postId);
        return ApiUtils.successWithEmptyResponse();
    }

    @GetMapping("")
    public BasicResponse<List<JobPostingsRes>> getPostings(@RequestParam int pageNo) throws Exception {
        List<JobPosting> result =  jobPostingService.getAllPostings(pageNo);
        return getListApiResponse(result);
    }

    @GetMapping("/search")
    public BasicResponse<List<JobPostingsRes>> searchPosting(@RequestParam int pageNo, @RequestParam String keyword) throws Exception {
        List<JobPosting> result =  jobPostingService.searchPosting(keyword, pageNo);
        return getListApiResponse(result);
    }

    private BasicResponse<List<JobPostingsRes>> getListApiResponse(List<JobPosting> result) {
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
        return ApiUtils.successWithDataResponse(postings);
    }

    @GetMapping("/{postId}")
    public BasicResponse<JobPostingDetailRes> getPostingDetail(@PathVariable Long postId) throws Exception {
        JobPosting result = jobPostingService.getDetails(postId);
        Company company = result.getCompany();
        return ApiUtils.successWithDataResponse(JobPostingDetailRes.builder()
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

    @PostMapping("/apply")
    public BasicResponse<Long> apply(@Validated @RequestBody ApplyReq req) throws Exception {
        return ApiUtils.successWithDataResponse(jobPostingService.apply(req));
    }
}
