package com.wanted.assignment.controller.reqeust;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApplyReq {
    @NotBlank(message = "사용자 정보를 포함해야 합니다.")
    private Long userId;
    @NotBlank(message = "채용 공고 정보를 포함해야 합니다.")
    private Long postId;
}
