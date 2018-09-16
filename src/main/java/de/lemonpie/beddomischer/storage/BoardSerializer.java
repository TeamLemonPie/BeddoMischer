package de.lemonpie.beddomischer.storage;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.Board;
import logger.Logger;

import java.sql.SQLException;
import java.util.List;

public class BoardSerializer
{
	public static synchronized void saveBoard(Board board)
	{
		try
		{
			BeddoMischerMain.getBoardDao().update(board);
		}
		catch(SQLException e)
		{
			Logger.error(e);
		}
	}

	public static Board loadBoard()
	{
		try
		{
			final List<Board> boards = BeddoMischerMain.getBoardDao().queryForAll();
			if(boards.isEmpty())
			{
				return createBoard();
			}
			return boards.get(0);
		}
		catch(SQLException e)
		{
			return createBoard();
		}
	}

	private static Board createBoard()
	{
		Board board = new Board();
		try
		{
			BeddoMischerMain.getBoardDao().create(board);
			return board;
		}
		catch(SQLException e1)
		{
			return null;
		}
	}
}
