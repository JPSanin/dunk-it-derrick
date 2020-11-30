package model;

import processing.core.PApplet;

public class TutorialLogic {
	
	private TutorialDerrick derrick;
	
	public TutorialLogic(PApplet app) {
		derrick= new TutorialDerrick(350,450,app);
	}
	
	
	public void drawDerrick() {
		derrick.draw();
	}


	public TutorialDerrick getDerrick() {
		return derrick;
	}
	
	

}
