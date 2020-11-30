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


	public Derrick getDerrick() {
		return derrick;
	}

}
