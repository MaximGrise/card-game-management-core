package com.maxim.gamemanagement.core.persistence.repo.game;

import com.maxim.gamemanagement.core.exception.AlreadyExistsException;
import com.maxim.gamemanagement.core.exception.NotFoundException;
import com.maxim.gamemanagement.core.persistence.domain.deck.Deck;
import com.maxim.gamemanagement.core.persistence.domain.game.Game;
import java.util.UUID;

public interface GameRepository {

  Game get(UUID id) throws NotFoundException;

  Game insert(Game game);

  void delete(UUID id) throws NotFoundException;
}
