package controller;

import model.TutorialLogic;
import processing.core.PApplet;

public class TutorialController {
	
	private TutorialLogic tl;

	public TutorialController(PApplet app) {
		 tl= new TutorialLogic(app);
	}
	
	
	public void drawDerrick() {
		tl.drawDerrick();
	}


	public TutorialLogic getLogic() {
		return tl;
	}
	
	
	

}
