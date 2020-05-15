package com.maxim.gamemanagement.core.persistence.domain.card;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.payfacto.gamemanagement.core.api.CardDto.SuitEnum;
import javax.persistence.Embeddable;

@Embeddable
public class Card {

  private CardSuit suit;
  private CardValue value;

  public Card() {
  }

  public Card(CardSuit suit, CardValue value) {
    this.suit = suit;
    this.value = value;
  }

  public enum CardSuit {
    SPADE,
    HEART,
    CLUB,
    DIAMOND;

    public static CardSuit fromName(String name) {
      for (CardSuit b : CardSuit.values()) {
        if (b.name().equals(name)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + name + "'");
    }
  }

  public enum CardValue {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    JACK,
    QUEEN,
    KING,
    ACE;

    public static CardValue fromName(String name) {
      for (CardValue b : CardValue.values()) {
        if (b.name().equals(name)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + name + "'");
    }
  }

  public CardSuit getSuit() {
    return suit;
  }

  public void setSuit(CardSuit suit) {
    this.suit = suit;
  }

  public CardValue getValue() {
    return value;
  }

  public void setValue(CardValue value) {
    this.value = value;
  }
}
