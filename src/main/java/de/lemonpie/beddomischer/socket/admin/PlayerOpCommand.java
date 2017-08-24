package de.lemonpie.beddomischer.socket.admin;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class PlayerOpCommand implements Command {
    @Override
    public String name() {
        return "player-op";
    }

    @Override
    public void execute(CommandData command) {
        String op = command.getValue().getAsString();
        if (op.equals("add")) {
            BeddoMischerMain.addPlayer();
        } else if (op.equals("remove")) {
            int playerId = command.getKey();
            BeddoMischerMain.getPlayer(playerId).ifPresent(player -> {
                BeddoMischerMain.getPlayers().remove(player);
            });
        }
    }
}
