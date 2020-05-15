package com.maxim.gamemanagement.core.service.game;

import com.maxim.gamemanagement.core.persistence.domain.game.Game;
import com.maxim.gamemanagement.core.persistence.domain.game.GamePlayer;
import com.maxim.gamemanagement.core.persistence.domain.player.Player;
import com.payfacto.gamemanagement.core.api.GameDto;
import com.payfacto.gamemanagement.core.api.GamePlayerDto;
import com.payfacto.gamemanagement.core.api.PlayerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GamePlayerDtoDomainMapper {

  @Mapping(source = "name", target = "player.name")
  @Mapping(source = "id", target = "player.id")
  GamePlayer toDto(GamePlayerDto player);

  @Mapping(source = "player.name", target = "name")
  @Mapping(source = "player.id", target = "id")
  GamePlayerDto toDomain(GamePlayer player);
}