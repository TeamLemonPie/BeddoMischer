package de.lemonpie.beddomischer.model;

import de.lemonpie.beddomischer.listener.BoardListener;
import de.lemonpie.beddomischer.model.card.Card;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Board {

	private List<BoardListener> listeners;
	private Card[] cards;

	public Board() {
		listeners = new LinkedList<>();
		cards = new Card[5];
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
		cards[index] = card;
		fireListener(listener -> listener.cardDidChangeAtIndex(index, card));
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
}
