package de.lemonpie.beddomischer.network.director.command.read;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddocommon.utils.OverlayHideOption;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.http.websocket.CallbackCommand;

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

		final CallbackCommand callbackBoard = new CallbackCommand(Scope.BOARD, CommandName.OVERLAY_HIDE, 0, command.getValue());
		final CallbackCommand callbackPlayer = new CallbackCommand(Scope.PLAYER, CommandName.OVERLAY_HIDE, 0, command.getValue());

		if(hideOption == OverlayHideOption.ALL || hideOption == OverlayHideOption.BOARD)
		{
			BeddoMischerMain.getWebSocketHandler().sendCommand(callbackBoard);
		}

		if(hideOption == OverlayHideOption.ALL || hideOption == OverlayHideOption.PLAYER)
		{
			BeddoMischerMain.getWebSocketHandler().sendCommand(callbackPlayer);
		}
	}
}
