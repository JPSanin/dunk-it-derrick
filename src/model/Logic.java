package model;

import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;

public class Logic {

	private Derrick derrick;

	private ArrayList<Player> players;
	private ArrayList<EnemyCat> cats;
	private DateComparator dc;
	private NicknameComparator nc;
	private TimeComparator tc;

	private PApplet app;

	private static Logic onlyInstance;


	public Logic(PApplet app) {
		this.app=app;
		derrick= new Derrick(0,450,app);
		players= new ArrayList<>();
		cats= new ArrayList<>();
		cats.add(new EnemyCat(150,200,50,250,app));
		cats.add(new EnemyCat(650,450,500,750,app));
		cats.add(new EnemyCat(1100,450,1050,1350,app));
		cats.add(new EnemyCat(1750,450,1650,2100,app));
		cats.add(new EnemyCat(2250,450,2150,2400,app));
		cats.add(new EnemyCat(2350,450,2150,2400,app));
		cats.add(new EnemyCat(2300,450,2150,2400,app));
		dc= new DateComparator();
		nc= new NicknameComparator();
		tc=new TimeComparator();
	}


	public static Logic getInstance(PApplet app) {
		if(onlyInstance == null) {
			onlyInstance = new Logic(app);
		}
		return onlyInstance;
	}

	public void drawCats() {
		for (int i = 0; i < cats.size(); i++) {
			cats.get(i).draw();
		}
	}

	
	public void moveCats() {
		for (int i = 0; i < cats.size(); i++) {
			new Thread(cats.get(i)).start();
		}
	}
	
	public void setCatsPositions(int mapX) {
		for (int i = 0; i < cats.size(); i++) {
			cats.get(i).setPostitions(mapX);
		}
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
			Collections.sort(players,nc);
			break;
		case 2:
			Collections.sort(players,dc);
			break;
		case 3:
			Collections.sort(players,tc);
			break;
		case 4:
			Collections.sort(players);
			break;
		}

	}
	
	public void checkHit() {
		for (int i = 0; i < cats.size(); i++) {
			if(derrick.checkHit(cats.get(i))) {
				cats.remove(i);
				derrick.setHealth(derrick.getHealth()-1);
			}
		}
	}

	public void checkFall() {
		derrick.checkFall();
	}

	public int getPlayersSize() {
		return players.size();
	}


	public Player getCurrentPlayer() {
		return players.get(players.size()-1);
	}


	


	





}
