package de.lemonpie.beddomischer.model.card;

public class BlankCard extends Card {

	@Override
	public int compareTo(Card o) {
		if (o instanceof BlankCard) {
			return 0;
		}
		return -1;
	}

	@Override
	public String name() {
		return "back";
	}
}
