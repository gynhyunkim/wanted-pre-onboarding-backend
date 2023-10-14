package com.wanted.assignment.service;

import com.wanted.assignment.domain.entity.Company;
import com.wanted.assignment.domain.entity.JobPosting;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class JobPostingSpecification {
    public static Specification<JobPosting> searchPosting(Map<String, String> searchKey){
        return ((root, query, criteriaBuilder) -> {
            Join<JobPosting, Company> postingCompany = root.join("company");
            List<Predicate> predicates = new ArrayList<>();
            for(String key : searchKey.keySet()){
                if (key != "name") {
                    predicates.add(criteriaBuilder.like(root.get(key), "%" + searchKey.get(key) + "%"));
                } else {
                    predicates.add(criteriaBuilder.like(postingCompany.get(key), "%" + searchKey.get(key) + "%"));
                }
            }
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        });
    }
}
