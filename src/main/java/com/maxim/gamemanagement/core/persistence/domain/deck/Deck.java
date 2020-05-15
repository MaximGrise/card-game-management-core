package com.maxim.gamemanagement.core.persistence.domain.deck;

import com.maxim.gamemanagement.core.persistence.domain.card.Card;
import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "deck")
public class Deck {

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
  @ElementCollection(fetch = FetchType.EAGER)
  private List<Card> cards;

  public Deck() {
    this.cards = new ArrayList<>();
  }

  public UUID getId() {
    return id;
  }

  public List<Card> getCards() {
    return cards;
  }

  @Override
  public String toString() {
    return "Deck{" +
        "id=" + id +
        ", cards=" + cards +
        '}';
  }
}
