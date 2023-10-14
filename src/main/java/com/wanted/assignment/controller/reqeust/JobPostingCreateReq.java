package com.wanted.assignment.controller.reqeust;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JobPostingCreateReq {
    @NotBlank(message = "회사 정보를 포함해야 합니다.")
    private Long companyId;
    @NotBlank(message = "채용 포지션을 포함해야 합니다.")
    private String position;
    @NotBlank(message = "보상 금액을 포함해야 합니다. 보상 금액이 없는 경우 0을 입력해주세요.")
    @PositiveOrZero(message = "보상 금액이 올바르지 않습니다.")
    private Integer reward;
    @NotBlank(message = "채용 상세 내용을 포함해야 합니다.")
    private String description;
    @NotBlank(message = "기술 스택을 포함해야 합니다.")
    private String skillSet;
}
