package de.lemonpie.beddomischer.model;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.listener.BoardListener;
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.validator.CardValidator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Board {

    private List<BoardListener> listeners;
    private Card[] cards;

	private int smallBlind;
	private int bigBlind;

    public Board() {
        listeners = new LinkedList<>();
        cards = new Card[5];

        Arrays.fill(cards, Card.EMPTY);
    }

    public Card getCard(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= cards.length) {
            throw new IndexOutOfBoundsException("Index: " + index + " size: " + cards.length);
        }
        return cards[index];
    }

    public void setCard(int index, Card card) throws IndexOutOfBoundsException {
        if (index < 0 || index >= cards.length) {
            throw new IndexOutOfBoundsException("Index: " + index + " size: " + cards.length);
        }

        if (BeddoMischerMain.getBlockOption() != BlockOption.NONE) {
            return;
        }

        if (CardValidator.getInstance().validateCard(card)) {
            cards[index] = card;
            fireListener(listener -> listener.cardDidChangeAtIndex(index, card));
        }
    }

    public void setCard(Card card) throws IndexOutOfBoundsException {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == Card.EMPTY) {
                setCard(i, card);
                break;
            }
        }
    }

	public int getSmallBlind() {
		return smallBlind;
	}

	public void setSmallBlind(int smallBlind) {
		this.smallBlind = smallBlind;
		fireListener(listener -> listener.smallBlindDidChange(smallBlind));
	}

	public int getBigBlind() {
		return bigBlind;
	}

	public void setBigBlind(int bigBlind) {
		this.bigBlind = bigBlind;
		fireListener(listener -> listener.bigBlindDidChange(bigBlind));
	}

	public void addListener(BoardListener boardListener) {
		this.listeners.add(boardListener);
    }

    public void removeListener(BoardListener boardListener) {
        this.listeners.remove(boardListener);
    }

    private void fireListener(Consumer<BoardListener> consumer) {
        for (BoardListener boardListener : listeners) {
            consumer.accept(boardListener);
        }
    }

    public Card[] getCards() {
        return cards;
    }

    public int getNumberOfMissingCards() {
        return (int) Stream.of(cards).filter(c -> c == Card.EMPTY).count();
    }

    public void clearCards() {
        for (int i = 0; i < getCards().length; i++) {
            setCard(i, Card.EMPTY);
        }
    }

    @Override
    public String toString() {
        return "Board[cards=" + Arrays.toString(cards) + ']';
    }
}