package view;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Info view class 

 * @author: Juan P. Sanin

 * @version: 1.0

 */


public class InfoView {

	private PImage background, clouds, infoItems;
	private PImage backButton, movementButton;
	private int cloudsX1, cloudsX2;
	private PApplet app;


	/** 
	 * 
	 *	Constructor method for InfoView <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates the visualization of the info screen<br>
	 * @param app, PApplet processing core
	 */
	public  InfoView(PApplet app) {
		this.app = app;
		background= app.loadImage("../images/HomeBackGround.png");
		clouds=app.loadImage("../images/Clouds.png");
		infoItems= app.loadImage("../images/InfoItems.png");
		backButton= app.loadImage("../images/BackButton.png");
		movementButton= app.loadImage("../images/MovementButton.png");
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

		app.image(clouds, cloudsX1, 0);
		app.image(clouds, cloudsX2, 0);
		app.image(infoItems, 0,0);
		

		if(app.mouseX>107 && app.mouseX<337 && app.mouseY>516 && app.mouseY<575) {
			app.image(backButton, 81,509);
		}
		if(app.mouseX>462 && app.mouseX<692 && app.mouseY>516 && app.mouseY<575){
			app.image(movementButton, 428,509);
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
		int screen=2;
		if(app.mouseX>107 && app.mouseX<337 && app.mouseY>516 && app.mouseY<575) {
			screen=1;
		}
		if(app.mouseX>462 && app.mouseX<692 && app.mouseY>516 && app.mouseY<575){
			screen=3;
		}
		return screen;
	}
}