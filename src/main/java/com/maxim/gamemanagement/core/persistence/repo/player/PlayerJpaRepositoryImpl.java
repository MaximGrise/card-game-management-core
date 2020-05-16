package com.maxim.gamemanagement.core.persistence.repo.player;

import com.maxim.gamemanagement.core.persistence.domain.player.Player;
import com.maxim.gamemanagement.core.exception.NotFoundException;
import java.util.UUID;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerJpaRepositoryImpl implements PlayerRepository {

  private final Logger logger = LoggerFactory.getLogger(
      PlayerJpaRepositoryImpl.class);

  @PersistenceContext
  private EntityManager em;

  @Override
  public Player get(UUID id) throws NotFoundException {
    Player player = this.em.find(Player.class, id);
    if (player == null) {
      throw new NotFoundException(String.format("Player with id %s not found.", id));
    } else {
      return player;
    }
  }

  @Override
  @Transactional
  public Player insert(Player player) {
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
    Player player = this.em.find(Player.class, id);
    if (player == null) {
      throw new NotFoundException(String.format("Player with id %s not found.", id));
    } else {
      this.em.remove(player);
    }
  }
}
