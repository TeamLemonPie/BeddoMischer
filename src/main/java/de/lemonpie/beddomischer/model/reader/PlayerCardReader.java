package de.lemonpie.beddomischer.model.reader;

public class PlayerCardReader extends CardReader {

	private int playerId;
	private int index;

	public PlayerCardReader(int readerId, int playerId ,int index) {
		super(readerId);
		this.playerId = playerId;
		this.index = index;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "PlayerCardReader{" +
				"playerId=" + playerId +
				", index=" + index +
				'}';
	}
}
