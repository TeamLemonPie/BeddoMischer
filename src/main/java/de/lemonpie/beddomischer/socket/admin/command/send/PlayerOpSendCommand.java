package de.lemonpie.beddomischer.socket.admin.command.send;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.socket.CommandData;

public class PlayerOpSendCommand extends CommandData
{

	public PlayerOpSendCommand(int playerId)
	{
		super(Scope.ADMIN, CommandName.PLAYER_OP, playerId, new JsonPrimitive("add"));
	}
}
