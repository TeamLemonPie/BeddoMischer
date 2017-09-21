package de.lemonpie.beddomischer.model.reader;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import de.lemonpie.beddomischer.listener.PlayerCardReaderListener;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@DatabaseTable
public class PlayerCardReader extends CardReader {

	private List<PlayerCardReaderListener> listeners;

	@DatabaseField
	private int playerId;

	public PlayerCardReader() {
		this.listeners = new LinkedList<>();
	}

	public PlayerCardReader(int readerId, int playerId) {
		super(readerId);
		this.listeners = new LinkedList<>();
		this.playerId = playerId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
		fireListener(l -> l.playerIdDidChange(this, playerId));
	}

	public void addListener(PlayerCardReaderListener playerListener) {
		this.listeners.add(playerListener);
	}

	public void removeListener(PlayerCardReaderListener playerListener) {
		this.listeners.remove(playerListener);
	}

	private void fireListener(Consumer<PlayerCardReaderListener> consumer) {
		for (PlayerCardReaderListener playerListener : listeners) {
			consumer.accept(playerListener);
		}
	}


	@Override
	public String toString() {
		return "PlayerCardReader{" +
				"playerId=" + playerId +
				'}';
	}
}
