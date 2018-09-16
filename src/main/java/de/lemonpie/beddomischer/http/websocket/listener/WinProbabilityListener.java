package de.lemonpie.beddomischer.http.websocket.listener;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.http.websocket.CallbackCommand;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.listener.BoardListener;
import de.lemonpie.beddomischer.listener.PlayerListener;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.player.Player;
import de.lemonpie.beddomischer.model.player.PlayerList;
import de.lemonpie.beddomischer.model.player.PlayerState;
import de.lemonpie.beddomischer.model.winprobability.Calculation;

import java.util.ArrayList;
import java.util.List;

public class WinProbabilityListener implements PlayerListener, BoardListener
{

	private WebSocketHandler webSocketHandler;

	public WinProbabilityListener(WebSocketHandler webSocketHandler)
	{
		this.webSocketHandler = webSocketHandler;
	}

	@Override
	public void nameDidChange(Player player, String name)
	{
	}

	@Override
	public void twitchNameDidChange(Player player, String twitchName)
	{
	}

	@Override
	public void stateDidChange(Player player, PlayerState state)
	{
		computeWinProbability();
	}

	@Override
	public void cardDidChangeAtIndex(Player player, int index, Card card)
	{
		computeWinProbability();
	}

	@Override
	public void cardDidChangeAtIndex(int index, Card card)
	{
		computeWinProbability();
	}

	private boolean lastComputed = false;

	private void computeWinProbability()
	{
		// Check if cards complete
		if(isPlayerSetComplete())
		{
			ArrayList<Player> playerList = new ArrayList<>();
			for(Player player : BeddoMischerMain.getPlayers())
			{
				player.setWinProbability(0);
				if(player.getPlayerState() == PlayerState.ACTIVE)
				{
					playerList.add(player);
				}
			}

			if(playerList.isEmpty())
			{
				return;
			}

			Board board = BeddoMischerMain.getBoard();
			Calculation calculation = new Calculation(playerList, board);
			List<Double> probabilities = calculation.calculate(1000);

			for(int i = 0; i < playerList.size(); i++)
			{
				Player player = playerList.get(i);
				player.setWinProbability((int) ((probabilities.get(i) * 100)));
			}
			lastComputed = true;
		}
		else
		{
			if(lastComputed)
			{
				// Clear win probability on new round
				PlayerList playerList = BeddoMischerMain.getPlayers();
				for(Player player : playerList)
				{
					player.setWinProbability(0);
				}
				lastComputed = false;
			}
		}
	}

	private boolean isPlayerSetComplete()
	{
		boolean complete = true;
		for(Player player : BeddoMischerMain.getPlayers())
		{
			if(player.getPlayerState() == PlayerState.ACTIVE)
			{
				if(player.getCardLeft() == Card.EMPTY || player.getCardRight() == Card.EMPTY)
				{
					complete = false;
				}
			}
		}
		return complete;
	}

	@Override
	public void winProbabilityDidChange(Player player, int value)
	{
		CallbackCommand callbackCommand = new CallbackCommand(Scope.PLAYER, CommandName.WIN_PROBABILITY, player.getId(),
				new JsonPrimitive(value));
		webSocketHandler.sendCommand(callbackCommand);
	}

	@Override
	public void isHighlightedDidChange(Player player, boolean value)
	{
	}

	@Override
	public void manageCardIndexDidChange(Player player, int value)
	{
	}

	@Override
	public void readerListDidChange()
	{
	}

	@Override
	public void chipsDidChange(Player player, int chips)
	{
	}

	@Override
	public void smallBlindDidChange(int newValue)
	{
	}

	@Override
	public void bigBlindDidChange(int newValue)
	{
	}

	@Override
	public void anteDidChange(int newValue)
	{
	}
}
