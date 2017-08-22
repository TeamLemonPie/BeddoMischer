package de.lemonpie.beddomischer.model.reader;

public abstract  class CardReader {

	private final int readerId;

	CardReader(int readerId) {
		this.readerId = readerId;
	}

	public int getReaderId() {
		return readerId;
	}
}
