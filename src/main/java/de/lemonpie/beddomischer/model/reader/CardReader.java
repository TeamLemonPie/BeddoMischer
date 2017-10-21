package de.lemonpie.beddomischer.model.reader;

import com.j256.ormlite.field.DatabaseField;

public abstract  class CardReader {

	@DatabaseField(unique = true, generatedId = true)
	private int readerId;

    public CardReader() {
    }

	CardReader(int readerId) {
		this.readerId = readerId;
	}

	public int getReaderId() {
		return readerId;
	}
}
