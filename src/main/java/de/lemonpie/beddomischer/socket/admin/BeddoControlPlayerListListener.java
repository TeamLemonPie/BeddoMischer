package de.lemonpie.beddomischer.socket.admin;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.listener.PlayerListListener;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.socket.admin.command.send.PlayerOpSendCommand;

public class BeddoControlPlayerListListener implements PlayerListListener
{
	@Override
	public void addPlayer(Player player)
	{
		BeddoMischerMain.getControlServerSocket().writeAll(new PlayerOpSendCommand(player.getId()));

		AdminPlayerListener listener = new AdminPlayerListener();
		player.addListener(listener);
	}

	@Override
	public void removePlayer(Player player)
	{
	}
}
