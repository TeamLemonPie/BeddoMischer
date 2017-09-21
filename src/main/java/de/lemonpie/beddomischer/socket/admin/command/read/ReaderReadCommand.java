package de.lemonpie.beddomischer.socket.admin.command.read;

import com.google.gson.JsonObject;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.model.reader.BoardCardReader;
import de.lemonpie.beddomischer.model.reader.CardReader;
import de.lemonpie.beddomischer.model.reader.PlayerCardReader;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

import java.util.Optional;

/**
 * Configure Reader.
 * <code>key = reader id</code>
 * <code>value = {type, index}</code>
 */
public class ReaderReadCommand implements Command {
    @Override
    public CommandName name() {
        return CommandName.READER;
    }

    @Override
    public void execute(CommandData command) {
        int readerId = command.getKey();

        Optional<CardReader> cardReader = BeddoMischerMain.getCardReader(readerId);

        if (command.getValue() instanceof JsonObject) {
            JsonObject value = (JsonObject) command.getValue();
            int type = value.get("type").getAsInt();

            if (type == 0) { // PLAYER
                int playerId = value.get("playerId").getAsInt();

                if (cardReader.isPresent() && cardReader.get() instanceof PlayerCardReader) {
                    PlayerCardReader reader = (PlayerCardReader) cardReader.get();
                    reader.setPlayerId(playerId);
                } else {
                    cardReader.ifPresent(reader -> BeddoMischerMain.getCardReaders().remove(reader)); // Remove old
                    PlayerCardReader reader = new PlayerCardReader(readerId, playerId);
                    BeddoMischerMain.getCardReaders().add(reader);
                }
            } else if (type == 1) { // BOARD
                if (!(cardReader.isPresent() && cardReader.get() instanceof BoardCardReader)) {
                    cardReader.ifPresent(reader -> BeddoMischerMain.getCardReaders().remove(reader)); // Remove old
                    BoardCardReader reader = new BoardCardReader(readerId);
                    BeddoMischerMain.getCardReaders().add(reader);
                }
            }
        }
        System.out.println(BeddoMischerMain.getCardReaders());
    }
}
