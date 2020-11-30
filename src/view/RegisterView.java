package view;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Register view class 

 * @author: Juan P. Sanin

 * @version: 1.0

 */


public class RegisterView {

	private PImage background, clouds, registerItems;
	private PImage continueButton;
	private int cloudsX1, cloudsX2;
	private PApplet app;


	/** 
	 * 
	 *	Constructor method for Register View <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates the visualization of the register screen<br>
	 * @param app, PApplet processing core
	 */
	public  RegisterView(PApplet app) {
		this.app = app;
		background= app.loadImage("../images/HomeBackGround.png");
		clouds=app.loadImage("../images/Clouds.png");
		registerItems= app.loadImage("../images/RegisterItems.png");
		continueButton= app.loadImage("../images/ContinueButton.png");
		cloudsX1=0;
		cloudsX2=1600;
	}

	/** 
	 * 
	 *	Method for drawing the register Screen<br>
		<b> pre: </b> <br>
		<b> post: </b>Draws the register screen<br>
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
		app.image(registerItems, 0,0);
		

		if(app.mouseX>286 && app.mouseX<513 && app.mouseY>392 && app.mouseY<451) {
			app.image(continueButton, 265,386);
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
		int screen=4;
		if(app.mouseX>286 && app.mouseX<513 && app.mouseY>392 && app.mouseY<451) {
			screen=5;
		}
		return screen;
	}
}