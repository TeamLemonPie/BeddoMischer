package de.lemonpie.beddomischer.model.reader;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class BoardCardReader extends CardReader {

    public BoardCardReader() {
    }

    public BoardCardReader(int readerId) {
        super(readerId);
    }

    @Override
    public String toString() {
        return "BoardCardReader{id=" + getReaderId() + "}";
    }
}
