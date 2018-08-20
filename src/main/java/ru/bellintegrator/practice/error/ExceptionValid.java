package ru.bellintegrator.practice.error;

import java.util.ArrayList;
import java.util.List;

import ru.bellintegrator.practice.response.ErrorResponse;

public class ExceptionValid extends Exception {
  public List<ErrorResponse> errors;

  public ExceptionValid() {
    this.errors = new ArrayList<>();
  }

  public void addError(String errMsg) {
    this.errors.add(new ErrorResponse(errMsg));
  }
}
