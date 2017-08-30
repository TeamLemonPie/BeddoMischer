package de.lemonpie.beddomischer.model.card;

public enum CardType {

	TWO("2", 2),
	THREE("3", 3),
	FOUR("4", 4),
	FIVE("5", 5),
	SIX("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8),
	NINE("9", 9),
	TEN("10", 10),
	JACK("B", 11),
	QUEEN("D", 12),
	KING("K", 13),
	ACE("A", 14);

	private String name;
	private int value;

	CardType(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return name;
	}
}
