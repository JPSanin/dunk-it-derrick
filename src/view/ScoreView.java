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
	private PImage[] sortingCovers;
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
		sortingCovers= new PImage[4];
		for (int i = 0; i < sortingCovers.length; i++) {
			sortingCovers[i]=app.loadImage("../images/sortingCover"+(i+1)+".png");
		}
		
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
		
		switch(sort) {
		case 1:
			app.image(sortingCovers[0], 0,0);
			break;
		case 2:
			app.image(sortingCovers[1], 0,0);
			break;
		case 3:
			app.image(sortingCovers[2], 0,0);
			break;
		case 4:
			app.image(sortingCovers[3], 0,0);
			break;
		}
		

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
		if(app.mouseX>10 && app.mouseX<195 && app.mouseY>190 && app.mouseY<235) {
			sort=1;
			playersY=275;
			controller.sort(sort);
		}
		if(app.mouseX>205 && app.mouseX<395 && app.mouseY>190 && app.mouseY<235) {
			sort=2;
			playersY=275;
			controller.sort(sort);
		}
		if(app.mouseX>405 && app.mouseX<595 && app.mouseY>190 && app.mouseY<235) {
			sort=3;
			playersY=275;
			controller.sort(sort);
		}
		if(app.mouseX>600 && app.mouseX<790 && app.mouseY>190 && app.mouseY<235) {
			sort=4;
			playersY=275;
			controller.sort(sort);
		}
		
		return screen;
	}
	
	
	public void sort() {
		controller.sort(sort);
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
