package de.lemonpie.beddomischer.storage;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.listener.CardReaderListListener;
import de.lemonpie.beddomischer.listener.PlayerCardReaderListener;
import de.lemonpie.beddomischer.model.reader.BoardCardReader;
import de.lemonpie.beddomischer.model.reader.CardReader;
import de.lemonpie.beddomischer.model.reader.PlayerCardReader;

import java.sql.SQLException;

public class StorageCardReaderListListener implements CardReaderListListener {

    @Override
    public void addCardReader(CardReader cardReader) {
        try {
            if (BeddoMischerMain.getBoardCardReaderDao().queryForId(cardReader.getReaderId()) == null) {
                if (cardReader instanceof BoardCardReader) {
                    BeddoMischerMain.getBoardCardReaderDao().create((BoardCardReader) cardReader);
                } else if (cardReader instanceof PlayerCardReaderListener) {
                    BeddoMischerMain.getPlayerCardReaderDao().create((PlayerCardReader) cardReader);
                    ((PlayerCardReader) cardReader).addListener(new StoragePlayerCardReaderListener());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCardReader(CardReader cardReader) {
        if (cardReader instanceof BoardCardReader) {
            try {
                BeddoMischerMain.getBoardCardReaderDao().delete((BoardCardReader) cardReader);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (cardReader instanceof PlayerCardReaderListener) {
            try {
                BeddoMischerMain.getPlayerCardReaderDao().delete((PlayerCardReader) cardReader);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
