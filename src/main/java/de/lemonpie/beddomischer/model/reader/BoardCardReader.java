package de.lemonpie.beddomischer.model.reader;

public class BoardCardReader extends CardReader {

    public BoardCardReader(int readerId) {
        super(readerId);
    }

    @Override
    public String toString() {
        return "BoardCardReader{id=" + getReaderId() + "}";
    }
}
