package view;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Register view class 

 * @author: Juan P. Sanin

 * @version: 1.0

 */


public class GameView {


	private PImage background, clouds, mapImage;
	private int cloudsX1, cloudsX2;
	private PImage infoOverlay;
	private PApplet app;


	/** 
	 * 
	 *	Constructor method for Register View <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates the visualization of the register screen<br>
	 * @param app, PApplet processing core
	 */
	public  GameView(PApplet app) {
		this.app = app;
		
		background= app.loadImage("../images/soloBackground.png");
		clouds=app.loadImage("../images/Clouds.png");
		infoOverlay=app.loadImage("../images/infoOverlay.png");
		mapImage=app.loadImage("../images/map.png");
		cloudsX1=0;
		cloudsX2=1600;

	
	}

	/** 
	 * 
	 *	Method for drawing the info Screen<br>
		<b> pre: </b> <br>
		<b> post: </b>Draws the info screen<br>
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

		app.image(clouds, cloudsX1, 50);
		app.image(clouds, cloudsX2, 50);
		app.image(mapImage, 0, 0);
		
		app.image(infoOverlay, 0, 0);
	}
	

	
	/** 
	 * 
	 *	Method for changing screens<br>
		<b> pre: </b> <br>
		<b> post: </b>Changes screens depending on the click<br>
		@return screen, the screen where the program should go
	 */
	public int changeScreen() {
		int screen=5;
		
			screen=6;
		
		return screen;
	}
}