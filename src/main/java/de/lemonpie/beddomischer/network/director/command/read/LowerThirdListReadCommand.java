package de.lemonpie.beddomischer.network.director.command.read;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.network.director.command.send.LowerThirdListSendCommand;

public class LowerThirdListReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.LOWER_THIRD_LIST;
	}

	@Override
	public void execute(CommandData command)
	{
		BeddoMischerMain.getDirectorServerSocket().writeAll(new LowerThirdListSendCommand());
	}
}
