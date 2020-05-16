package com.maxim.gamemanagement.core.utils;

import com.maxim.gamemanagement.core.persistence.domain.card.Card;
import java.util.List;
import java.util.Random;

/**
 * Could be just a generic Object shuffler...
 */
public final class CardShuffler {

  /**
   * Will modify the list directly.
   * <p>
   * Simple implementation, going for O(n).
   */
  public static void shuffle(List<Card> cards) {
    Random rand = new Random();
    for (int i = 0; i < cards.size() - 1; i++) {
      int randIndex = rand.nextInt(cards.size());
      Card prev = cards.get(i);
      cards.set(i, cards.get(randIndex));
      cards.set(randIndex, prev);
    }
  }
}
