package com.maxim.gamemanagement.core.persistence.repo.game;

import com.maxim.gamemanagement.core.exception.NotFoundException;
import com.maxim.gamemanagement.core.persistence.domain.game.Game;
import com.maxim.gamemanagement.core.persistence.domain.game.GameDeck;
import java.util.Comparator;
import java.util.UUID;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class GameDeckJpaRepositoryImpl implements GameDeckRepository {

  private final Logger logger = LoggerFactory.getLogger(
      GameDeckJpaRepositoryImpl.class);

  @PersistenceContext
  private EntityManager em;

  @Override
  public GameDeck get(UUID id) throws NotFoundException {
    GameDeck gameDeck = this.em.find(GameDeck.class, id);
    if (gameDeck == null) {
      throw new NotFoundException(String.format("GameDeck with id %s not found.", id));
    } else {
      return gameDeck;
    }
  }

  @Override
  @Transactional
  public GameDeck insert(GameDeck gameDeck) {
    try {
      // FIXME kind of a business logic, should probably not be here
      gameDeck.getUnplayed().sort(Comparator.naturalOrder());
      gameDeck.getPlayed().sort(Comparator.naturalOrder());
      this.em.persist(gameDeck);
    } catch (EntityExistsException e) {
      logger.warn("Entity {} already exists.", gameDeck);
    }
    return gameDeck;
  }

  @Override
  @Transactional
  public void delete(UUID id) throws NotFoundException {
    GameDeck gameDeck = this.em.find(GameDeck.class, id);
    if (gameDeck == null) {
      throw new NotFoundException(String.format("GameDeck with id %s not found.", id));
    } else {
      this.em.remove(gameDeck);
    }
  }
}
