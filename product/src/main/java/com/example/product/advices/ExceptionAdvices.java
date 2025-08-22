package com.example.product.advices;

import com.example.product.dtos.ErrorResponseDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices
{
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseDto handleRuntimeException(RuntimeException e)
    {
        ErrorResponseDto dto = new ErrorResponseDto();
        dto.setMessage(e.getMessage());
        dto.setStatus("ERROR");
        return dto;
    }
    // if we found any exception in this controller this method gets called
    @ExceptionHandler(Exception.class)
    public ErrorResponseDto handleException(Exception e)
    {
        ErrorResponseDto dto = new ErrorResponseDto();
        dto.setMessage(e.getMessage());
        dto.setStatus("ERROR");
        return dto;
    }
}
