package de.lemonpie.beddomischer.storage;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.listener.PlayerListListener;
import de.lemonpie.beddomischer.model.Player;

import java.sql.SQLException;

public class StoragePlayerListListener implements PlayerListListener {
    @Override
    public void addPlayer(Player player) {
        try {
            player.addListener(new StoragePlayerListener(player));
            if (BeddoMischerMain.getPlayerDao().queryForId(player.getId()) == null) {
                BeddoMischerMain.getPlayerDao().create(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removePlayer(Player player) {
        try {
            BeddoMischerMain.getPlayerDao().delete(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
