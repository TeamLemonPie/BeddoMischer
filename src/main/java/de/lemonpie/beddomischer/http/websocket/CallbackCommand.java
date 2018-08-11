package de.lemonpie.beddomischer.http.websocket;

import com.google.gson.JsonElement;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;

public class CallbackCommand
{

	private Scope scope;
	private String command;
	private int key;
	private JsonElement value;

	public CallbackCommand(Scope scope, CommandName command, int key, JsonElement value)
	{
		this.scope = scope;
		this.command = command.getName();
		this.key = key;
		this.value = value;
	}

	public Scope getScope()
	{
		return scope;
	}

	public String getCommand()
	{
		return command;
	}

	public int getKey()
	{
		return key;
	}

	public JsonElement getValue()
	{
		return value;
	}
}
