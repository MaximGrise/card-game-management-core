package com.maxim.gamemanagement.core.service.player;

import com.maxim.gamemanagement.core.persistence.repo.player.PlayerRepository;
import com.maxim.gamemanagement.core.exception.AlreadyExistsException;
import com.maxim.gamemanagement.core.exception.NotFoundException;
import com.payfacto.gamemanagement.core.api.PlayerDto;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PlayerService {

  private final PlayerRepository repo;
  private final PlayerDtoDomainMapper mapper;

  @Inject
  public PlayerService(PlayerRepository repo,
      PlayerDtoDomainMapper mapper) {
    this.repo = repo;
    this.mapper = mapper;
  }

  public PlayerDto find(UUID id) throws NotFoundException {
    return this.mapper.toDto(repo.get(id));
  }

  public PlayerDto create(PlayerDto player) throws AlreadyExistsException {
    return this.mapper.toDto(repo.insert(mapper.toDomain(player)));
  }

  public void delete(UUID id) throws NotFoundException {
    repo.delete(id);
  }
}
