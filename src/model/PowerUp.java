package model;

import processing.core.PApplet;

public abstract class PowerUp implements Consumable{

	private int height, width;
	private int posX, posY;
	
	private PApplet app;
	
	public PowerUp(int posX, int posY, PApplet app) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.app = app;
		height=50;
		width=50;
	}
	
	public abstract void draw();
	
	public void setPostition(int mapX) {
		posX+=mapX;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}


	public PApplet getApp() {
		return app;
	}
	
	
	
	
	
}
