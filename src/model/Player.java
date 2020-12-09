package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class Player extends Thread implements Comparable<Player> {
	
	private String nickname;
	private LocalDateTime date;
	private String dateString;
	private int score;
	private int gameTime;
	private PFont font;
	private PApplet app;
	
	
	public Player(String nickname, PApplet app) {
		this.nickname = nickname;
		this.app=app;
		date=LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		dateString= date.format(formatter); 
		score=1;
		gameTime=0;
		font= app.createFont("../fonts/Minecraft.ttf", 16);
	}
	
	public void draw(int posy) {
		if(posy>274 && posy<480) {
			app.textFont(font);
			app.textAlign(PConstants.CENTER);
			app.text(nickname,103,posy);
			app.text(dateString,301,posy);
			app.text(gameTime+ " s",499,posy);
			app.text(score,697,posy);
		}
		
	}
	
	
	public void run() {
		calculateScore();
	}
	
	private void calculateScore() {
		score= score*gameTime;
	}

	@Override
	public int compareTo(Player p1) {
		
		return score-p1.getScore();
	}

	
	

	public LocalDateTime getDate() {
		return date;
	}

	public String getNickname() {
		return nickname;
	}



	public String getDateString() {
		return dateString;
	}


	public int getScore() {
		return score;
	}

	

	public void setScore(int score) {
		this.score = score;
	}


	
	public int getGameTime() {
		return gameTime;
	}

	public void setGameTime(int gameTime) {
		this.gameTime = gameTime;
	}



	
	
	

}
