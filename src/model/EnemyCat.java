package model;

import processing.core.PApplet;
import processing.core.PImage;

public class EnemyCat extends Thread{

	private static final int RIGHT=0;
	private static final int LEFT=1;
	
	private int height, width;
	private int posX, posY;
	private int vel, dir;
	private int xmin,xmax;
	private PImage[] images;
	private PApplet app;
	
	
	public EnemyCat(int posX, int posY,int xmin,int xmax, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.xmin=xmin;
		this.xmax=xmax;	
		this.app = app;
		
		height=50;
		width=50;
		
		vel=2;
		int ran= (int) app.random(0,2);
		dir= (ran==0)?1:-1;
		
		images= new PImage[2];
		
		images[RIGHT]=app.loadImage("../images/catRight.png");
		images[LEFT]=app.loadImage("../images/catLeft.png");
		
	}
	
	public void run() {
		move();
	}
	
	private void move() {
		
		if(posX<xmin) {
			dir*=-1;
		}
		if(posX+width>xmax) {
			dir*=-1;
		}
		posX+=vel*dir;
	}
	
	public void draw() {
		if(dir==1) {
			app.image(images[RIGHT],posX,posY,height,width);
		}
		if(dir==-1) {
			app.image(images[LEFT],posX,posY,height,width);
		}
	}
	
	public void setPostitions(int mapX) {
		posX+=mapX;
		xmin+=mapX;
		xmax+=mapX;
	}

}
