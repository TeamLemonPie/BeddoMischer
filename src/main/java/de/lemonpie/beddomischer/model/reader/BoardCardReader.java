package de.lemonpie.beddomischer.model.reader;

public class BoardCardReader extends CardReader {

	private int index;

	public BoardCardReader(int readerId, int index) {
		super(readerId);
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "BoardCardReader{" +
				"index=" + index +
				'}';
	}
}
