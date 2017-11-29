package de.lemonpie.beddomischer.socket.admin.command.read;

import com.google.gson.JsonObject;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

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

        // Remove old reader id
		removeOldReaderMapping(readerId);

        if (command.getValue() instanceof JsonObject) {
            JsonObject value = (JsonObject) command.getValue();
            int type = value.get("type").getAsInt();

            if (type == 0) { // PLAYER
				int playerId = value.get("playerId").getAsInt();
				BeddoMischerMain.getPlayers().getPlayer(playerId).ifPresent(player -> {
					player.setReaderId(readerId);
				});
            } else if (type == 1) { // BOARD
                int oldReaderId = value.get("oldReaderId").getAsInt();
                BeddoMischerMain.getBoard().removeReaderId(oldReaderId);
				BeddoMischerMain.getBoard().addReaderId(readerId);
            }
        }
    }

    private void removeOldReaderMapping(int readerId) {
		BeddoMischerMain.getPlayers().forEach(player -> {
			if (player.getReaderId() == readerId) {
				player.setReaderId(BeddoMischerMain.READER_NULL_ID);
			}
		});

		BeddoMischerMain.getBoard().removeReaderId(readerId);
	}
}
