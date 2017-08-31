package de.lemonpie.beddomischer.model.card;

public enum CardSymbol {

	BACK("Back", "B"),
	HEART("He", "H"),
	CROSS("Kr", "C"),
	SPADES("Pi", "P"),
	DIAMONDS("Ka", "K");

	private String shortName;
	private String shortCode;

	CardSymbol(String shortName, String shortCode) {
		this.shortName = shortName;
		this.shortCode = shortCode;
	}

	public String getShortName() {
		return shortName;
	}

	public String getShortCode() {
		return shortCode;
	}

	@Override
	public String toString() {
		return shortName;
	}
}
