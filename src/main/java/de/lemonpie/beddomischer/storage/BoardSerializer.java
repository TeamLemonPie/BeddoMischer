package de.lemonpie.beddomischer.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.lemonpie.beddomischer.model.Board;
import logger.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BoardSerializer
{

	private static final String fileName = "board.json";

	public static synchronized void saveBoard(Board board)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		final Path path = Paths.get(fileName);

		try
		{
			final String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(board);
			Files.write(path, s.getBytes(), StandardOpenOption.CREATE);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public static Board loadBoard()
	{
		ObjectMapper objectMapper = new ObjectMapper();
		final Path path = Paths.get(fileName);

		Board board = new Board();
		if(Files.exists(path))
		{
			try
			{
				board = objectMapper.readValue(Files.newBufferedReader(path), Board.class);
			}
			catch(IOException e)
			{
				Logger.error(e);
			}
		}
		return board;
	}
}
