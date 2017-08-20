package de.lemonpie.beddomischer.model.card;

public enum Color {
	PIK("Pi"),
	HERZ("He"),
	KARO("Ka"),
	KREUZ("Ke");

	private String name;

	Color(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
