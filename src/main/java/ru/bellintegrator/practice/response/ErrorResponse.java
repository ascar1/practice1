package ru.bellintegrator.practice.response;

public class ErrorResponse {
  public ErrorResponse() {

  }

  public ErrorResponse(String errMsg) {
    this.error = errMsg;
  }

  public String error;
}
