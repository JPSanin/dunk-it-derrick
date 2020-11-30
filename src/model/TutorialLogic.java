package model;

import processing.core.PApplet;

public class TutorialLogic {
	
	private Derrick derrick;
	
	public TutorialLogic(PApplet app) {
		derrick= new Derrick(350,450,app);
	}
	
	
	public void drawDerrick() {
		derrick.draw();
	}


	public Derrick getDerrick() {
		return derrick;
	}
	
	

}
