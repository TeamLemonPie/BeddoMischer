package de.lemonpie.beddomischer.network.admin.command.read.player;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;

public class PlayerManageCardReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.PLAYER_MANAGE_CARD;
	}

	@Override
	public void execute(CommandData command)
	{
		int playerId = command.getKey();
		int manageCardId = command.getValue().getAsInt();

		BeddoMischerMain.getPlayers().getObject(playerId).ifPresent(p -> p.setManageCardId(manageCardId));
	}
}
