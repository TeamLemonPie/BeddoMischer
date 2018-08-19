package de.lemonpie.beddomischer.network.admin.command.read;

import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import org.junit.runner.RunWith;

@RunWith(BeddoMischerTestRunner.class)
public class ReaderReadCommandTest
{
//	TODO Fix compiler
/*
	@Test
	public void setOnePlayerReaderShouldReturnNormal()
	{
		Player player = BeddoMischerMain.getPlayers().add();

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 0); // Player Type
		jsonObject.addProperty("playerId", player.getId());
		CommandData data = new CommandData(Scope.ADMIN, CommandName.READER, 3, jsonObject); // Key = Reader ID

		CommandExecutor.getInstance().execute(data);

		assertThat(player.getReaderId()).isEqualTo(3);
	}

	@Test
	public void setOnePlayerReaderWithPreviousPlayerReaderShouldReturnNormal()
	{
		Player player = BeddoMischerMain.getPlayers().add();
		player.setReaderId(2);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 0); // Player Type
		jsonObject.addProperty("playerId", player.getId());
		CommandData data = new CommandData(Scope.ADMIN, CommandName.READER, 3, jsonObject); // Key = Reader ID

		CommandExecutor.getInstance().execute(data);

		assertThat(player.getReaderId()).isEqualTo(3);
	}

	@Test
	public void setOnePlayerReaderWithPreviousBoardReaderShouldReturnNormal()
	{
		Player player = BeddoMischerMain.getPlayers().add();
		BeddoMischerMain.getBoard().addReaderId(3);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 0); // Player Type
		jsonObject.addProperty("playerId", player.getId());
		CommandData data = new CommandData(Scope.ADMIN, CommandName.READER, 3, jsonObject); // Key = Reader ID

		CommandExecutor.getInstance().execute(data);

		assertThat(BeddoMischerMain.getBoard().getReaderIds().contains(3)).isFalse();
		assertThat(player.getReaderId()).isEqualTo(3);
	}

	@Test
	public void setOneBoardReaderShouldReturnNormal()
	{
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 1); // Board Type
		jsonObject.addProperty("oldReaderId", -3);

		CommandData data = new CommandData(Scope.ADMIN, CommandName.READER, 3, jsonObject); // Key = Reader ID

		CommandExecutor.getInstance().execute(data);

		assertThat(BeddoMischerMain.getBoard().getReaderIds().contains(3)).isTrue();
	}

	@Test
	public void setOneBoardReaderWithPreviousPlayerReaderShouldReturnNormal()
	{
		Player player = BeddoMischerMain.getPlayers().add();
		player.setReaderId(3);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 1); // Board Type
		jsonObject.addProperty("oldReaderId", -3);

		CommandData data = new CommandData(Scope.ADMIN, CommandName.READER, 3, jsonObject); // Key = Reader ID

		CommandExecutor.getInstance().execute(data);

		assertThat(player.getReaderId()).isEqualTo(-3);
		assertThat(BeddoMischerMain.getBoard().getReaderIds().contains(3)).isTrue();
	}
	*/
}
