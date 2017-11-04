package de.lemonpie.beddomischer;

public enum CommandName {

    CLEAR("clear"),
    CARD("card"),
	BOARD_CARD("board_card"),
	READER("reader"), // CONFIG

    PLAYER_NAME("name"),
    PLAYER_TWITCH("twitchName"),
	PLAYER_STATE("state"),
	PLAYER_CHIP("chip"),
    PLAYER_OP("player-op"),
    WINPROBABILITY("winprobability"),

	BLOCK("block"),

	DATA("data"),

	COUNTDOWN("countdown");

    private String name;

    CommandName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
