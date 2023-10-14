package com.wanted.assignment.common;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class ApiUtils {

    public static <T> BasicResponse<T> successWithDataResponse(T t) {
        return BasicResponse.<T>builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .data(t)
                .build();
    }

    public static BasicResponse<Void> successWithEmptyResponse() {
        return BasicResponse.<Void>builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .build();
    }
}
