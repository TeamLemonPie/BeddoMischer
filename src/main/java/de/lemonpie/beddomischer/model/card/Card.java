package de.lemonpie.beddomischer.model.card;

public class Card {
	private final Color color;
	private final CardType type;

	public Card(Color color, CardType type) {
		this.color = color;
		this.type = type;
	}

	public Color getColor() {
		return color;
	}

	public CardType getType() {
		return type;
	}
}
