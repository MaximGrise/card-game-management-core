package com.maxim.gamemanagement.core.service.game;

import com.maxim.gamemanagement.core.persistence.domain.game.Game;
import com.payfacto.gamemanagement.core.api.GameDto;
import org.mapstruct.Mapper;

@Mapper(uses = {GameDeckDtoDomainMapper.class, GamePlayerDtoDomainMapper.class})
public interface GameDtoDomainMapper {

  // id   OK
  // deck
  // players
  GameDto toDto(Game Game);

  Game toDomain(GameDto Game);
}