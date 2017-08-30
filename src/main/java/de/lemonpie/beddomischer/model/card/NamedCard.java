package de.lemonpie.beddomischer.model.card;

public class NamedCard extends Card {

	private final CardSymbol cardSymbol;
	private final CardType type;

	public NamedCard(CardSymbol cardSymbol, CardType type) {
		this.cardSymbol = cardSymbol;
		this.type = type;
	}

	public CardSymbol getCardSymbol() {
		return cardSymbol;
	}

	public CardType getType() {
		return type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof NamedCard)) return false;

		NamedCard namedCard = (NamedCard) o;

		if (cardSymbol != namedCard.cardSymbol) return false;
		return type == namedCard.type;
	}

	@Override
	public int hashCode() {
		int result = cardSymbol != null ? cardSymbol.hashCode() : 0;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		return result;
	}

	@Override
	public int compareTo(Card o) {
		if (o instanceof NamedCard) {
			return Integer.compare(type.ordinal(), ((NamedCard) o).getType().ordinal());
		}
		return 0;
	}

	@Override
	public String name() {
		return cardSymbol.toString() + "-" + type.toString();
	}
}
