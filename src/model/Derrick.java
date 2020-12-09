
package model;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Derrick class 
 * @author: Juan P. Sanin
 * @version: 1.0
 */

public class Derrick extends Thread{


	private static final int RIGHT=0;
	private static final int LEFT=1;
	private static final int JUMP_RIGHT=2;
	private static final int JUMP_LEFT=3;
	private static final int SPEED=1;
	private static final int INVINCIBLE=2;


	private int floor;
	private int height;
	private int width;
	private int winX;
	private PVector position;
	private PVector velocity;
	private PVector acceleration;
	private boolean jumping;
	private int health;
	private int status;
	private char key;

	private int displayImage;

	private PImage[] images;
	private PApplet app;


	public Derrick(int x, int y,PApplet app) {
		position= new PVector(x,y);
		velocity= new PVector(0,0);
		acceleration= new PVector(0,0);
		floor= 500;
		height=50;
		width=50;
		jumping=false;
		health=3;
		status=0;

		displayImage=RIGHT;
		images= new PImage[4];
		images[RIGHT]=app.loadImage("../images/DerrickRight.png");
		images[LEFT]=app.loadImage("../images/DerrickLeft.png");
		images[JUMP_RIGHT]=app.loadImage("../images/DerrickJumpingRight.png");
		images[JUMP_LEFT]=app.loadImage("../images/DerrickJumpingLeft.png");
		winX=2200;
		this.app = app;
	}

	public void run() {
		move();
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void draw() {
		acceleration.y= 3.5f;
		if(position.x<=-1 || position.x>=750) {
			if(position.x<-1) {
				position.x=1;
			}
			acceleration.x=acceleration.x*-1;
			velocity.x=velocity.x*-1;
		}

		app.image(images[displayImage], position.x, position.y);
		position.add(velocity);
		velocity.add(acceleration);


		if(velocity.x>-1 && velocity.x<1) {
			acceleration.x=0;
			velocity.x=0;
		}

		if(position.y>=floor-height-5) {
			jumping=false;
			position.y=floor-height;
			velocity.y=0;
			acceleration.y=0;

			if(displayImage==JUMP_RIGHT) {
				displayImage=RIGHT;
			}

			if(displayImage==JUMP_LEFT) {
				displayImage=LEFT;
			}
		}
	
	}

	public void move() {
		if(key=='A' || key=='a') {
			if(status==SPEED) {
				velocity.x=-13;
				acceleration.x=0.5f;
				displayImage=LEFT;
			}else {
				velocity.x=-10;
				acceleration.x=0.5f;
				displayImage=LEFT;
			}

		}

		if(key=='D' || key=='d') {
			if(status==SPEED) {
				velocity.x=13;
				acceleration.x=-0.5f;
				displayImage=RIGHT;
			}else {
				velocity.x=10;
				acceleration.x=-0.5f;
				displayImage=RIGHT;
			}
		}

		if(key==32 && jumping==false) {
			jumping=true;
			velocity.y=-30;
			if(displayImage==RIGHT) {
				displayImage=JUMP_RIGHT;
			}

			if(displayImage==LEFT) {
				displayImage=JUMP_LEFT;
			}
		}
	}

	
	public boolean checkWin() {
		boolean win=false;
		
		if(position.x>winX && position.y>230) {
			win=true;
		}
		
		return win;
	}

	public void blocker(int mapX) {
		int[][] blocks= {
				{0+mapX,100,250},
				{2000+mapX,250,350},
				{2050+mapX,200,300},
				{2100+mapX,150,500},

		};
		int[][] fireHydrants= {
				{200+mapX,400},
				{450+mapX,400},
				{750+mapX,400},
				{1000+mapX,400},
				{1350+mapX,400},
				{1600+mapX,400},
		};

		int[] topHydrant= {250+mapX,150};

		for (int i = 0; i < 4; i++) {
			if(position.x+width>blocks[i][0] && 
					position.x<blocks[i][0]+width&&
					position.y>blocks[i][1] && 
					position.y<=blocks[i][2]) {
				acceleration.x=acceleration.x*-1;
				velocity.x=velocity.x*-1;
			}
		}
		for (int i = 0; i < 6; i++) {
			if(position.x+width>fireHydrants[i][0] && 
					position.x<fireHydrants[i][0]+width &&
					position.y>fireHydrants[i][1]+25 && 
					position.y<=450 ) {
				acceleration.x=acceleration.x*-1;
				velocity.x=velocity.x*-1;
			}
		}

		
		if(position.x+width>topHydrant[0] && 
				position.x<topHydrant[0]+width &&
				position.y>topHydrant[1]+25 && 
				position.y<=250 ) {
			acceleration.x=acceleration.x*-1;
			velocity.x=velocity.x*-1;
		}

	}


	public void floorSetter(int mapX) {
		int[][] floors= {
				{200+mapX,250+mapX,400},
				{450+mapX,500+mapX,400},
				{0+mapX,500+mapX,250},
				{250+mapX,300+mapX,150},
				{800+mapX,1000+mapX,650},
				{750+mapX,800+mapX,400},
				{850+mapX,950+mapX,250},
				{590+mapX,790+mapX,350},
				{1400+mapX,1600+mapX,650},
				{1100+mapX,1300+mapX,350},
				{1450+mapX,1550+mapX,350},
				{1750+mapX,2150+mapX,350},
				{2000+mapX,2050+mapX,300},
				{2050+mapX,2100+mapX,250},
				{2100+mapX,2150+mapX,200},
				{1000+mapX,1050+mapX,400},
				{1350+mapX,1400+mapX,400},
				{1600+mapX,1650+mapX,400},
		
						
		};

		floor=500;
		for (int i = 0; i < 18; i++) {
			if(position.x+width>floors[i][0] && 
					position.x<floors[i][1] &&
					position.y<=floors[i][2]-height) {
				floor=floors[i][2];
			}
		}
	}
	
	
	public void heightBlocker(int mapX) {
		int[][] tops= {
				{0+mapX,475+mapX,300},
				{850+mapX,950+mapX,300},
				{590+mapX,790+mapX,400},
				{1100+mapX,1300+mapX,400},
				{1450+mapX,1550+mapX,400},
				{1750+mapX,2150+mapX,400},
		};
		
		for (int i = 0; i < 6; i++) {
			if(position.x+width>tops[i][0] && 
					position.x<tops[i][1] &&
					position.y<=tops[i][2]-1 
					&& position.y>=tops[i][2]-height ) {
				position.y=tops[i][2];
			}
		}
	}

	public void checkFall() {
		if(position.y>598) {
			health=0;
		}
	}
	
	public boolean checkHit(EnemyCat c) {
		boolean hit=false;
		if(status!=INVINCIBLE) {
			if (PApplet.dist(position.x+width/2,position.y+height/2 ,c.getPosX()+c.getWidth()/2 , c.getPosY()+c.getHeight()/2)
					<width/2) {
				hit=true;
			}
		}
		
		return hit;
	}


	public PVector getPosition() {
		return position;
	}

	public void setPositionX(float f) {
		this.position.x = f;
	}

	public void setKey(char key) {
		this.key = key;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setWinPos(int mapX) {
		winX+=mapX;
	}
	
	


	
	






}
