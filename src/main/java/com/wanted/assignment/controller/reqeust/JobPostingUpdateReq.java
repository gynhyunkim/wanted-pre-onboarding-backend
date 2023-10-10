package com.wanted.assignment.controller.reqeust;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JobPostingUpdateReq {
    private String position;
    private Integer reward;
    private String description;
    private String skillSet;
}
