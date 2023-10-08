package com.wanted.assignment.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="job-postings")
public class JobPosting {
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
    private Company company;
}
