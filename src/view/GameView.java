package view;

import controller.Controller;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

/**
 * Register view class 

 * @author: Juan P. Sanin

 * @version: 1.0

 */


public class GameView {


	private PImage background, clouds, mapImage;
	private float cloudsX1, cloudsX2;
	private int mapX;
	private int gameTime, scrapTime;
	private PFont font;
	private PImage infoOverlay;
	private Controller controller;
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
		controller= new Controller(app);

		background= app.loadImage("../images/soloBackground.png");
		clouds=app.loadImage("../images/Clouds.png");
		infoOverlay=app.loadImage("../images/infoOverlay.png");
		mapImage=app.loadImage("../images/map.png");
		font= app.createFont("../fonts/Minecraft.ttf", 18);
		cloudsX1=0;
		cloudsX2=1600;
		mapX=0;
		gameTime=0;
		scrapTime=0;
	
	}

	/** 
	 * 
	 *	Method for drawing the info Screen<br>
		<b> pre: </b> <br>
		<b> post: </b>Draws the info screen<br>
	 */
	public void drawScreen() {
		gameTime= (int) app.millis()/1000-scrapTime;
		app.image(background, 0, 0);

		moveMap();
		if(cloudsX1>-1600) {
			cloudsX1-=0.5;
		}else {
			cloudsX1=1600;
		}

		if(cloudsX2>-1600) {
			cloudsX2-=0.5;
		}else {
			cloudsX2=1600;
		}

		app.image(clouds, cloudsX1, 50);
		app.image(clouds, cloudsX2, 50);
		controller.blocker(mapX);
		controller.floorSetter(mapX);
		controller.heightBlocker(mapX);
		controller.moveCats();
		controller.drawDerrick();
		//controller.catBlocker();

		controller.drawCats();

		app.image(mapImage, mapX, 0);

		app.image(infoOverlay, 0, 0);
		app.textFont(font);
		app.textAlign(PConstants.CENTER);
		app.text(gameTime+" s",140,48);


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
		controller.getCurrentPlayer().setGameTime(gameTime);
		screen=6;
		return screen;
	}

	public void moveDerrick(char key) {
		controller.getLogic().getDerrick().setKey(key);
		new Thread(controller.getLogic().getDerrick()).start();

	}

	private void moveMap() {
		if(mapX>=0) {
			mapX=0;
		}else if(controller.getLogic().getDerrick().getPosition().x<200) {
				mapX+=1;
				controller.getLogic().getDerrick().setPositionX(controller.getLogic().getDerrick().getPosition().x+1);
				controller.setCatsPositions(1);
			

		}
		if(mapX<=-1600) {
			mapX=-1600;
		}else if(controller.getLogic().getDerrick().getPosition().x>400) {
			mapX-=1;
			controller.getLogic().getDerrick().setPositionX(controller.getLogic().getDerrick().getPosition().x-1);
			controller.setCatsPositions(-1);

		}
	}
	
	public void setScrapTime(int scrapTime) {
		this.scrapTime = scrapTime;
	}



}