package ru.bellintegrator.practice.response;

public class ErrorResponse {
    public ErrorResponse (){

    }
    public ErrorResponse(String ErrMsg){
        this.error = ErrMsg;
    }
    public String error;
}
