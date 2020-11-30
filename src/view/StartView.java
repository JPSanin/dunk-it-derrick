package view;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Start view class 

 * @author: Juan P. Sanin

 * @version: 1.0

 */


public class StartView {

	private PImage background, clouds, menuItems;
	private PImage infoButton, playButton;
	private int cloudsX1, cloudsX2;
	private PApplet app;


	/** 
	 * 
	 *	Constructor method for StartView <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates the visualization of the Start screen<br>
	 * @param app, PApplet processing core
	 */
	public  StartView(PApplet app) {
		this.app = app;
		background= app.loadImage("../images/HomeBackGround.png");
		clouds=app.loadImage("../images/Clouds.png");
		menuItems=app.loadImage("../images/MenuItems.png");
		infoButton= app.loadImage("../images/InfoButton.png");
		playButton= app.loadImage("../images/PlayButton.png");
		cloudsX1=0;
		cloudsX2=1600;
	}

	/** 
	 * 
	 *	Method for drawing the Start Screen<br>
		<b> pre: </b> <br>
		<b> post: </b>Draws the Start screen<br>
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
		app.image(menuItems, 0, 0);

		if(app.mouseX>142 && app.mouseX<370 && app.mouseY>410 && app.mouseY<470) {
			app.image(infoButton, 113, 404);
		}
		if(app.mouseX>430 && app.mouseX<658 && app.mouseY>410 && app.mouseY<470){
			app.image(playButton, 401, 404);
		}

	}
	
	/** 
	 * 
	 *	Method for changing screens<br>
		<b> pre: </b> <br>
		<b> post: </b>Changes screens depending on the click<br>
		@return screen, the screen where the program should go
	 */
	public int changeScreen() {
		int screen=1;
		if(app.mouseX>142 && app.mouseX<370 && app.mouseY>410 && app.mouseY<470) {
			screen=2;
		}
		if(app.mouseX>430 && app.mouseX<658 && app.mouseY>410 && app.mouseY<470){
			screen=3;
		}
		return screen;
	}


}

