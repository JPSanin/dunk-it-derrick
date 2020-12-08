package model;

import java.util.Comparator;

public class DateComparator implements Comparator<Player> {

	@Override
	public int compare(Player p1, Player p2) {
		return p1.getDate().compareTo(p2.getDate());
	}

}
