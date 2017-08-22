package de.lemonpie.beddomischer.model;

public class CardReader {

	public enum CardReaderType {
		PLAYER,
		BOARD
	}

	private final int readerID;

	private CardReaderType type;
	private int index;

	public CardReader(int readerID, CardReaderType type, int index) {
		this.readerID = readerID;
		this.type = type;
		this.index = index;
	}

	public int getReaderID() {
		return readerID;
	}

	public CardReaderType getType() {
		return type;
	}

	public void setType(CardReaderType type) {
		this.type = type;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
