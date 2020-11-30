package model;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Tutorial derrick class 

 * @author: Juan P. Sanin

 * @version: 1.0

 */

public class TutorialDerrick extends Thread{
	
	
	private static final int RIGHT=0;
	private static final int LEFT=1;
	private static final int JUMP_RIGHT=2;
	private static final int JUMP_LEFT=3;


	private PVector position;
	private PVector velocity;
	private PVector acceleration;
	private char key;
	private boolean jumping;
	
	private int displayImage;
	
	private PImage[] images;
	private PApplet app;
	
	
	public TutorialDerrick(int x, int y,PApplet app) {
		position= new PVector(x,y);
		velocity= new PVector(0,0);
		acceleration= new PVector(0,0);
		jumping=false;
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
		if(position.x<=0 || position.x>=750) {
			acceleration.x=acceleration.x*-1;
			velocity.x=velocity.x*-1;
		}
		position.add(velocity);
		velocity.add(acceleration);
		app.image(images[displayImage], position.x, position.y);
		
		if(velocity.x>-1 && velocity.x<1) {
			acceleration.x=0;
			velocity.x=0;
		}
		
		
		
		if(position.y>=445) {
			jumping=false;
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
			acceleration.x=0.5f;
			displayImage=LEFT;
			
		}
		
		if(key=='D' || key=='d') {
			velocity.x=10;
			acceleration.x=-0.5f;
			displayImage=RIGHT;
		}
		
		if(key==32 && jumping==false) {
			jumping=true;
			velocity.y=-35;
			acceleration.y= 3.5f;
			
			if(displayImage==RIGHT) {
				displayImage=JUMP_RIGHT;
			}
			
			if(displayImage==LEFT) {
				displayImage=JUMP_LEFT;
			}
		}
	}



	public void setKey(char key) {
		this.key = key;
	}
	
	
	
	
	
	

}
