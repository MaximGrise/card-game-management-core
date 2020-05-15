package com.maxim.gamemanagement.core.controller.game;

import com.maxim.gamemanagement.core.controller.ControllerError;
import org.springframework.http.HttpStatus;

public enum GameControllerError implements ControllerError {

  GAME_ALREADY_EXISTS("game-1", "Game already exists.", HttpStatus.CONFLICT),
  GAME_NOT_FOUND("game-2", "Game not found.", HttpStatus.NOT_FOUND);

  private final String code;

  private final String description;

  private final HttpStatus status;

  GameControllerError(String code, String description,
      HttpStatus status) {
    this.code = code;
    this.description = description;
    this.status = status;
  }

  public String getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public HttpStatus getStatus() {
    return status;
  }
}