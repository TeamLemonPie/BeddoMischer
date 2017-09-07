package de.lemonpie.beddomischer.socket.admin;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.listener.PlayerListener;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.socket.admin.command.send.CardSendCommand;

public class AdminPlayerListener implements PlayerListener {

    private Player player;

    public AdminPlayerListener(Player player) {
        this.player = player;
    }

    @Override
    public void nameDidChange(String name) {
    }

    @Override
    public void twitchNameDidChange(String twitchName) {
    }

    @Override
    public void hideDidChange(boolean hide) {
    }

    @Override
    public void cardDidChangeAtIndex(int index, Card card) {
        CardSendCommand cardSendCommand = new CardSendCommand(player.getId(), index, card);
        BeddoMischerMain.getControlServerSocket().writeAll(cardSendCommand);
    }

    @Override
    public void chipsDidChange(int chips) {
    }
}
