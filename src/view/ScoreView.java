package view;

import controller.Controller;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Score view class 

 * @author: Juan P. Sanin

 * @version: 1.0

 */


public class ScoreView {

	private PImage background, clouds, scoreItems;
	private PImage playAgainButton;
	private int cloudsX1, cloudsX2;
	private int playersY;
	private int sort;
	
	private Controller controller;
	private PApplet app;


	/** 
	 * 
	 *	Constructor method for Score View <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates the visualization of the scores screen<br>
	 * @param app, PApplet processing core
	 */
	public  ScoreView(PApplet app) {
		this.app = app;
		background= app.loadImage("../images/BackGround.png");
		clouds=app.loadImage("../images/Clouds.png");
		scoreItems= app.loadImage("../images/ScoreItems.png");
		playAgainButton= app.loadImage("../images/PlayAgainButton.png");
		cloudsX1=0;
		cloudsX2=1600;
		playersY=275;
		sort=1;
		controller= new Controller(app);
	}

	/** 
	 * 
	 *	Method for drawing the score Screen<br>
		<b> pre: </b> <br>
		<b> post: </b>Draws the score screen<br>
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
		app.image(scoreItems, 0,0);
		

		if(app.mouseX>286 && app.mouseX<513 && app.mouseY>515 && app.mouseY<574) {
			app.image(playAgainButton, 258,508);
		}
		
		controller.drawPlayers(playersY);

	}
	
	/** 
	 * 
	 *	Method for changing screens<br>
		<b> pre: </b> <br>
		<b> post: </b>Changes screens depending on the click<br>
		@return screen, the screen where the program should go
	 */
	public int changeScreen() {
		int screen=6;
		if(app.mouseX>286 && app.mouseX<513 && app.mouseY>515 && app.mouseY<574) {
			screen=1;
		}
		return screen;
	}
	
	
	/** 
	 * 
	 *	Method for scrolling<br>
		<b> pre: </b> <br>
		<b> post: </b>Scrolls if it is possible<br>
		@param e, represents the direction of the scroll
	 */
	public void scroll(float e) {
		if(e>0) {
			if(playersY+((controller.getPlayersSize()*25))>500) {
				playersY-=25;	
			}
			
			
		}
		if(e<0) {
			if(playersY<275) {
				playersY+=25;
			}
		
		}
		
	}
}
