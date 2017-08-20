package de.lemonpie.beddomischer.model.card;

public enum CardType {
	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8"),
	NINE("9"),
	TEN("10"),
	JACK("B"),
	QUEEN("D"),
	KING("K");

	private String name;

	CardType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
