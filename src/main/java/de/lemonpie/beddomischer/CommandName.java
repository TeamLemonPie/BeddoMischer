package de.lemonpie.beddomischer;

public enum CommandName {

    CLEAR("clear"),
    CARD("card"),
    READER("reader"), // CONFIG

    PLAYER_NAME("name"),
    PLAYER_TWITCH("twitchName"),
    PLAYER_CHIP("chip"),
    PLAYER_OP("player-op");

    private String name;

    CommandName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
