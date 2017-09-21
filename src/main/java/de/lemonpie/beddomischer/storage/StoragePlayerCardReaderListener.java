package de.lemonpie.beddomischer.storage;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.listener.PlayerCardReaderListener;
import de.lemonpie.beddomischer.model.reader.PlayerCardReader;

import java.sql.SQLException;

public class StoragePlayerCardReaderListener implements PlayerCardReaderListener {
    @Override
    public void playerIdDidChange(PlayerCardReader reader, int playerId) {
        try {
            BeddoMischerMain.getPlayerCardReaderDao().update(reader);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
