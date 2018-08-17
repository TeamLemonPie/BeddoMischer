package de.lemonpie.beddomischer.network.director.command.read;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.http.websocket.CallbackCommand;

public class LowerThirdStartReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.LOWER_THIRD_START;
	}

	@Override
	public void execute(CommandData command)
	{
		BeddoMischerMain.getWebSocketHandler().sendCommand(new CallbackCommand(Scope.DIRECTOR, CommandName.LOWER_THIRD_START, command.getKey(), null));
	}
}
