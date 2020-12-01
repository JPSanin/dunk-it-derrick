package model;

import processing.core.PApplet;

public class Logic {
	
	private Derrick derrick;
	
	
	public Logic(PApplet app) {
		derrick= new Derrick(0,450,app);
	}
	
	
	
	
	public void drawDerrick() {
		derrick.draw();
	}

	
	
	public void blocker(int mapX) {
		derrick.blocker(mapX);
		
	}
	

	

	public Derrick getDerrick() {
		return derrick;
	}




	public void floorSetter(int mapX) {
		derrick.floorSetter(mapX);
	}




	public void heightBlocker(int mapX) {
		derrick.heightBlocker(mapX);
		
	}




	

}
