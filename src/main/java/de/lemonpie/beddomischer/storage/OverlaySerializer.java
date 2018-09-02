package de.lemonpie.beddomischer.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.Overlay;
import logger.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class OverlaySerializer
{
	private static final String fileName = BeddoMischerMain.BASE_PATH + "/Overlay.json";

	public static synchronized void saveOverlay(Overlay overlay)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		final Path path = Paths.get(fileName);

		try
		{
			final String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(overlay);
			Files.write(path, s.getBytes(), StandardOpenOption.CREATE);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public static Overlay loadOverlay()
	{
		ObjectMapper objectMapper = new ObjectMapper();
		final Path path = Paths.get(fileName);

		Overlay overlay = new Overlay();
		if(Files.exists(path))
		{
			try
			{
				overlay = objectMapper.readValue(Files.newBufferedReader(path), Overlay.class);
			}
			catch(IOException e)
			{
				Logger.error(e);
			}
		}
		return overlay;
	}
}
