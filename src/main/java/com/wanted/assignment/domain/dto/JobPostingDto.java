package com.wanted.assignment.domain.dto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="job-postings")
public class JobPostingDto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "position")
    private String position;
    @Column(name = "reward")
    private Integer reward;
    @Column(name = "skill_set")
    private String skillSet;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyDto companyDto;
}
