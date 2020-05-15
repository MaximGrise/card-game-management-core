package com.maxim.gamemanagement.core.service.deck;

import com.maxim.gamemanagement.core.persistence.domain.deck.Deck;
import com.payfacto.gamemanagement.core.api.DeckDto;
import org.mapstruct.Mapper;

@Mapper
public interface DeckDtoDomainMapper {

  DeckDto toDto(Deck deck);

  Deck toDomain(DeckDto deck);
}