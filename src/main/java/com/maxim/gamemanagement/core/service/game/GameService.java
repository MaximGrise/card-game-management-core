package com.maxim.gamemanagement.core.service.game;

import com.maxim.gamemanagement.core.exception.AlreadyExistsException;
import com.maxim.gamemanagement.core.exception.NotFoundException;
import com.maxim.gamemanagement.core.persistence.repo.deck.DeckRepository;
import com.maxim.gamemanagement.core.persistence.repo.game.GameRepository;
import com.maxim.gamemanagement.core.service.deck.DeckService;
import com.maxim.gamemanagement.core.service.player.PlayerService;
import com.payfacto.gamemanagement.core.api.DeckDto;
import com.payfacto.gamemanagement.core.api.GameDto;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class GameService {

  private final GameRepository repo;

  private final GameDtoDomainMapper mapper;

  @Inject
  public GameService(GameRepository repo, GameDtoDomainMapper mapper) {
    this.repo = repo;
    this.mapper = mapper;
  }

  public GameDto find(UUID id) throws NotFoundException {
    return this.mapper.toDto(repo.get(id));
  }

  public GameDto create(GameDto game) {
    return this.mapper.toDto(repo.insert(mapper.toDomain(game)));
  }

  public void delete(UUID id) throws NotFoundException {
    repo.delete(id);
  }
}
