package de.lemonpie.beddomischer.network.admin.command.read;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.BlockOption;

public class BlockReadCommand implements Command
{

	@Override
	public CommandName name()
	{
		return CommandName.BLOCK;
	}

	@Override
	public void execute(CommandData command)
	{
		BlockOption option = BlockOption.values()[command.getKey()];
		BeddoMischerMain.setBlockOption(option);
	}
}
