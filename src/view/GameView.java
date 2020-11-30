package view;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Register view class 

 * @author: Juan P. Sanin

 * @version: 1.0

 */


public class GameView {

	private int[][] map;
	//0 Can Move
	//1 Floor
	//2&&3 fir hydrant
	//4 Brick
	//5 Steel
	//6 Metal
	private PImage background, clouds;
	private int cloudsX1, cloudsX2;
	private PImage floor, fireHydrant, brick, steel, metal;
	private int squareSize;
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
		floor=app.loadImage("../images/floor.png");
		fireHydrant=app.loadImage("../images/firehydrant.png");
		brick=app.loadImage("../images/brick.png");
		steel=app.loadImage("../images/steel.png");
		metal=app.loadImage("../images/metal.png");
		infoOverlay=app.loadImage("../images/infoOverlay.png");
		
		squareSize=50;
		cloudsX1=0;
		cloudsX2=1600;
		map= new int[][]{ 
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{5,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0},
			{5,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0},
			{5,5,5,5,5,5,5,5,5,5,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4},
			{0,0,0,0,2,0,0,0,0,2,0,0,0,0,0,2},
			{0,0,0,0,3,0,0,0,0,3,0,0,0,0,0,3},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		};
	
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
		
		drawMap();
		
		
		app.image(infoOverlay, 0, 0);
	}
	
	
	private void drawMap() {
		for (int columns = 0; columns < 16; columns++) {
			for (int rows = 0; rows < 12; rows++) {
				if(map[rows][columns] == 1) {
					app.image(floor,0+ (columns * squareSize),0 + (rows * squareSize),squareSize,squareSize);
				}
				if(map[rows][columns] == 2) {
					
					app.image(fireHydrant,0+ (columns * squareSize),0 + (rows * squareSize),squareSize,squareSize*2);	
				}
				
				if(map[rows][columns] == 4) {
					app.image(brick,0+ (columns * squareSize),0 + (rows * squareSize),squareSize,squareSize);
				}
				
				if(map[rows][columns] == 5) {
					app.image(steel,0+ (columns * squareSize),0 + (rows * squareSize),squareSize,squareSize);
				}
				
				
			}
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
		int screen=5;
		
			screen=6;
		
		return screen;
	}
}