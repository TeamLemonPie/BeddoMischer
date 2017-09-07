package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class PlayerHideReadCommand implements Command {

    @Override
    public CommandName name() {
        return CommandName.PLAYER_HIDE;
    }

    @Override
    public void execute(CommandData command) {
        int playerId = command.getKey();
        boolean hide = command.getValue().getAsBoolean();

        BeddoMischerMain.getPlayers().getPlayer(playerId).ifPresent(p -> p.setHide(hide));
    }
}
