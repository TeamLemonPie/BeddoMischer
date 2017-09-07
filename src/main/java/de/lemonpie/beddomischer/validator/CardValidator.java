package de.lemonpie.beddomischer.validator;

import de.lemonpie.beddomischer.model.card.Card;

import java.util.HashSet;
import java.util.Set;

public class CardValidator {

    private static final CardValidator INSTANCE;

    static {
        INSTANCE = new CardValidator();
    }

    public static CardValidator getInstance() {
        return INSTANCE;
    }

    private Set<Card> detactedCards = new HashSet<>();

    public boolean validateCard(Card card) {
        System.out.println(detactedCards);
        if (card != Card.EMPTY) {
            for (Card c : detactedCards) {

                if (card.equals(card)) {
                    return false;
                }
            }
            detactedCards.add(card);
            return true;
        } else {
            return true;
        }

    }

    public void clear() {
        detactedCards.clear();
    }

    public void clear(Card... cards) {
        for (int i = 0; i < cards.length; i++) {
            detactedCards.remove(cards[i]);
        }
    }
}
