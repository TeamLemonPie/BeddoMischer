package de.lemonpie.beddomischer;

import de.tobias.utils.settings.Key;

public class ServerSettings
{
	@Key("reader.interface")
	public String readerInterface = "localhost";
	@Key("reader.port")
	public int readerPort = 9997;

	@Key("control.interface")
	public String controlInterface = "localhost";
	@Key("control.port")
	public int controlPort = 9998;

	@Key("http.interface")
	public String httpInterface = "localhost";
	@Key("http.port")
	public int httpPort = 9999;

}
