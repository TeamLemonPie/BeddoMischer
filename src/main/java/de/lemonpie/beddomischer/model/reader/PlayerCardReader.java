package de.lemonpie.beddomischer.model.reader;

public class PlayerCardReader extends CardReader {

	private int playerId;

    public PlayerCardReader(int readerId, int playerId) {
        super(readerId);
		this.playerId = playerId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	@Override
	public String toString() {
		return "PlayerCardReader{" +
				"playerId=" + playerId +
				'}';
	}
}
