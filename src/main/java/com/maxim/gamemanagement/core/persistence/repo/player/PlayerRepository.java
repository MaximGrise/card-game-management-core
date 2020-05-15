package com.maxim.gamemanagement.core.persistence.repo.player;

import com.maxim.gamemanagement.core.persistence.domain.player.Player;
import com.maxim.gamemanagement.core.exception.AlreadyExistsException;
import com.maxim.gamemanagement.core.exception.NotFoundException;
import java.util.UUID;

public interface PlayerRepository {

  Player get(UUID id) throws NotFoundException;

  Player insert(Player deck) throws AlreadyExistsException;

  void delete(UUID id) throws NotFoundException;
}
