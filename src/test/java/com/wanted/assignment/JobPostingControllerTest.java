package com.wanted.assignment;

import com.google.gson.Gson;
import com.wanted.assignment.common.ApiResponse;
import com.wanted.assignment.common.ApiUtils;
import com.wanted.assignment.controller.JobPostingController;
import com.wanted.assignment.controller.reqeust.JobPostingCreateReq;
import com.wanted.assignment.domain.entity.Company;
import com.wanted.assignment.domain.entity.JobPosting;
import com.wanted.assignment.domain.mapper.CompanyMapper;
import com.wanted.assignment.domain.mapper.JobPostingMapper;
import com.wanted.assignment.service.JobPostingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class JobPostingControllerTest {

    @InjectMocks
    private JobPostingController jobPostingController;
    @Mock
    private JobPostingService jobPostingService;
    @Mock
    private JobPostingMapper jobPostingMapper;
    @Mock
    private CompanyMapper companyMapper;
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(jobPostingController).build();
    }

    @DisplayName("채용 공고 등록 성공")
    @Test
    void createPostingSuccess() throws Exception {
        JobPostingCreateReq req = JobPostingCreateReq.builder()
                .companyId(Long.valueOf(1))
                .description("test")
                .position("test")
                .skillSet("test")
                .reward(1000)
                .build();
        JobPosting response = JobPosting.builder()
                .position("test")
                .reward(1000)
                .skillSet("test")
                .description("test")
                .company(Company.builder()
                        .id(Long.valueOf(1))
                        .name("test")
                        .build()).build();
        doReturn(response).when(jobPostingService)
                .create(any(JobPostingCreateReq.class));
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/posting/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(req))
        );
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }

}
