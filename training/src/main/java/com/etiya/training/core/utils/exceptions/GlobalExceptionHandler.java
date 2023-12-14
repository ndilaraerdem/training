package com.etiya.training.core.utils.exceptions;

import com.etiya.training.core.utils.exceptions.types.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //exception handler methodlarda fırlayan exception 1. parametreye otromatik gönderilir
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String>  handleValidationException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors().stream().map(error -> {
            return error.getDefaultMessage();
        }).toList();
        return errors;
//        return "Validayon hatası yaptınız";
    }

    @ExceptionHandler({ BusinessException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBusinessException(BusinessException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler({ Exception.class })
    public String handleException() {
        return "Bilinmeyen hata";
    }
}
