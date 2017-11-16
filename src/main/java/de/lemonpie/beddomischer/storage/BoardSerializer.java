package de.lemonpie.beddomischer.storage;

import com.google.gson.Gson;
import de.lemonpie.beddomischer.model.Board;
import logger.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BoardSerializer {

	private static final String fileName = "board.json";

	public static void saveBoard(Board board) {
		Gson gson = new Gson();
		final Path path = Paths.get(fileName);

		final String s = gson.toJson(board);
		try {
			Files.write(path, s.getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			Logger.error(e);
		}
	}

	public static Board loadBoard() {
		Gson gson = new Gson();
		final Path path = Paths.get(fileName);

		Board board = new Board();
		if (Files.exists(path)) {
			try {
				board = gson.fromJson(Files.newBufferedReader(path), Board.class);
			} catch (IOException e) {
				Logger.error(e);
			}
		}
		return board;
	}
}
