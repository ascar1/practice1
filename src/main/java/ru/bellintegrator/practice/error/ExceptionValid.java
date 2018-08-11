package ru.bellintegrator.practice.error;

import ru.bellintegrator.practice.response.ErrorResponse;

import java.util.ArrayList;
import java.util.List;

public class ExceptionValid extends Exception {
    public ExceptionValid(){
        this.errors = new ArrayList<ErrorResponse>();
    }

    public List<ErrorResponse> errors;

    public void addError(String ErrMsg){
        this.errors.add(new ErrorResponse(ErrMsg));
    }
}
