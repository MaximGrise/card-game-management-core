package com.maxim.gamemanagement.core.controller.deck;

import com.maxim.gamemanagement.core.controller.ControllerError;
import org.springframework.http.HttpStatus;

public enum DeckControllerError implements ControllerError {

  DECK_ALREADY_EXISTS("deck-1", "Deck already exists.", HttpStatus.CONFLICT),
  DECK_NOT_FOUND("deck-2", "Deck not found.", HttpStatus.NOT_FOUND);

  private final String code;

  private final String description;

  private final HttpStatus status;

  DeckControllerError(String code, String description,
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