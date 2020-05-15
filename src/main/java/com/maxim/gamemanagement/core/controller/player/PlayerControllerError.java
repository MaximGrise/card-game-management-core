package com.maxim.gamemanagement.core.controller.player;

import com.maxim.gamemanagement.core.controller.ControllerError;
import org.springframework.http.HttpStatus;

public enum PlayerControllerError implements ControllerError {

  PLAYER_ALREADY_EXISTS("player-1", "Player already exists with that name.", HttpStatus.CONFLICT),
  PLAYER_NOT_FOUND("player-2", "Player not found.", HttpStatus.NOT_FOUND);

  private final String code;

  private final String description;

  private final HttpStatus status;

  PlayerControllerError(String code, String description,
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