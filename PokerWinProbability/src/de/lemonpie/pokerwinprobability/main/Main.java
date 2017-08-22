package de.lemonpie.pokerwinprobability.main;

import java.util.ArrayList;

import de.lemonpie.pokerwinprobability.logic.Board;
import de.lemonpie.pokerwinprobability.logic.Calculation;
import de.lemonpie.pokerwinprobability.logic.Card;
import de.lemonpie.pokerwinprobability.logic.Player;

public class Main
{
	public static void main(String[] args)
	{
		ArrayList<Player> players = new ArrayList<>();
		
		Player player1 = new Player();
		player1.setName("1");
		player1.setCardLeft(new Card("PD"));
		player1.setCardRight(new Card("C8"));
		players.add(player1);

		Player player2 = new Player();
		player2.setName("2");
		player2.setCardLeft(new Card("H3"));
		player2.setCardRight(new Card("H2"));
		players.add(player2);
		
		Player player3 = new Player();
		player3.setName("3");
		player3.setCardLeft(new Card("K8"));
		player3.setCardRight(new Card("H8"));
		players.add(player3);
		
		Board board = new Board();
		board.setCard(new Card("K5"), 0);
		board.setCard(new Card("KD"), 1);
		board.setCard(new Card("HD"), 2);
		board.setCard(new Card("K6"), 3);
		
		Calculation calculation = new Calculation(players, board);
		ArrayList<Double> probabilities = calculation.calculate(10000);		
		ArrayList<Integer> percentages = new ArrayList<>();
		
		for(int i = 0; i < players.size(); i++)
		{
			percentages.add((int)(probabilities.get(i) * 100));
		}
		
		//DEBUG print
		for(int i = 0; i < players.size(); i++)
		{
			System.out.println(players.get(i).getName() + ": " + probabilities.get(i) + " --> " + percentages.get(i) + "%");
		}
	}	
}