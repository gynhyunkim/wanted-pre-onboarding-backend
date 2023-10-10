package com.wanted.assignment;

import com.wanted.assignment.repository.JobPostingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JobPostingRepositoryTest {
    @Autowired
    JobPostingRepository jobPostingRepository;

    @Test
    public void testInsertDummies() {

    }
}
