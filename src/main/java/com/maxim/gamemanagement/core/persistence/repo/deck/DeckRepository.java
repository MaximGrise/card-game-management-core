package com.maxim.gamemanagement.core.persistence.repo.deck;

import com.maxim.gamemanagement.core.persistence.domain.deck.Deck;
import com.maxim.gamemanagement.core.exception.AlreadyExistsException;
import com.maxim.gamemanagement.core.exception.NotFoundException;
import java.util.UUID;

public interface DeckRepository {

  Deck get(UUID id) throws NotFoundException;

  Deck insert(Deck deck) throws AlreadyExistsException;

  void delete(UUID id) throws NotFoundException;
}
