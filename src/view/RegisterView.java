package view;

import controlP5.ControlP5;
import controlP5.Textfield;
import exceptions.EmptyNicknameException;
import exceptions.NicknameLengthException;
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
	private PImage emptyNickImage,longNickImage;
	private boolean emptyNick, longNick;
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
		emptyNickImage= app.loadImage("../images/emptyNick.png");
		longNickImage= app.loadImage("../images/longNick.png");
		font= app.createFont("../fonts/Minecraft.ttf", 48);
		cloudsX1=0;
		cloudsX2=1600;
		
		emptyNick=false;
		longNick=false;
		
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
		
		if(emptyNick) {
			app.image(emptyNickImage, 0, 0);
		}
		
		if(longNick) {
			app.image(longNickImage, 0, 0);
		}
		
		

	}
	
	/** 
	 * 
	 *	Method for changing screens<br>
		<b> pre: </b> <br>
		<b> post: </b>Changes screens depending on the click<br>
		@return screen, the screen where the program should go
	 * @throws EmptyNicknameException 
	 * @throws NicknameLengthException 
	 */
	public int changeScreen()  {
		int screen=4;
		if(emptyNick==false && longNick==false) {
			if(app.mouseX>286 && app.mouseX<513 && app.mouseY>392 && app.mouseY<451) {
				boolean success;
				try {
					success = addPlayer();
					if(success) {
						screen=5;
						cp5.get(Textfield.class, "Nickname").setText("");
					}
				} catch (NicknameLengthException e) {
					cp5.get(Textfield.class, "Nickname").setText("");
					longNick=true;
				} catch (EmptyNicknameException e) {
					emptyNick=true;
				}
				
			}
		}else {
			if(emptyNick) {
				emptyNick=false;
			}
			if(longNick) {
				longNick=false;
			}
		}
		
		return screen;
	}
	
	private boolean addPlayer() throws NicknameLengthException, EmptyNicknameException {
		boolean success=false;
		nickname=cp5.get(Textfield.class, "Nickname").getText();
		
		boolean empty = nickname.equals("");
		
		if(empty) {
			throw new EmptyNicknameException();
		}else if(nickname.length()>10) {
			throw new NicknameLengthException();
			
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