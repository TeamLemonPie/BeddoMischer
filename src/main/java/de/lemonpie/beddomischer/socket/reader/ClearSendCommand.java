package de.lemonpie.beddomischer.socket.reader;

import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.socket.CommandData;

public class ClearSendCommand extends CommandData
{
	public ClearSendCommand(int key)
	{
		super(Scope.READER, CommandName.CLEAR, key, null);
	}
}
