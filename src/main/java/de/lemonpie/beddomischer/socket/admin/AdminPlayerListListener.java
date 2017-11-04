package de.lemonpie.beddomischer.socket.admin;

import de.lemonpie.beddomischer.listener.PlayerListListener;
import de.lemonpie.beddomischer.model.Player;

public class AdminPlayerListListener implements PlayerListListener {
    @Override
    public void addPlayer(Player player) {
		AdminPlayerListener listener = new AdminPlayerListener();
		player.addListener(listener);
    }

    @Override
    public void removePlayer(Player player) {
    }
}
