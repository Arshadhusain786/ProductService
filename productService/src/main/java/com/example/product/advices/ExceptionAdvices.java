package com.example.product.advices;

import com.example.product.dtos.ErrorResponseDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestController
public class ExceptionAdvices {

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseDto handleRuntimeException(RuntimeException e) {
        ErrorResponseDto dto = new ErrorResponseDto();
        dto.setMessage(e.getMessage());
        dto.setStatus("ERROR");
        return dto;
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponseDto handleException(Exception e) {
        ErrorResponseDto dto = new ErrorResponseDto();
        dto.setMessage(e.getMessage());
        dto.setStatus("ERROR");
        return dto;
    }
}
