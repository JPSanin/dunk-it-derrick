package view;

import controller.Controller;
import exceptions.WinException;
import model.HealthHeart;
import model.SpecialBasketball;
import model.SpeedShoes;
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
	private boolean win, lose;
	private int shoeIconX, heartIconX,ballIconX,iconsY;
	
	private PImage[] shoes, hearts, balls;
	private PFont font;
	private PImage infoOverlay;
	private PImage threeHearts,twoHearts,oneHeart;
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
		threeHearts=app.loadImage("../images/threeHearts.png");
		twoHearts=app.loadImage("../images/twoHearts.png");
		oneHeart=app.loadImage("../images/oneHeart.png");
		font= app.createFont("../fonts/Minecraft.ttf", 18);
		shoes= new PImage[3];
		hearts= new PImage[2];
		balls= new PImage[3];
		
		for (int i=0; i<shoes.length; i++) {
			shoes[i]=app.loadImage("../images/shoes"+i+".png");
		}
		
		for (int i=0; i<hearts.length; i++) {
			hearts[i]=app.loadImage("../images/hearts"+i+".png");
		}
		
		for (int i=0; i<balls.length; i++) {
			balls[i]=app.loadImage("../images/balls"+i+".png");
		}
		
		cloudsX1=0;
		cloudsX2=1600;
		mapX=0;
		gameTime=0;
		scrapTime=0;
		win=false;
		lose=false;
		shoeIconX=690;
		heartIconX=722;
		ballIconX=751;
		iconsY=31;

	}

	/** 
	 * 
	 *	Method for drawing the info Screen<br>
		<b> pre: </b> <br>
		<b> post: </b>Draws the info screen<br>
	 */
	public void drawScreen() {
		int allTime=(int) app.millis()/1000;
		gameTime= (int) app.millis()/1000-scrapTime;
		controller.resetInvincibility(allTime);
		controller.resetSpeed(allTime);
		
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
		controller.drawCats();
		controller.checkHit();
		controller.checkFall();
		controller.drawPowerUps();
		controller.checkConsume();

	
		app.image(mapImage, mapX, 0);

		app.image(infoOverlay, 0, 0);
		app.textFont(font);
		app.textAlign(PConstants.CENTER);
		app.text(gameTime+" s",140,48);
		app.text(controller.getCurrentPlayer().getScore()+" pts",355,48);
		switch (controller.getLogic().getDerrick().getHealth()) {
		case 1:
			app.image(oneHeart, 510,30);
			break;
		case 2:
			app.image(twoHearts, 510,30);
			break;
		case 3:
			app.image(threeHearts,515,31);
			break;

		}
		
		drawIcons();
		
		if(controller.getLogic().getDerrick().getHealth()==0) {
			controller.getCurrentPlayer().setGameTime(gameTime);
			new Thread(controller.getCurrentPlayer()).start();
			lose=true;
		}
		
		try {
			controller.checkWin();
		} catch (WinException we) {
			controller.getCurrentPlayer().setGameTime(gameTime);
			controller.getCurrentPlayer().setScore(controller.getCurrentPlayer().getScore()+500);
			new Thread(controller.getCurrentPlayer()).start();
			win=true;
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
			controller.setPowerUpsPositions(1);
			controller.setWinPosition(1);

		}
		if(mapX<=-1600) {
			mapX=-1600;
		}else if(controller.getLogic().getDerrick().getPosition().x>400) {
			mapX-=1;
			controller.getLogic().getDerrick().setPositionX(controller.getLogic().getDerrick().getPosition().x-1);
			controller.setCatsPositions(-1);
			controller.setPowerUpsPositions(-1);
			controller.setWinPosition(-1);

		}
	}
	
	private void drawIcons() {
		
		if(controller.getLogic().getDerrick().getStatus()==1) {
			if(Math.floor((app.millis()/1000))%2==0) {
				app.image(shoes[1], shoeIconX, iconsY);
			}
		}
		if(controller.getLogic().getDerrick().getStatus()==2) {
			if(Math.floor((app.millis()/1000))%2==0) {
				app.image(balls[1], ballIconX, iconsY);
			}
		}
		
		switch(controller.getLogic().getPowerUps().size()) {
		case 0:
			if(controller.getLogic().getDerrick().getStatus()==0) {
				app.image(shoes[2], shoeIconX, iconsY);
				app.image(hearts[1], heartIconX, iconsY);
				app.image(balls[2], ballIconX, iconsY);
			}
			break;
		case 1:
			if(controller.getLogic().getPowerUps().get(0) instanceof HealthHeart) {
				app.image(hearts[0], heartIconX, iconsY);
				
				switch(controller.getLogic().getDerrick().getStatus()) {
				case 0:
					app.image(shoes[2], shoeIconX, iconsY);
					app.image(balls[2], ballIconX, iconsY);
					break;
				case 1:
					app.image(balls[2], ballIconX, iconsY);
					break;
				case 2:
					app.image(shoes[2], shoeIconX, iconsY);
					break;
				}
				
			}else if(controller.getLogic().getPowerUps().get(0) instanceof SpeedShoes) {
				app.image(shoes[0], shoeIconX, iconsY);
				
				switch(controller.getLogic().getDerrick().getStatus()) {
				case 0:
					app.image(hearts[1], heartIconX, iconsY);
					app.image(balls[2], ballIconX, iconsY);
					break;
				case 1:
					app.image(balls[2], ballIconX, iconsY);
					break;
				case 2:
					app.image(shoes[2], shoeIconX, iconsY);
					break;
				}
		
			}else{
				app.image(balls[0], ballIconX, iconsY);
				
				switch(controller.getLogic().getDerrick().getStatus()) {
				case 0:
					app.image(shoes[2], shoeIconX, iconsY);
					app.image(hearts[1], heartIconX, iconsY);
				
					break;
				case 1:
					app.image(balls[2], ballIconX, iconsY);
					break;
				case 2:
					app.image(shoes[2], shoeIconX, iconsY);
					break;
				}

			}
			break;
			
		case 2:
			if((controller.getLogic().getPowerUps().get(0) instanceof SpeedShoes &&
					controller.getLogic().getPowerUps().get(1) instanceof HealthHeart) ||
					(controller.getLogic().getPowerUps().get(1) instanceof SpeedShoes &&
							controller.getLogic().getPowerUps().get(0) instanceof HealthHeart) ) {
				app.image(hearts[0], heartIconX, iconsY);
				app.image(shoes[0], shoeIconX, iconsY);
				if(controller.getLogic().getDerrick().getStatus()==0) {
					app.image(balls[2], ballIconX, iconsY);
				}
			}
			
			if((controller.getLogic().getPowerUps().get(0) instanceof SpeedShoes &&
					controller.getLogic().getPowerUps().get(1) instanceof SpecialBasketball) ||
					(controller.getLogic().getPowerUps().get(1) instanceof SpeedShoes &&
							controller.getLogic().getPowerUps().get(0) instanceof SpecialBasketball) ) {
				app.image(shoes[0], shoeIconX, iconsY);
				app.image(balls[0], ballIconX, iconsY);
				if(controller.getLogic().getDerrick().getStatus()==0) {
					app.image(hearts[1], heartIconX, iconsY);
				}
			}
			
			if((controller.getLogic().getPowerUps().get(0) instanceof HealthHeart &&
					controller.getLogic().getPowerUps().get(1) instanceof SpecialBasketball) ||
					(controller.getLogic().getPowerUps().get(1) instanceof HealthHeart &&
							controller.getLogic().getPowerUps().get(0) instanceof SpecialBasketball) ) {
				app.image(hearts[0], heartIconX, iconsY);
				app.image(balls[0], ballIconX, iconsY);
				if(controller.getLogic().getDerrick().getStatus()==0) {
					app.image(shoes[2], shoeIconX, iconsY);
				}
			}
			
			
			break;
			
		case 3:
			app.image(hearts[0], heartIconX, iconsY);
			app.image(shoes[0], shoeIconX, iconsY);
			app.image(balls[0], ballIconX, iconsY);
			break;
		}
		
		
	}

	public void setScrapTime(int scrapTime) {
		this.scrapTime = scrapTime;
	}

	
	public boolean isWin() {
		return win;
	}

	public boolean isLose() {
		return lose;
	}

	public void reset() {
		controller.reset();
		cloudsX1=0;
		cloudsX2=1600;
		mapX=0;
		win=false;
		lose=false;
		
	}
	

}