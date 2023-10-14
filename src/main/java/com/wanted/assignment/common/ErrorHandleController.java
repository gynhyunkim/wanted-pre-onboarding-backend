package com.wanted.assignment.common;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class ErrorHandleController {

    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BasicResponse<String> handleBadRequestException(Exception e) {
        return BasicResponse.<String>builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .success(false)
                .error(new BasicResponse.ApiError(HttpStatus.BAD_REQUEST.name(), e.getMessage()))
                .build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BasicResponse<String> handleNotFoundException(Exception e) {
        return BasicResponse.<String>builder()
                .code(HttpStatus.NOT_FOUND.value())
                .success(false)
                .error(new BasicResponse.ApiError(HttpStatus.NOT_FOUND.name(), e.getMessage()))
                .build();
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BasicResponse<String> handleInternalServerError(Exception e) {
        return BasicResponse.<String>builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .success(false)
                .error(new BasicResponse.ApiError(HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage()))
                .build();
    }
}
