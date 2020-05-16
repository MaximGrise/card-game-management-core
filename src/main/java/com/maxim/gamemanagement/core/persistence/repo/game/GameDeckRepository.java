package com.maxim.gamemanagement.core.persistence.repo.game;

import com.maxim.gamemanagement.core.exception.NotFoundException;
import com.maxim.gamemanagement.core.persistence.domain.game.Game;
import com.maxim.gamemanagement.core.persistence.domain.game.GameDeck;
import com.maxim.gamemanagement.core.persistence.domain.game.GamePlayer;
import java.util.UUID;

public interface GameDeckRepository {

  GameDeck get(UUID id) throws NotFoundException;

  GameDeck insert(GameDeck deck);

  void delete(UUID id) throws NotFoundException;
}
