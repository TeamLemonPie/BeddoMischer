package de.lemonpie.beddomischer.network.director.command.read;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.utils.OverlayHideOption;
import de.lemonpie.beddomischer.BeddoMischerMain;

public class OverlayHideReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.OVERLAY_HIDE;
	}

	@Override
	public void execute(CommandData command)
	{
		final OverlayHideOption hideOption = OverlayHideOption.values()[command.getKey()];

		if(hideOption == OverlayHideOption.ALL || hideOption == OverlayHideOption.BOARD)
		{
			BeddoMischerMain.getOverlay().setHideBoard(command.getValue().getAsBoolean());
		}

		if(hideOption == OverlayHideOption.ALL || hideOption == OverlayHideOption.PLAYER)
		{
			BeddoMischerMain.getOverlay().setHidePlayer(command.getValue().getAsBoolean());
		}
	}
}
