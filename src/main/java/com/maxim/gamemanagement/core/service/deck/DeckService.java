package com.maxim.gamemanagement.core.service.deck;

import com.maxim.gamemanagement.core.persistence.repo.deck.DeckRepository;
import com.maxim.gamemanagement.core.exception.AlreadyExistsException;
import com.maxim.gamemanagement.core.exception.NotFoundException;
import com.payfacto.gamemanagement.core.api.DeckDto;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DeckService {

  private final DeckRepository repo;
  private final DeckDtoDomainMapper mapper;

  @Inject
  public DeckService(DeckRepository repo,
      DeckDtoDomainMapper mapper) {
    this.repo = repo;
    this.mapper = mapper;
  }

  public DeckDto find(UUID id) throws NotFoundException {
    return this.mapper.toDto(repo.get(id));
  }

  public DeckDto create(DeckDto deck) throws AlreadyExistsException {
    return this.mapper.toDto(repo.insert(mapper.toDomain(deck)));
  }

  public void delete(UUID id) throws NotFoundException {
    repo.delete(id);
  }
}
