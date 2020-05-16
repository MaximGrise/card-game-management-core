package com.maxim.gamemanagement.core.service.game;

import com.maxim.gamemanagement.core.exception.NotFoundException;
import com.maxim.gamemanagement.core.persistence.domain.card.Card;
import com.maxim.gamemanagement.core.persistence.domain.deck.Deck;
import com.maxim.gamemanagement.core.persistence.domain.game.Game;
import com.maxim.gamemanagement.core.persistence.domain.game.GameDeck;
import com.maxim.gamemanagement.core.persistence.domain.game.GamePlayer;
import com.maxim.gamemanagement.core.persistence.domain.player.Player;
import com.maxim.gamemanagement.core.persistence.repo.game.GameDeckRepository;
import com.maxim.gamemanagement.core.persistence.repo.game.GamePlayerRepository;
import com.maxim.gamemanagement.core.persistence.repo.game.GameRepository;
import com.maxim.gamemanagement.core.persistence.repo.player.PlayerRepository;
import com.maxim.gamemanagement.core.service.card.CardDtoDomainMapper;
import com.maxim.gamemanagement.core.service.deck.DeckService;
import com.maxim.gamemanagement.core.service.player.PlayerService;
import com.maxim.gamemanagement.core.utils.CardShuffler;
import com.payfacto.gamemanagement.core.api.CardDto;
import com.payfacto.gamemanagement.core.api.DeckDto;
import com.payfacto.gamemanagement.core.api.GameDto;
import com.payfacto.gamemanagement.core.api.GamePlayerDto;
import com.payfacto.gamemanagement.core.api.GameScoreDto;
import com.payfacto.gamemanagement.core.api.PlayerDto;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.aspectj.weaver.ast.Not;
import org.dom4j.dtd.Decl;
import org.springframework.web.client.HttpClientErrorException.NotFound;

@Named
public class GameService {

  private final DeckService deckService;
  private final PlayerRepository playerRepo;
  private final GameDeckRepository gameDeckRepo;
  private final GameRepository gameRepo;
  private final GamePlayerRepository gamePlayerRepo;
  private final CardDtoDomainMapper cardMapper;
  private final GamePlayerDtoDomainMapper gamePlayerMapper;
  private final GameDtoDomainMapper mapper;

  public GameService(DeckService deckService,
      PlayerRepository playerRepo,
      GameDeckRepository gameDeckRepo,
      GameRepository gameRepo,
      GamePlayerRepository gamePlayerRepo,
      CardDtoDomainMapper cardMapper,
      GamePlayerDtoDomainMapper gamePlayerMapper,
      GameDtoDomainMapper mapper) {
    this.deckService = deckService;
    this.playerRepo = playerRepo;
    this.gameDeckRepo = gameDeckRepo;
    this.gameRepo = gameRepo;
    this.gamePlayerRepo = gamePlayerRepo;
    this.cardMapper = cardMapper;
    this.gamePlayerMapper = gamePlayerMapper;
    this.mapper = mapper;
  }

  public GameDto find(UUID id) throws NotFoundException {
    return this.mapper.toDto(gameRepo.get(id));
  }

  public GameDto create(GameDto gameDto) throws PlayerNotFoundException, DeckNotFoundException {
    Game game = this.mapper.toDomain(gameDto);

    // if a deck is specified, make sure it exists and add it to the gamedeck first
    if (gameDto.getDeck() != null) {
      DeckDto deck;
      try {
        deck = this.deckService.find(gameDto.getDeck().getId());
      } catch (NotFoundException e) {
        throw new DeckNotFoundException();
      }
      GameDeck gameDeck = new GameDeck();
      gameDeck.getUnplayed()
          .addAll(deck.getCards().stream().map(cardMapper::toDomain).collect(
              Collectors.toList()));
      this.gameDeckRepo.insert(gameDeck);
      game.setDeck(gameDeck);
    }

    // make sure all players exists, and then add them to the game
    if (gameDto.getPlayers() != null && !gameDto.getPlayers().isEmpty()) {
      for (GamePlayerDto gamePlayer : gameDto.getPlayers()) {
        try {
          Player player = this.playerRepo.get(gamePlayer.getId());
          game.getPlayers().add(new GamePlayer(player));
        } catch (NotFoundException e) {
          throw new PlayerNotFoundException(gamePlayer.getId());
        }
      }
    }

    return this.mapper.toDto(this.gameRepo.insert(game));
  }

  public void delete(UUID id) throws NotFoundException {
    gameRepo.delete(id);
  }

  public void shuffleDeck(UUID id)
      throws NotFoundException {
    Game game = gameRepo.get(id);
    CardShuffler.shuffle(game.getDeck().getUnplayed());
    gameRepo.update(game);
  }

  public void dealCardToPlayer(UUID gameId, int cardCount, UUID playerId)
      throws NotFoundException {
    Game game;
    GamePlayer player;
    try {
      game = gameRepo.get(gameId);
    } catch (NotFoundException e) {
      throw new GameNotFoundException();
    }
    try {
      player = this.gamePlayerRepo.get(playerId);
    } catch (NotFoundException e) {
      throw new PlayerNotFoundException(playerId);
    }

    for (int i = 0; i < cardCount; i++) {
      // FIXME do we throw if no cards left?
      if (game.getDeck().getUnplayed().size() > 0) {
        Card dealt = game.getDeck().getUnplayed()
            .remove(new Random().nextInt(game.getDeck().getUnplayed().size()));
        game.getDeck().getPlayed().add(dealt);
        player.getHand().add(dealt);
      }
    }

    this.gameRepo.update(game);
    this.gamePlayerRepo.update(player);
  }

  public List<GameScoreDto> retrieveScore(UUID id) throws NotFoundException {
    Game game = this.gameRepo.get(id);
    return game.getPlayers().stream()
        .map(player -> {
          int cardTotalValue = 0;
          for (Card card : player.getHand()) {
            cardTotalValue += card.getValue().getIntValue();
          }
          return new GameScoreDto()
              .playerName(player.getPlayer().getName())
              .playerId(player.getPlayer().getId())
              .totalFaceValue(cardTotalValue);
        })
        .sorted(Comparator.comparingInt(GameScoreDto::getTotalFaceValue))
        .collect(Collectors.toList());
  }

  public void addDeck(UUID game, UUID deck) throws GameNotFoundException, DeckNotFoundException {
    // TODO
    throw new UnsupportedOperationException();
  }

  public void addPlayer(UUID game, UUID player)
      throws GameNotFoundException, PlayerNotFoundException {
    // TODO
    throw new UnsupportedOperationException();
  }

  public void removePlayer(UUID game, UUID player)
      throws GameNotFoundException, PlayerNotFoundException {
    // TODO
    throw new UnsupportedOperationException();
  }

  public class GameNotFoundException extends NotFoundException {

  }

  public class DeckNotFoundException extends NotFoundException {

  }

  public class PlayerNotFoundException extends NotFoundException {

    private UUID playerId;

    public PlayerNotFoundException(UUID playerId) {
      this.playerId = playerId;
    }

    public UUID getPlayerId() {
      return playerId;
    }
  }
}
