package com.maxim.gamemanagement.core.persistence.domain.game;

import com.maxim.gamemanagement.core.persistence.domain.card.Card;
import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "game")
public class Game {

  @Id
  @NotNull
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(updatable = false, nullable = false, unique = true)
  private UUID id;

  @NotNull
  @JoinColumn(updatable = true, nullable = true, unique = false)
  @OneToOne
  private GameDeck deck;

  @NotNull
  @JoinColumn(updatable = true, nullable = false, unique = false)
  @OneToMany
  private List<GamePlayer> players;

  public Game() {
    players = new ArrayList<>();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public GameDeck getDeck() {
    return deck;
  }

  public void setDeck(GameDeck deck) {
    this.deck = deck;
  }

  public List<GamePlayer> getPlayers() {
    return players;
  }

  public void setPlayers(
      List<GamePlayer> players) {
    this.players = players;
  }
}
