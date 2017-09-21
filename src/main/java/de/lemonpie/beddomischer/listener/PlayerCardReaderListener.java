package de.lemonpie.beddomischer.listener;

import de.lemonpie.beddomischer.model.reader.PlayerCardReader;

public interface PlayerCardReaderListener {
    void playerIdDidChange(PlayerCardReader reader, int playerId);
}
