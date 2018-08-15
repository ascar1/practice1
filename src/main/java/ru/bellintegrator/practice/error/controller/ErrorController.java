package ru.bellintegrator.practice.error.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.error.ExceptionValid;
import ru.bellintegrator.practice.response.ErrorResponse;

import java.util.List;

@RestControllerAdvice
public class ErrorController {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExceptionValid.class)
    public List<ErrorResponse> handleExeptionValid (ExceptionValid ex){
        return ex.errors;
    }

}
