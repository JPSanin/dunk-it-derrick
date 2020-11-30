
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
	
	private int height;
	private int width;
	private PVector position;
	private PVector velocity;
	private PVector acceleration;
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
		height=50;
		width=50;
		
		health=3;
		status=0;
		
		displayImage=RIGHT;
		images= new PImage[4];
		images[RIGHT]=app.loadImage("../images/DerrickRight.png");
		images[LEFT]=app.loadImage("../images/DerrickLeft.png");
		images[JUMP_RIGHT]=app.loadImage("../images/DerrickJumpingRight.png");
		images[JUMP_LEFT]=app.loadImage("../images/DerrickJumpingLeft.png");
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
		position.add(velocity);
		velocity.add(acceleration);
		app.image(images[displayImage], position.x, position.y);
		
		if(velocity.x>-1 && velocity.x<1) {
			acceleration.x=0;
			velocity.x=0;
		}
		
		if(position.x<=0 || position.x>=750) {
			acceleration.x=acceleration.x*-1;
			velocity.x=velocity.x*-1;
		}
		
		if(position.y>=445) {
			position.y=450;
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
			velocity.x=-10;
			acceleration.x=0.3f;
			displayImage=LEFT;
			
		}
		
		if(key=='D' || key=='d') {
			velocity.x=10;
			acceleration.x=-0.3f;
			displayImage=RIGHT;
		}
		
		if(key==32) {
			velocity.y=-30;
			acceleration.y= 3.5f;
			if(displayImage==RIGHT) {
				displayImage=JUMP_RIGHT;
			}
			
			if(displayImage==LEFT) {
				displayImage=JUMP_LEFT;
			}
		}
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
	
	
	
	
	
	

}
