package view;

import controller.TutorialController;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Tutorial view class 

 * @author: Juan P. Sanin

 * @version: 1.0

 */


public class TutorialView {

	private PImage background, clouds;
	private PImage continueButtonSmall, continueButtonBig;
	private int cloudsX1, cloudsX2;
	private TutorialController tc;
	private PApplet app;


	/** 
	 * 
	 *	Constructor method for Tutorial View <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates the visualization of the tutorial screen<br>
	 * @param app, PApplet processing core
	 */
	public  TutorialView(PApplet app) {
		this.app = app;
		background= app.loadImage("../images/BackGround.png");
		clouds=app.loadImage("../images/Clouds.png");
		continueButtonSmall= app.loadImage("../images/ContinueButtonSmall.png");
		continueButtonBig= app.loadImage("../images/ContinueButton.png");
		cloudsX1=0;
		cloudsX2=1600;
		tc= new TutorialController(app);
	}

	/** 
	 * 
	 *	Method for drawing the tutorial Screen<br>
		<b> pre: </b> <br>
		<b> post: </b>Draws the tutorial screen<br>
	 */
	public void drawScreen() {

		app.image(background, 0, 0);

		if(cloudsX1>-1600) {
			cloudsX1--;
		}else {
			cloudsX1=1600;
		}

		if(cloudsX2>-1600) {
			cloudsX2--;
		}else {
			cloudsX2=1600;
		}

		app.image(clouds, cloudsX1, 0);
		app.image(clouds, cloudsX2, 0);
		app.image(continueButtonSmall, 520, 525);

		if(app.mouseX>520 && app.mouseX<745 && app.mouseY>525 && app.mouseY<580) {
			app.image(continueButtonBig, 505,520);
		}
		
		tc.drawDerrick();

	}
	
	

	public void moveDerrick(char key) {
		tc.getLogic().getDerrick().setKey(key);
		new Thread(tc.getLogic().getDerrick()).start();
	}
	
	
	/** 
	 * 
	 *	Method for changing screens<br>
		<b> pre: </b> <br>
		<b> post: </b>Changes screens depending on the click<br>
		@return screen, the screen where the program should go
	 */
	public int changeScreen() {
		int screen=3;
		if(app.mouseX>520 && app.mouseX<745 && app.mouseY>525 && app.mouseY<580) {
			screen=1;
		}
		return screen;
	}

}

