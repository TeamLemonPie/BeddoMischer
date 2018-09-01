package de.lemonpie.beddomischer;

import de.tobias.utils.settings.Key;

public class ServerSettings
{
	@Key("director.interface")
	public String directorInterface = "0.0.0.0";
	@Key("director.port")
	public int directorPort = 9996;

	@Key("reader.interface")
	public String readerInterface = "0.0.0.0";
	@Key("reader.port")
	public int readerPort = 9997;

	@Key("control.interface")
	public String controlInterface = "0.0.0.0";
	@Key("control.port")
	public int controlPort = 9998;

	@Key("http.interface")
	public String httpInterface = "0.0.0.0";
	@Key("http.port")
	public int httpPort = 9999;

	public int numberOfSeats = 7;

}
