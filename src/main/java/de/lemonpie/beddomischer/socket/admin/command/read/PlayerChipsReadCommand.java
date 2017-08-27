package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class PlayerChipsReadCommand implements Command {

    @Override
    public String name() {
        return "chips";
    }

    @Override
    public void execute(CommandData command) {
        int playerId = command.getKey();
        int chips = command.getValue().getAsInt();

        BeddoMischerMain.getPlayers().getPlayer(playerId).ifPresent(p -> p.setChips(chips));
    }
}
