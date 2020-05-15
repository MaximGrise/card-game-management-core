package com.maxim.gamemanagement.core.service.game;

import com.maxim.gamemanagement.core.persistence.domain.deck.Deck;
import com.maxim.gamemanagement.core.persistence.domain.game.Game;
import com.maxim.gamemanagement.core.persistence.domain.game.GameDeck;
import com.payfacto.gamemanagement.core.api.DeckDto;
import com.payfacto.gamemanagement.core.api.GameDeckDto;
import com.payfacto.gamemanagement.core.api.GameDto;
import java.util.Set;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

public interface GameDeckDtoDomainMapper {

  GameDeck toDto(GameDeckDto deck);

  GameDeckDto toDomain(GameDeck deck);

  default UUID deckToId(Deck deck) {
    return deck.getId();
  }
}