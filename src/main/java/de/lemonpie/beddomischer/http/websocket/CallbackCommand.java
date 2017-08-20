package de.lemonpie.beddomischer.http.websocket;

import com.google.gson.JsonElement;

public class CallbackCommand {

	private String scope;
	private String command;
	private int key;
	private JsonElement value;

	public CallbackCommand(String scope, String command, int key, JsonElement value) {
		this.scope = scope;
		this.command = command;
		this.key = key;
		this.value = value;
	}

	public String getScope() {
		return scope;
	}

	public String getCommand() {
		return command;
	}

	public int getKey() {
		return key;
	}

	public JsonElement getValue() {
		return value;
	}
}
