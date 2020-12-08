package controller;

import model.Logic;
import processing.core.PApplet;

public class Controller {
	private Logic logic;
	
	public Controller(PApplet app) {
		 logic= Logic.getInstance(app);
	}
	
	
	public void drawDerrick() {
		logic.drawDerrick();
	}


	public Logic getLogic() {
		return logic;
	}


	public void blocker(int mapX) {
		logic.blocker(mapX);
		
	}


	public void floorSetter(int mapX) {
		logic.floorSetter(mapX);
		
	}


	public void heightBlocker(int mapX) {
		logic.heightBlocker(mapX);
		
	}


	public void addPlayer(String nickname) {
		logic.addPlayer(nickname);
		
	}


	public void drawPlayers(int playersY) {
		logic.drawPlayers(playersY);
		
	}


	public int getPlayersSize() {
		return logic.getPlayersSize();
	}


	public void sort(int sort) {
		logic.sortScores(sort);
		
	}


	public void drawCats() {
		logic.drawCats();
		
	}


	public void moveCats() {
		logic.moveCats();
	}


	public void setCatsPositions(int mapX) {
		logic.setCatsPositions(mapX);
		
	}
	
	
	
}
