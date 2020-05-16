package com.maxim.gamemanagement.core.persistence.repo.game;

import com.maxim.gamemanagement.core.exception.NotFoundException;
import com.maxim.gamemanagement.core.persistence.domain.game.Game;
import com.maxim.gamemanagement.core.persistence.domain.game.GamePlayer;
import java.util.UUID;

public interface GamePlayerRepository {

  GamePlayer get(UUID id) throws NotFoundException;

  GamePlayer update(GamePlayer player);

  GamePlayer insert(GamePlayer player);

  void delete(UUID id) throws NotFoundException;
}
