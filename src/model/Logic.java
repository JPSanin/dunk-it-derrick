package model;

import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;

public class Logic {
	
	private Derrick derrick;
	
	private ArrayList<Player> players;
	
	private PApplet app;
	
	private static Logic onlyInstance;
	
	
	public Logic(PApplet app) {
		this.app=app;
		derrick= new Derrick(0,450,app);
		players= new ArrayList<>();
	}
	
	
	public static Logic getInstance(PApplet app) {
		if(onlyInstance == null) {
			onlyInstance = new Logic(app);
		}
		return onlyInstance;
	}
	
	
	
	public void drawDerrick() {
		derrick.draw();
	}

	
	
	public void blocker(int mapX) {
		derrick.blocker(mapX);
		
	}
	
	public void floorSetter(int mapX) {
		derrick.floorSetter(mapX);
	}




	public void heightBlocker(int mapX) {
		derrick.heightBlocker(mapX);
		
	}

	public Derrick getDerrick() {
		return derrick;
	}


	public void addPlayer(String nickname) {
		Player p= new Player(nickname,app);
		players.add(p);
		
	}

	public void drawPlayers(int playersY) {
		for (int i = 0; i < players.size(); i++) {
			players.get(i).draw(playersY+(i*25));
		}
		
	}
	
	public void sortScores(int sort) {
		switch (sort) {
		case 1:
			Collections.sort(players);
			break;
		case 2:
			
			break;
		
		}
		
	}


	public int getPlayersSize() {
		return players.size();
	}



	

}
