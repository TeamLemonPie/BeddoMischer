package de.lemonpie.beddomischer.storage;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.listener.PlayerListener;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.PlayerState;
import de.lemonpie.beddomischer.model.card.Card;

import java.sql.SQLException;

public class StoragePlayerListener implements PlayerListener {

    private Player player;

    public StoragePlayerListener(Player player) {
        this.player = player;
    }

    @Override
    public void nameDidChange(String name) {
        try {
            BeddoMischerMain.getPlayerDao().update(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void twitchNameDidChange(String twitchName) {
        try {
            BeddoMischerMain.getPlayerDao().update(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
	public void stateDidChange(PlayerState state) {
		try {
            BeddoMischerMain.getPlayerDao().update(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cardDidChangeAtIndex(int index, Card card) {
        try {
            BeddoMischerMain.getPlayerDao().update(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void chipsDidChange(int chips) {
        try {
            BeddoMischerMain.getPlayerDao().update(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
