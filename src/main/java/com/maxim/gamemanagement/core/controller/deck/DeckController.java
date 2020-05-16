package com.maxim.gamemanagement.core.controller.deck;

import com.maxim.gamemanagement.core.aspects.LogMethodInOut;
import com.maxim.gamemanagement.core.controller.ControllerException;
import com.maxim.gamemanagement.core.service.deck.DeckService;
import com.maxim.gamemanagement.core.exception.AlreadyExistsException;
import com.maxim.gamemanagement.core.exception.NotFoundException;
import com.payfacto.gamemanagement.core.api.CreateDeckRequestDto;
import com.payfacto.gamemanagement.core.api.DeckApi;
import com.payfacto.gamemanagement.core.api.DeckDto;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Named
@Path("/deck")
public class DeckController implements DeckApi {

  private final DeckService deckService;

  @Inject
  public DeckController(DeckService deckService) {
    this.deckService = deckService;
  }


  @Override
  @LogMethodInOut
  public Response getDeck(UUID id) {
    try {
      return Response.ok().entity(this.deckService.find(id)).build();
    } catch (NotFoundException e) {
      throw new ControllerException(DeckError.DECK_NOT_FOUND);
    }
  }

  @Override
  @LogMethodInOut
  public Response createDeck(@Valid CreateDeckRequestDto createDeckRequestDto) {
    try {
      return Response.ok()
          .entity(this.deckService.create(new DeckDto().cards(createDeckRequestDto.getCards())))
          .build();
    } catch (AlreadyExistsException e) {
      throw new ControllerException(DeckError.DECK_ALREADY_EXISTS);
    }
  }

  @Override
  @LogMethodInOut
  public Response deleteDeck(UUID id) {
    try {
      this.deckService.delete(id);
      return Response.ok().build();
    } catch (NotFoundException e) {
      throw new ControllerException(DeckError.DECK_NOT_FOUND);
    }
  }
}
