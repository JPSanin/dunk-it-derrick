package model;

import processing.core.PApplet;
import processing.core.PImage;

public class SpeedShoes extends PowerUp{
	
	private PImage image;
	
	public SpeedShoes(int posX, int posY, PApplet app) {
		super(posX, posY, app);
		image= app.loadImage("../images/speedShoe.png");
	}

	

	@Override
	public void draw() {
		getApp().image(image, getPosX(),getPosY(),getWidth(),getHeight());
	}
	
	@Override
	public boolean consumed(Derrick d) {
		boolean consume= false;
		if (PApplet.dist(d.getPosition().x+d.getWidth()/2,d.getPosition().y+d.getHeight()/2 ,getPosX()+getWidth()/2 ,getPosY()+getHeight()/2)
				<d.getWidth()/2+getWidth()/2) {
			consume=true;
		}
		return consume;
	}

}
