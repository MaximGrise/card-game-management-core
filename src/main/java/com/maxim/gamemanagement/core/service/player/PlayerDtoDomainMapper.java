package com.maxim.gamemanagement.core.service.player;

import com.maxim.gamemanagement.core.persistence.domain.deck.Deck;
import com.maxim.gamemanagement.core.persistence.domain.player.Player;
import com.payfacto.gamemanagement.core.api.DeckDto;
import com.payfacto.gamemanagement.core.api.PlayerDto;
import org.mapstruct.Mapper;

@Mapper
public interface PlayerDtoDomainMapper {

  PlayerDto toDto(Player player);

  Player toDomain(PlayerDto player);
}