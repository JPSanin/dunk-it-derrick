package model;

import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;

public class Logic {

	private Derrick derrick;

	private ArrayList<Player> players;
	private ArrayList<EnemyCat> cats;
	private ArrayList<PowerUp> powerUps;
	private int speedTime;
	private int invinciTime;

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
		powerUps= new ArrayList<>();
		powerUps.add(new SpeedShoes(55,210,app));
		powerUps.add(new HealthHeart(1250,450,app));
		powerUps.add(new SpecialBasketball(2050,450,app));
		speedTime=0;
		invinciTime=0;
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

	public void drawPowerUps() {
		for (int i = 0; i < powerUps.size(); i++) {
			powerUps.get(i).draw();
		}
	}


	public void setPowerUpsPositions(int mapX) {
		for (int i = 0; i < powerUps.size(); i++) {
			powerUps.get(i).setPostition(mapX);
		}	
	}

	public void checkConsume() {
		for (int i = 0; i < powerUps.size(); i++) {
			if(powerUps.get(i).consumed(derrick)) {
				if(powerUps.get(i) instanceof HealthHeart) {
					if(derrick.getHealth()<3) {
						derrick.setHealth(derrick.getHealth()+1);
					}
					getCurrentPlayer().setScore(getCurrentPlayer().getScore()+50);
				}

				if(powerUps.get(i) instanceof SpeedShoes) {
					speedTime=(int) app.millis()/1000;
					derrick.setStatus(1);
					getCurrentPlayer().setScore(getCurrentPlayer().getScore()+50);
				}

				if(powerUps.get(i) instanceof SpecialBasketball) {
					invinciTime=(int) app.millis()/1000;
					derrick.setStatus(2);
					getCurrentPlayer().setScore(getCurrentPlayer().getScore()+50);

				}
				powerUps.remove(i);
			}
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

	public void resetSpeed(int gameTime) {

		if(derrick.getStatus()==1) {
			int difTime=gameTime;
			speedTime+=difTime;
			if(speedTime>=5000) {
				derrick.setStatus(0);
			}

		}
		//System.out.println(powerTime+", "+ derrick.getStatus());
	}

	public void resetInvincibility(int gameTime) {

		if(derrick.getStatus()==2) {
			int difTime=gameTime;
			invinciTime+=difTime;
			if(invinciTime>=5000) {
				derrick.setStatus(0);
			}

		}
		System.out.println(invinciTime+", "+ derrick.getStatus());
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
