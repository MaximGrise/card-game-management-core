package com.maxim.gamemanagement.core.persistence.domain.game;

import com.maxim.gamemanagement.core.persistence.domain.card.Card;
import com.maxim.gamemanagement.core.persistence.domain.deck.Deck;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Embeddable
@Table(name = "game_deck")
public class GameDeck {

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
  @ElementCollection
  @Column(updatable = true, nullable = false, unique = false)
  private List<Card> unplayed;

  @NotNull
  @ElementCollection
  @Column(updatable = true, nullable = false, unique = false)
  private List<Card> played;

//
//  @NotNull
//  @OneToMany
//  @JoinColumn(updatable = true, nullable = false, unique = false)
//  private List<Deck> originalDecks;

  public GameDeck() {
    this.unplayed = new ArrayList<>();
    this.played = new ArrayList<>();
//    this.originalDecks = new ArrayList<>();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public List<Card> getUnplayed() {
    return unplayed;
  }

  public void setUnplayed(
      List<Card> unplayed) {
    this.unplayed = unplayed;
  }

  public List<Card> getPlayed() {
    return played;
  }

  public void setPlayed(List<Card> played) {
    this.played = played;
  }

//  public List<Deck> getOriginalDecks() {
//    return originalDecks;
//  }
//
//  public void setOriginalDecks(
//      List<Deck> originalDecks) {
//    this.originalDecks = originalDecks;
//  }
}
