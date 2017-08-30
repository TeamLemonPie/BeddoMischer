package de.lemonpie.beddomischer.winprobability;

public enum CardSymbol {
	BACK("Back", "B"),
	HEART("He", "H"),
	CROSS("Kr", "C"),
	SPADES("Pi", "P"),
	DIAMONDS("Ka", "K");

	private String shortName;
	private String shortCode;

	private CardSymbol(String shortName, String shortCode) {
		this.shortName = shortName;
		this.shortCode = shortCode;
	}

	public String getShortName() {
		return shortName;
	}

	public String getShortCode() {
		return shortCode;
	}

	public static CardSymbol fromShortCode(String shortCode) {
		for (CardSymbol value : values()) {
			if (value.shortCode.equals(shortCode.toUpperCase())) {
				return value;
			}
		}

		return null;
	}
}