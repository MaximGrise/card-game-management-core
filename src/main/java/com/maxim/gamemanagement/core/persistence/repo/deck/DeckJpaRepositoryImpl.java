package com.maxim.gamemanagement.core.persistence.repo.deck;

import com.maxim.gamemanagement.core.persistence.domain.deck.Deck;
import com.maxim.gamemanagement.core.exception.NotFoundException;
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
public class DeckJpaRepositoryImpl implements DeckRepository {

  private final Logger logger = LoggerFactory.getLogger(DeckJpaRepositoryImpl.class);

  @PersistenceContext
  private EntityManager em;

  @Override
  public Deck get(UUID id) throws NotFoundException {
    Deck deck = this.em.find(Deck.class, id);
    if (deck == null) {
      throw new NotFoundException(String.format("Deck with id %s not found.", id));
    } else {
      return deck;
    }
  }

  @Override
  @Transactional
  public Deck insert(Deck deck) {
    try {
      // FIXME kind of a business logic, should probably not be here
      deck.getCards().sort(Comparator.naturalOrder());
      this.em.persist(deck);
    } catch (EntityExistsException e) {
      logger.warn("Entity {} already exists.", deck);
    }
    return deck;
  }

  @Override
  @Transactional
  public void delete(UUID id) throws NotFoundException {
    Deck deck = this.em.find(Deck.class, id);
    if (deck == null) {
      throw new NotFoundException(String.format("Deck with id %s not found.", id));
    } else {
      this.em.remove(deck);
    }
  }
}
