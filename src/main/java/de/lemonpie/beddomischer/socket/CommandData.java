package de.lemonpie.beddomischer.socket;

import com.google.gson.JsonElement;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;

public class CommandData
{

	private Scope scope;
	private String command;
	private int key;
	private JsonElement value;

	public CommandData(Scope scope, CommandName command, int key, JsonElement value)
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

	protected void setValue(JsonElement value)
	{
		this.value = value;
	}
}
