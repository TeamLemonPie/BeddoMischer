package de.lemonpie.beddomischer.socket.admin.command.send;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.socket.CommandData;

public class ReaderCountSendCommand extends CommandData
{

	public enum Type
	{
		ADD, REMOVE
	}

	public ReaderCountSendCommand(Type type)
	{
		super(Scope.ADMIN, CommandName.READER_COUNT, -1, new JsonPrimitive(type.ordinal()));
	}
}
