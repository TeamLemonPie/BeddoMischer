package de.lemonpie.beddomischer.network.admin;

import de.lemonpie.beddocommon.model.ObservableListListener;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.player.Player;
import de.lemonpie.beddomischer.network.admin.command.send.PlayerOpSendCommand;

public class AdminPlayerListListener implements ObservableListListener<Player>
{
	@Override
	public void addObjectToList(Player player)
	{
		BeddoMischerMain.getControlServerSocket().writeAll(new PlayerOpSendCommand(player.getId()));

		AdminPlayerListener listener = new AdminPlayerListener();
		player.addListener(listener);
	}

	@Override
	public void removeObjectFromList(Player player)
	{
	}
}
