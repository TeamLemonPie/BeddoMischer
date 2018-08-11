package de.lemonpie.beddomischer.socket.admin.command.send;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddocommon.network.server.CommandData;
import de.lemonpie.beddomischer.model.Player;

public class PlayerWinProbabilitySendCommand extends CommandData
{

	public PlayerWinProbabilitySendCommand(Player player, int value)
	{
		super(Scope.ADMIN, CommandName.WIN_PROBABILITY, player.getId(), new JsonPrimitive(value));
	}
}
