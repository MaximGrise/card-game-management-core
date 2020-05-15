package com.maxim.gamemanagement.core.persistence.repo.game;

import com.maxim.gamemanagement.core.exception.NotFoundException;
import com.maxim.gamemanagement.core.persistence.domain.game.Game;
import java.util.UUID;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class GameJpaRepositoryImpl implements GameRepository {

  private final Logger logger = LoggerFactory.getLogger(
      GameJpaRepositoryImpl.class);

  @PersistenceContext
  private EntityManager em;

  @Override
  public Game get(UUID id) throws NotFoundException {
    Game Game = this.em.find(Game.class, id);
    if (Game == null) {
      throw new NotFoundException(String.format("Game with id %s not found.", id));
    } else {
      return Game;
    }
  }

  @Override
  @Transactional
  public Game insert(Game Game) {
    try {
      this.em.persist(Game);
    } catch (EntityExistsException e) {
      logger.warn("Entity {} already exists.", Game);
    }
    return Game;
  }

  @Override
  @Transactional
  public void delete(UUID id) throws NotFoundException {
    Game Game = this.em.find(Game.class, id);
    if (Game == null) {
      throw new NotFoundException(String.format("Game with id %s not found.", id));
    } else {
      this.em.remove(Game);
    }
  }
}
