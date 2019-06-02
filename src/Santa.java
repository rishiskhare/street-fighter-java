import java.io.File;

import javafx.geometry.Rectangle2D;
//import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.scene.image.Image;


public class Santa extends Player{

	private int speed = 6;
	private static MediaPlayer sound = null;
	
	public Santa(Boolean playerNum) {
		super(125,4,7,"resources/santaSpritesheet.png", true,6);
		xPos = 200;
		yPos = 275;
		groundHeight = yPos;
		defaultHealth = 125;
		projectileImage = "resources/present.png";
		leftProjectileImage = "resources/present.png";
		setImage("idle");
		setX(xPos);
		setY(yPos);
		setFitWidth(140);
		setFitHeight(150);
		bulletHeight = 120;
		bulletWidth = 120;
		isPlayerOne = playerNum;
	}
	
	@Override
	public void act(long now) {	

		if(getDirection()) {
			bulletX = (int) (getX()+70);
		}else {
			bulletX = (int) (getX());
		}
		bulletY = (int) (getY()-5);
		if(getX()> 1100) {
			setX(-100);			
		}
		if(getX()<-200) {
    		setX(1000);
		}
		if(getOneIntersectingObject(Player.class)!=null) {
			speed = 5;
		}else {
			speed = 10;
		}
		move(dx,dy);
		if(dx!=0) {
			if(Math.abs(getX()-currentX)> 60) {
				dx = 0;
				dy =0;
			}
		}
		if(this.getY() < groundHeight){
			dy = dy + 0.15;
		}else{
			dy = 0;
		}
		if(isPlayerOne) {
		if(getWorld().isKeyDown(KeyCode.D)) {
			if(getY()>=groundHeight&&!getWorld().isKeyDown(KeyCode.F)&&!getWorld().isKeyDown(KeyCode.E)) {
				setImage("run");
			}
            move(speed,0);
            setHurt(false);
            setDirection(true);
        }
        if (getWorld().isKeyDown(KeyCode.A)) {
        	if(getY()>=groundHeight&&!getWorld().isKeyDown(KeyCode.F)&&!getWorld().isKeyDown(KeyCode.E)) {
        		setImage("runLeft");
        	}
        	move(-speed,0);
        	setHurt(false);
        	setDirection(false);
        } 
        if(!getWorld().isKeyDown(KeyCode.A)&&!getWorld().isKeyDown(KeyCode.D)&&!getWorld().isKeyDown(KeyCode.F)&&
        		getY()>=groundHeight&&!getWorld().isKeyDown(KeyCode.E)&& !isHurt){
        	if(getDirection()) {
        		setImage("idle");
        	}else if(!getDirection()) {
        		setImage("idleLeft");
        	}
        }
		}else if(!isPlayerOne) {
	        if (getWorld().isKeyDown(KeyCode.LEFT)) {
	        	if(getY()>=groundHeight&&!getWorld().isKeyDown(KeyCode.O)&&!getWorld().isKeyDown(KeyCode.P)) {
	        		setImage("runLeft");
	        	}
	        	System.out.println("run");
	        	move(-speed,0); 
	        	setHurt(false);
	        	setDirection(false);
	        } 
			if(getWorld().isKeyDown(KeyCode.RIGHT)){
				if(getY()>=groundHeight&&!getWorld().isKeyDown(KeyCode.O)&&!getWorld().isKeyDown(KeyCode.P)) {
					setImage("run");
				}
	            move(speed,0);  
	            setHurt(false);
	            setDirection(true);
	        }
	        if(!getWorld().isKeyDown(KeyCode.LEFT)&&!getWorld().isKeyDown(KeyCode.RIGHT)&&!getWorld().isKeyDown(KeyCode.O)&&
	        		getY()>=groundHeight&&!getWorld().isKeyDown(KeyCode.P)&&!isHurt){
	        	if(getDirection()) {
	        		setImage("idle");
	        	}else if(!getDirection()) {
	        		setImage("idleLeft");
	        	}
	        }		
		}
        
        
        

	}
	public void playAttackSound() {
		Media hurt = new Media(new File("src/resources/DwarfAttack.mp3").toURI().toString());
		sound = new MediaPlayer(hurt);
		sound.play();	
	}
	
	public void playDeathSound() {
		Media hurt = new Media(new File("src/resources/MinotaurDeathSound.mp3").toURI().toString());
		sound = new MediaPlayer(hurt);
		sound.play();		
	}
	public void playShootingSound() {
		Media hurt = new Media(new File("src/resources/DwarfAndSantaThrow.mp3").toURI().toString());
		sound = new MediaPlayer(hurt);
		sound.play();	
	}
	public void playJumpSound() {
		Media hurt = new Media(new File("src/resources/jump.mp3").toURI().toString());
		sound = new MediaPlayer(hurt);
		sound.play();	
	}
	//POWER UPS
	public void playerPowerUp() {	
		poweredUp = true;
		speed = 8;
		jumpDy = 8;
		setProjectileDamage(8);
	}
	
	
	@Override
	public void setImage(String str) {
		switch (str) {
		case "idle":
			this.setViewport(new Rectangle2D(0, 1015, 150, 145));
			break;
		case "idleLeft":
			this.setViewport(new Rectangle2D(0, 870, 150, 145));
			break;
		case "jump":
			this.setViewport(new Rectangle2D(0, 1305, 150, 145));
			break;
		case "jumpLeft":
			this.setViewport(new Rectangle2D(0, 1160, 150, 145));
			break;
		case "attack":
			this.setViewport(new Rectangle2D(0, 0, 150, 145));
			break;
		case "attackLeft":
			this.setViewport(new Rectangle2D(0, 145, 150, 145));
			break;
		case "die":
			this.setViewport(new Rectangle2D(0, 435, 150, 145));
			break;
		case "dieLeft":
			this.setViewport(new Rectangle2D(0, 290, 150, 145));
			break;
		case "run":
			this.setViewport(new Rectangle2D(0, 1595, 150, 145));
			break;
		case "runLeft":
			this.setViewport(new Rectangle2D(0, 1450, 150, 145));
			break;
		case "shoot":
			this.setViewport(new Rectangle2D(0, 1885, 150, 145));
			break;
		case "shootLeft":
			this.setViewport(new Rectangle2D(0, 1740, 150, 145));
			break;
		case "hurt":
			this.setViewport(new Rectangle2D(0, 725, 150, 145));
			break;
		case "hurtLeft":
			this.setViewport(new Rectangle2D(0, 580, 150, 145));
			break;
		case "powerUp":
			System.out.println("powered up");
			break;
		case "powerUpLeft":
			System.out.println("powered up(L)");
			

			break;
		}
	}

  }

