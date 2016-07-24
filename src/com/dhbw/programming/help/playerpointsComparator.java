package com.dhbw.programming.help;

import java.util.Comparator;

import com.dhbw.programming.modell.Player;

public class playerpointsComparator implements Comparator<Player> {

	public int compare(Player o1, Player o2) {
		Integer player1 = o1.getTotalpoints();
		Integer player2 = o2.getTotalpoints();

		if (player1.compareTo(player2) == 0) {
			if (o1.getName().compareTo(o2.getName()) == 0) {
				return -1;
			} else
				return o1.getName().compareTo(o2.getName());
		} else
			return player2.compareTo(player1);
	}
}
