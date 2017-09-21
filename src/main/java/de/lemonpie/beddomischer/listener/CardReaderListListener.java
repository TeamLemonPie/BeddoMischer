package de.lemonpie.beddomischer.listener;

import de.lemonpie.beddomischer.model.reader.CardReader;

public interface CardReaderListListener {

    void addCardReader(CardReader cardReader);

    void removeCardReader(CardReader cardReader);
}
