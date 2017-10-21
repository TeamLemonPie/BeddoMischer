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

    private Set<Card> detectedCards = new HashSet<>();

    public boolean validateCard(Card card) {
        if (card != Card.EMPTY) {
            for (Card c : detectedCards) {
                if (card.equals(c)) {
                    return false;
                }
            }
            detectedCards.add(card);
            return true;
        } else {
            return true;
        }
    }

    public void clear() {
        detectedCards.clear();
    }

    public void clear(Card... cards) {
        for (Card card : cards) {
            detectedCards.remove(card);
        }
    }
}
