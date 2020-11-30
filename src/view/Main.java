package view;

import processing.core.PApplet;

public class Main extends PApplet{


	private StartView sv;
	private InfoView iv;
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
		rv= new RegisterView(this);
		gv= new GameView(this);
		scv= new ScoreView(this);
		
	}

	public void draw() {
		background(0);

		switch(screen) {
		case 1:
			sv.drawScreen();
			break;
		case 2:
			iv.drawScreen();
			break;
		case 3:
			
			break;
		case 4:
			rv.drawScreen();
			break;
		case 5:
			gv.drawScreen();
			break;
		case 6:
			scv.drawScreen();
			break;
		}


		fill(255);
		text(mouseX+ ","+mouseY, mouseX,mouseY);
	}



	public void mousePressed() {
		switch(screen) {
		case 1:
			screen=sv.changeScreen();
			break;
		case 2:
			screen=iv.changeScreen();
			break;
		case 3:
			
			break;
		case 4:
			screen=rv.changeScreen();
			break;
		case 5:
			screen=gv.changeScreen();
			break;
		case 6:
			screen=scv.changeScreen();
			break;
		}
	}
}
