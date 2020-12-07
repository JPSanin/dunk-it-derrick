package view;

import controlP5.ControlP5;
import controlP5.Textfield;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * Register view class 

 * @author: Juan P. Sanin

 * @version: 1.0

 */


public class RegisterView {

	private PImage background, clouds, registerItems;
	private PImage continueButton;
	private PFont font;
	private int cloudsX1, cloudsX2;
	private PApplet app;
	private String[] inputs;
	private String nickname;
	private ControlP5 cp5;


	/** 
	 * 
	 *	Constructor method for Register View <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates the visualization of the register screen<br>
	 * @param app, PApplet processing core
	 */
	public  RegisterView(PApplet app) {
		this.app = app;
		background= app.loadImage("../images/HomeBackGround.png");
		clouds=app.loadImage("../images/Clouds.png");
		registerItems= app.loadImage("../images/RegisterItems.png");
		continueButton= app.loadImage("../images/ContinueButton.png");
		font= app.createFont("../fonts/Minecraft.ttf", 48);
		cloudsX1=0;
		cloudsX2=1600;
		
		cp5 = new ControlP5(app);
		inputs= new String[1];
		initializeTextFields();
	}
	
	
	private void initializeTextFields() {
		inputs[0] = "Nickname";
		
		cp5.addTextfield(inputs[0]).setPosition(250,281).setSize(290, 73).setAutoClear(true).setColorValue(app.color(255))
		.setColorActive(app.color(0,0,0,1)).setColorBackground(app.color(0,0,0,1)).setColorForeground(app.color(0,0,0,1))
		.setColor(app.color(0,0,0,255)).setColorCursor(app.color(0,0,0,255)).setFont(font).getCaptionLabel().hide();
	}

	/** 
	 * 
	 *	Method for drawing the register Screen<br>
		<b> pre: </b> <br>
		<b> post: </b>Draws the register screen<br>
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
		app.image(registerItems, 0,0);
		

		if(app.mouseX>286 && app.mouseX<513 && app.mouseY>392 && app.mouseY<451) {
			app.image(continueButton, 265,386);
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
		int screen=4;
		if(app.mouseX>286 && app.mouseX<513 && app.mouseY>392 && app.mouseY<451) {
			boolean success= addPlayer();
			if(success) {
				screen=5;
				cp5.get(Textfield.class, "Nickname").setText("");
			}
		
		}
		return screen;
	}
	
	private boolean addPlayer() {
		boolean success=false;
		nickname=cp5.get(Textfield.class, "Nickname").getText();
		
		boolean empty = nickname.equals("");
		
		if(empty) {
			//throw empty nickname exception
			System.out.println("throw empty nickname exception");
			
		}else if(nickname.length()>10) {
			//throw nickname length exception
			cp5.get(Textfield.class, "Nickname").setText("");
			System.out.println("throw nickname length exception");
			
		}else{
			//registrar
			System.out.println("registrar");
			success=true;
		}
		
		
		
		return success;
		
	}
	
	public ControlP5 getCp5() {
		return cp5;
	}
}