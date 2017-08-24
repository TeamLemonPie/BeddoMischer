package de.lemonpie.beddomischer.socket.admin.command.send;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.socket.CommandData;

public class PlayerOpSendCommand extends CommandData {

    public PlayerOpSendCommand(int playerId) {
        super("admin", "player-op", playerId, new JsonPrimitive("add"));
    }
}
