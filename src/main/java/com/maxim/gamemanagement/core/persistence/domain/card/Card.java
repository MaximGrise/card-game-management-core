package com.maxim.gamemanagement.core.persistence.domain.card;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.payfacto.gamemanagement.core.api.CardDto.SuitEnum;
import javax.persistence.Embeddable;

@Embeddable
public class Card implements Comparable<Card> {

  private CardSuit suit;
  private CardValue value;

  public Card() {
  }

  public Card(CardSuit suit, CardValue value) {
    this.suit = suit;
    this.value = value;
  }

  @Override
  public int compareTo(Card o) {
    if (this.getValue().value > o.getValue().value) {
      return -1;
    } else if (this.getValue().value < o.getValue().value) {
      return 1;
    } else {
      return 0;
    }
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
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    JACK(10),
    QUEEN(11),
    KING(12),
    ACE(1);

    private final int value;

    public int getIntValue() {
      return value;
    }

    private CardValue(int value) {
      this.value = value;
    }

    public static CardValue fromName(String name) {
      for (CardValue b : CardValue.values()) {
        if (b.name().equals(name)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected name '" + name + "'");
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
