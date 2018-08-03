package de.lemonpie.beddomischer.socket.admin.command.send;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.socket.CommandData;

public class PlayerWinProbabilitySendCommand extends CommandData
{

	public PlayerWinProbabilitySendCommand(Player player, int value)
	{
		super(Scope.ADMIN, CommandName.WIN_PROBABILITY, player.getId(), new JsonPrimitive(value));
	}
}
