package com.maxim.gamemanagement.core.service.card;

import com.maxim.gamemanagement.core.persistence.domain.card.Card;
import com.maxim.gamemanagement.core.persistence.domain.card.Card.CardSuit;
import com.maxim.gamemanagement.core.persistence.domain.card.Card.CardValue;
import com.maxim.gamemanagement.core.persistence.domain.deck.Deck;
import com.payfacto.gamemanagement.core.api.CardDto;
import com.payfacto.gamemanagement.core.api.CardDto.SuitEnum;
import com.payfacto.gamemanagement.core.api.CardDto.ValueEnum;
import com.payfacto.gamemanagement.core.api.DeckDto;
import javax.inject.Named;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

@Named
public interface CardDtoDomainMapper {

  // TODO Do proper mapstruct here converting the Enums...
  default CardDto toDto(Card card) {
    return new CardDto().suit(SuitEnum.fromValue(card.getSuit().name())).value(
        ValueEnum.fromValue(card.getValue().name()));
  }

  // TODO Do proper mapstruct here converting the Enums...
  default Card toDomain(CardDto card) {
    return new Card(CardSuit.fromName(card.getSuit().name()),
        CardValue.fromName(card.getValue().name()));
  }
}