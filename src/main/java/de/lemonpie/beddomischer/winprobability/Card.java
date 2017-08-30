package de.lemonpie.beddomischer.winprobability;


public class Card implements Comparable<Object> {
	private CardSymbol symbol;
	private CardValue value;

	public Card(CardSymbol symbol, CardValue value) {
		this.symbol = symbol;
		this.value = value;
	}

	public Card() {
		this.symbol = CardSymbol.BACK;
		this.value = CardValue.BACK;
	}

	public Card(String shortCode) {
		symbol = CardSymbol.fromShortCode(shortCode.substring(0, 1));
		value = CardValue.fromShortCode(shortCode.substring(1, 2));
	}

	public CardSymbol getSymbol() {
		return symbol;
	}

	public CardValue getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (symbol != other.symbol)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	public boolean isEmpty() {
		if (symbol == CardSymbol.BACK && value == CardValue.BACK) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Card [symbol=" + symbol + ", value=" + value + "]";
	}

	@Override
	public int compareTo(Object obj) {
		Card other = (Card) obj;

		return this.value.getValue().compareTo(other.getValue().getValue());
	}
}