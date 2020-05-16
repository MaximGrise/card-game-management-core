package com.maxim.gamemanagement.core.controller.game;

import com.maxim.gamemanagement.core.controller.ControllerException;
import com.maxim.gamemanagement.core.controller.deck.DeckError;
import com.maxim.gamemanagement.core.controller.player.PlayerError;
import com.maxim.gamemanagement.core.exception.NotFoundException;
import com.maxim.gamemanagement.core.persistence.domain.card.Card;
import com.maxim.gamemanagement.core.persistence.domain.game.Game;
import com.maxim.gamemanagement.core.service.game.GameService;
import com.maxim.gamemanagement.core.service.game.GameService.DeckNotFoundException;
import com.maxim.gamemanagement.core.service.game.GameService.GameNotFoundException;
import com.maxim.gamemanagement.core.service.game.GameService.PlayerNotFoundException;
import com.payfacto.gamemanagement.core.api.CardDto;
import com.payfacto.gamemanagement.core.api.CreateGameRequestDto;
import com.payfacto.gamemanagement.core.api.GameApi;
import com.payfacto.gamemanagement.core.api.GameDeckDto;
import com.payfacto.gamemanagement.core.api.GameDto;
import com.payfacto.gamemanagement.core.api.GamePlayerDto;
import com.payfacto.gamemanagement.core.api.GameScoreDto;
import com.payfacto.gamemanagement.core.api.PlayerDto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    try {
      return Response.ok().entity(this.service.create(game)).build();
    } catch (DeckNotFoundException e) {
      throw new ControllerException(DeckError.DECK_NOT_FOUND);
    } catch (PlayerNotFoundException e) {
      throw new ControllerException(PlayerError.PLAYER_NOT_FOUND);
    }
  }


  @Override
  public Response getGame(UUID id) {
    try {
      return Response.ok().entity(this.service.find(id)).build();
    } catch (NotFoundException e) {
      throw new ControllerException(GameError.GAME_NOT_FOUND);
    }
  }

  @Override
  public Response deleteGame(UUID id) {
    try {
      this.service.delete(id);
      return Response.ok().build();
    } catch (NotFoundException e) {
      throw new ControllerException(GameError.GAME_NOT_FOUND);
    }
  }

  @Override
  public Response addGameDeck(UUID id, UUID deckId) {
    try {
      this.service.addDeck(id, deckId);
      return Response.ok().build();
    } catch (GameNotFoundException e) {
      throw new ControllerException(GameError.GAME_NOT_FOUND);
    } catch (DeckNotFoundException e) {
      throw new ControllerException(DeckError.DECK_NOT_FOUND);
    }
  }

  @Override
  public Response getGameDeck(UUID id) {
    return null;
  }


  @Override
  public Response addPlayer(UUID id, UUID playerId) {
    try {
      this.service.addPlayer(id, playerId);
      return Response.ok().build();
    } catch (GameNotFoundException e) {
      throw new ControllerException(GameError.GAME_NOT_FOUND);
    } catch (PlayerNotFoundException e) {
      throw new ControllerException(PlayerError.PLAYER_NOT_FOUND);
    }
  }


  @Override
  public Response removePlayer(UUID id, UUID playerId) {
    try {
      this.service.removePlayer(id, playerId);
      return Response.ok().build();
    } catch (GameNotFoundException e) {
      throw new ControllerException(GameError.GAME_NOT_FOUND);
    } catch (PlayerNotFoundException e) {
      throw new ControllerException(PlayerError.PLAYER_NOT_FOUND);
    }
  }

  @Override
  public Response shuffleDeck(UUID id) {
    try {
      this.service.shuffleDeck(id);
      return Response.ok().build();
    } catch (NotFoundException e) {
      throw new ControllerException(GameError.GAME_NOT_FOUND);
    }
  }

  @Override
  public Response dealCardToPlayer(UUID id, @NotNull Integer count, @NotNull UUID playerId) {
    try {
      this.service.dealCardToPlayer(id, count, playerId);
      return Response.ok().build();
    } catch (NotFoundException e) {
      throw new ControllerException(PlayerError.PLAYER_NOT_FOUND);
    }
  }

  @Override
  public Response getGameScore(UUID id) {
    try {
      return Response.ok().entity(this.service.retrieveScore(id)).build();
    } catch (NotFoundException e) {
      throw new ControllerException(GameError.GAME_NOT_FOUND);
    }
  }
}
