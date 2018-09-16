package de.lemonpie.beddomischer.http.websocket.command;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.network.director.command.send.LowerThirdFailedSendCommand;

public class LowerThirdFailedReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.LOWER_THIRD_FAILED;
	}

	@Override
	public void execute(CommandData command)
	{
		BeddoMischerMain.getDirectorServerSocket().writeAll(new LowerThirdFailedSendCommand(command.getKey()));
	}
}
