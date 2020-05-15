package com.maxim.gamemanagement.core.controller.player;

import com.maxim.gamemanagement.core.aspects.LogMethodInOut;
import com.maxim.gamemanagement.core.controller.ControllerException;
import com.maxim.gamemanagement.core.exception.AlreadyExistsException;
import com.maxim.gamemanagement.core.exception.NotFoundException;
import com.maxim.gamemanagement.core.service.player.PlayerService;
import com.payfacto.gamemanagement.core.api.CreatePlayerRequestDto;
import com.payfacto.gamemanagement.core.api.PlayerDto;
import com.payfacto.gamemanagement.core.api.PlayerApi;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.core.Response;

@Named
public class PlayerController implements PlayerApi {

  private final PlayerService playerService;

  @Inject
  public PlayerController(PlayerService playerService) {
    this.playerService = playerService;
  }

  @Override
  @LogMethodInOut
  public Response getPlayer(UUID id) {
    try {
      return Response.ok().entity(this.playerService.find(id)).build();
    } catch (NotFoundException e) {
      throw new ControllerException(PlayerControllerError.PLAYER_NOT_FOUND);
    }
  }

  @Override
  @LogMethodInOut
  public Response createPlayer(@Valid CreatePlayerRequestDto createPlayerRequestDto) {
    try {
      return Response.ok()
          .entity(
              this.playerService.create(new PlayerDto().name(createPlayerRequestDto.getName())))
          .build();
    } catch (AlreadyExistsException e) {
      throw new ControllerException(PlayerControllerError.PLAYER_ALREADY_EXISTS);
    }
  }

  @Override
  @LogMethodInOut
  public Response deletePlayer(UUID id) {
    try {
      this.playerService.delete(id);
      return Response.ok().build();
    } catch (NotFoundException e) {
      throw new ControllerException(PlayerControllerError.PLAYER_NOT_FOUND);
    }
  }
}
