package de.lemonpie.beddomischer.storage;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.listener.PlayerListener;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.PlayerState;
import de.lemonpie.beddomischer.model.card.Card;
import logger.Logger;

import java.sql.SQLException;

public class StoragePlayerListener implements PlayerListener {

    @Override
	public void nameDidChange(Player player, String name) {
		try {
            BeddoMischerMain.getPlayerDao().update(player);
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    @Override
	public void twitchNameDidChange(Player player, String twitchName) {
		try {
            BeddoMischerMain.getPlayerDao().update(player);
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    @Override
	public void stateDidChange(Player player, PlayerState state) {
		try {
            BeddoMischerMain.getPlayerDao().update(player);
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

	@Override
	public void readerIdDidChange(Player player, int readerId) {
		try {
			BeddoMischerMain.getPlayerDao().update(player);
		} catch (SQLException e) {
			Logger.error(e);
		}
	}

	@Override
	public void cardDidChangeAtIndex(Player player, int index, Card card) {
		try {
            BeddoMischerMain.getPlayerDao().update(player);
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    @Override
	public void chipsDidChange(Player player, int chips) {
		try {
            BeddoMischerMain.getPlayerDao().update(player);
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

	@Override
	public void winProbabilityDidChange(Player player, int value) {
	}
}
