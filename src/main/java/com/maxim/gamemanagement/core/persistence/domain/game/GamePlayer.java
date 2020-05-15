package com.maxim.gamemanagement.core.persistence.domain.game;

import com.maxim.gamemanagement.core.persistence.domain.card.Card;
import com.maxim.gamemanagement.core.persistence.domain.player.Player;
import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Embeddable
@Table(name = "game_player")
public class GamePlayer {

  @Id
  @NotNull
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(updatable = false, nullable = false, unique = true)
  private UUID id;

  @ManyToOne
  @JoinColumn(updatable = false, nullable = false, unique = false)
  private Game game;

  @NotNull
  @Column(updatable = true, nullable = false, unique = false)
  @ElementCollection
  private List<Card> hand;

  @NotNull
  @OneToOne
  @JoinColumn(updatable = true, nullable = false, unique = false)
  private Player player;

  public GamePlayer() {
  }

  public GamePlayer(UUID id, List<Card> hand, Player player) {
    this.id = id;
    this.hand = hand;
    this.player = player;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public List<Card> getHand() {
    return hand;
  }

  public void setHand(List<Card> hand) {
    this.hand = hand;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
}
