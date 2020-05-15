package com.maxim.gamemanagement.core.controller;

import org.springframework.http.HttpStatus;

/**
 * Generic controller error to be passed to ControllerException. Should be implemented by errors
 * Enum.
 */
public interface ControllerError {

  String getCode();

  String getDescription();

  HttpStatus getStatus();
}