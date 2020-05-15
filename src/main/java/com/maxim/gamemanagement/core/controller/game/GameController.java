package com.maxim.gamemanagement.core.controller.game;

import com.maxim.gamemanagement.core.controller.ControllerException;
import com.maxim.gamemanagement.core.controller.deck.DeckControllerError;
import com.maxim.gamemanagement.core.exception.AlreadyExistsException;
import com.maxim.gamemanagement.core.exception.NotFoundException;
import com.maxim.gamemanagement.core.service.game.GameService;
import com.payfacto.gamemanagement.core.api.CreateGameRequestDto;
import com.payfacto.gamemanagement.core.api.DeckDto;
import com.payfacto.gamemanagement.core.api.GameApi;
import com.payfacto.gamemanagement.core.api.GameDeckDto;
import com.payfacto.gamemanagement.core.api.GameDto;
import com.payfacto.gamemanagement.core.api.GamePlayerDto;
import com.payfacto.gamemanagement.core.api.PlayerDto;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.core.Response;

@Named
public class GameController implements GameApi {

  private final GameService service;

  @Inject
  public GameController(GameService service) {
    this.service = service;
  }


  @Override
  public Response createGame(@Valid CreateGameRequestDto request) {
    GameDto game = new GameDto();
    if (request.getPlayersIds() != null && !request.getPlayersIds().isEmpty()) {
      game.players(request.getPlayersIds().stream().map(id -> new GamePlayerDto().id(id)).collect(
          Collectors.toList()));
    }
    if (request.getDeckId() != null) {
      game.deck(new GameDeckDto().id(request.getDeckId()));
    }
    return Response.ok().entity(this.service.create(game)).build();
  }

  @Override
  public Response getGame(UUID id) {
    try {
      return Response.ok().entity(this.service.find(id)).build();
    } catch (NotFoundException e) {
      throw new ControllerException(GameControllerError.GAME_NOT_FOUND);
    }
  }

  @Override
  public Response deleteGame(UUID id) {
    try {
      this.service.delete(id);
      return Response.ok().build();
    } catch (NotFoundException e) {
      throw new ControllerException(GameControllerError.GAME_NOT_FOUND);
    }
  }


  @Override
  public Response addDeck(UUID id, UUID deckId) {
    return null;
  }

  @Override
  public Response addPlayer(UUID id, UUID playerId) {
    return null;
  }


  @Override
  public Response removePlayer(UUID id, UUID playerId) {
    return null;
  }
}
