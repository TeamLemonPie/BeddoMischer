package de.lemonpie.beddomischer.model;

import de.lemonpie.beddomischer.listener.PlayerListListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class PlayerList extends ArrayList<Player> {

    private List<PlayerListListener> listeners;

    public PlayerList() {
        this.listeners = new LinkedList<>();
    }

    @Override
    public boolean add(Player player) {
        fireListener(l -> l.addPlayer(player));
        return super.add(player);
    }

    public void addListener(PlayerListListener playerListener) {
        this.listeners.add(playerListener);
    }

    public void removeListener(PlayerListListener playerListener) {
        this.listeners.remove(playerListener);
    }

    private void fireListener(Consumer<PlayerListListener> consumer) {
        for (PlayerListListener playerListener : listeners) {
            consumer.accept(playerListener);
        }
    }
}
