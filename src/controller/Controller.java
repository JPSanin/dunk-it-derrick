package controller;

import model.Logic;
import processing.core.PApplet;

public class Controller {
	private Logic logic;
	
	public Controller(PApplet app) {
		 logic= new Logic(app);
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
	
	
	
}
