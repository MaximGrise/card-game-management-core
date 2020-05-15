package com.maxim.gamemanagement.core.controller;

import java.io.Serializable;
import org.springframework.http.HttpStatus;

public class ControllerException extends RuntimeException implements
    Serializable {

  private final String code;
  private final String description;
  private final HttpStatus httpStatus;

  public ControllerException(ControllerError error, Object... args) {
    super("Exception explicitly thrown by controller: " + error.getCode());

    this.code = error.getCode();
    this.httpStatus = error.getStatus();

    if (args != null && args.length > 0) {
      this.description = String.format(error.getDescription(), args);
    } else {
      this.description = error.getDescription();
    }
  }

  public ControllerException(String error, HttpStatus httpStatus,
      Object... args) {
    super("Exception explicitly thrown by controller: " + error);

    this.code = String.valueOf(httpStatus.value());
    this.httpStatus = httpStatus;

    this.description = error;
  }

  public String getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  @Override
  public String toString() {
    return "ControllerException{" +
        "code='" + code + '\'' +
        ", description='" + description + '\'' +
        ", httpStatus=" + httpStatus +
        '}';
  }
}