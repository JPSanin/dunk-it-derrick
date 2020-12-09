package view;

import processing.core.PApplet;
import processing.event.MouseEvent;

public class Main extends PApplet{


	private StartView sv;
	private InfoView iv;
	private TutorialView tv;
	private RegisterView rv;
	private GameView gv;
	private ScoreView scv;
	private int screen;
	public static void main(String[] args) {
		PApplet.main(Main.class.getName());
	}

	public void settings() {
		size(800,600);

	}

	public void setup() {
		screen=1;
		sv= new StartView(this);
		iv= new InfoView(this);
		tv= new TutorialView(this);
		rv= new RegisterView(this);
		gv= new GameView(this);
		scv= new ScoreView(this);
		
	}

	public void draw() {
		background(0);

		switch(screen) {
		case 1:
			sv.drawScreen();
			rv.getCp5().hide();
			break;
		case 2:
			iv.drawScreen();
			rv.getCp5().hide();
			break;
		case 3:
			tv.drawScreen();
			rv.getCp5().hide();
			break;
		case 4:
			rv.drawScreen();
			rv.getCp5().show();
			break;
		case 5:
			gv.drawScreen();
			if(gv.isLose()) {
				screen=6;
				scv.setLose(gv.isLose());
			}
			if(gv.isWin()) {
				screen=6;
				scv.setWin(gv.isWin());
			}
			rv.getCp5().hide();
			break;
		case 6:
			scv.drawScreen();
			rv.getCp5().hide();
			break;
		}


		fill(255);
		text(mouseX+ ","+mouseY, mouseX,mouseY);
	}

	public void mousePressed() {
		switch(screen) {
		case 1:
			screen=sv.changeScreen();
			rv.clearTextField();
			break;
		case 2:
			screen=iv.changeScreen();
			break;
		case 3:
			screen= tv.changeScreen();
			break;
		case 4:
			screen=rv.changeScreen();
			if(screen==5) {
				gv.setScrapTime(rv.getTime());
			}
			break;
		case 5:
			screen=gv.changeScreen();
			if(screen==6) {
				scv.sort();
			}
			break;
		case 6:
			screen=scv.changeScreen();
			break;
		}
	}
	
	public void keyPressed() {
		switch(screen) {
		case 3:
			 tv.moveDerrick(key);
			break;
		
		case 5:
			gv.moveDerrick(key);
			break;
		}
	}
	
	
	public void mouseWheel(MouseEvent event) {
		float e = event.getCount();
		switch(screen) {
		case 6:
			scv.scroll(e);
			break;
		}


	}
	
}
