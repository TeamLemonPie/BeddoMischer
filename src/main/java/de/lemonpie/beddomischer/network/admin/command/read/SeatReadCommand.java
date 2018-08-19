package de.lemonpie.beddomischer.network.admin.command.read;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;

public class SeatReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.SEAT;
	}

	@Override
	public void execute(CommandData command)
	{
		//TODO: remove old player
		BeddoMischerMain.getSeatList().getObject(command.getKey()).ifPresent(seat -> seat.setPlayerId(command.getValue().getAsInt()));
	}
}
