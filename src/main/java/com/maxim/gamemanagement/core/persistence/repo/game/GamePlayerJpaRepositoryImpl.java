package com.maxim.gamemanagement.core.persistence.repo.game;

import com.maxim.gamemanagement.core.exception.NotFoundException;
import com.maxim.gamemanagement.core.persistence.domain.game.Game;
import com.maxim.gamemanagement.core.persistence.domain.game.GamePlayer;
import com.maxim.gamemanagement.core.persistence.domain.player.Player;
import com.maxim.gamemanagement.core.persistence.repo.player.PlayerRepository;
import java.util.UUID;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class GamePlayerJpaRepositoryImpl implements GamePlayerRepository {

  private final Logger logger = LoggerFactory.getLogger(
      GamePlayerJpaRepositoryImpl.class);

  @PersistenceContext
  private EntityManager em;

  @Override
  public GamePlayer get(UUID id) throws NotFoundException {
    GamePlayer gamePlayer = this.em.find(GamePlayer.class, id);
    if (gamePlayer == null) {
      throw new NotFoundException(String.format("GamePlayer with id %s not found.", id));
    } else {
      return gamePlayer;
    }
  }

  @Override
  public GamePlayer update(GamePlayer player) {
    this.em.merge(player);
    return player;
  }

  @Override
  @Transactional
  public GamePlayer insert(GamePlayer player) {
    try {
      this.em.persist(player);
    } catch (EntityExistsException e) {
      logger.warn("Entity {} already exists.", player);
    }
    return player;
  }

  @Override
  @Transactional
  public void delete(UUID id) throws NotFoundException {
    GamePlayer player = this.em.find(GamePlayer.class, id);
    if (player == null) {
      throw new NotFoundException(String.format("GamePlayer with id %s not found.", id));
    } else {
      this.em.remove(player);
    }
  }
}
