import java.io.File;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Player_One extends Player{
	private int speed = 10;
	private static MediaPlayer sound = null;
	public Player_One(int xPos, int yPos) {
		
		super(100,6,3,"adventurerSpritesheet.png", true,8);
		groundHeight = yPos;
		projectileImage = "Aircutter.png";
		leftProjectileImage = "Aircutter-left.png";
		setImage("idle");
		setX(xPos);
		setY(yPos);
		setFitWidth(140);
		setFitHeight(150);
	}
	
	@Override
	public void act(long now) {	
		bulletHeight = (int) (getY()+5);
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
		if(getWorld().isKeyDown(KeyCode.D)) {
			if(getY()>=groundHeight&&!getWorld().isKeyDown(KeyCode.F)&&!getWorld().isKeyDown(KeyCode.E)) {
				setImage("run");
			}
            move(speed,0);
            setDirection(true);
        }
        if (getWorld().isKeyDown(KeyCode.A)) {
        	if(getY()>=groundHeight&&!getWorld().isKeyDown(KeyCode.F)&&!getWorld().isKeyDown(KeyCode.E)) {
        		setImage("runLeft");
        	}
        	move(-speed,0);  
        	setDirection(false);
        } 
        if(!getWorld().isKeyDown(KeyCode.A)&&!getWorld().isKeyDown(KeyCode.D)&&!getWorld().isKeyDown(KeyCode.F)&&
        		getY()>=groundHeight&&!getWorld().isKeyDown(KeyCode.E)){
        	if(getDirection()) {
        		setImage("idle");
        	}else if(!getDirection()) {
        		setImage("idleLeft");
        	}
        }
        
        //POWER UPS
        

	}
	public void playAttackSound() {
		Media hurt = new Media(new File("adventurerAttackSound.mp3").toURI().toString());
		sound = new MediaPlayer(hurt);
		sound.play();
		
	}
	
	public void powerUp() {
		projectileImage = "powerUpWeapon.png";
		leftProjectileImage = "Aircutter-left.png"; //CHANGE IMAGES
		setMeleeDamage(50);
	}
	
	@Override
	public void setImage(String str) {
		switch (str) {
		case "idle":
			this.setViewport(new Rectangle2D(1050, 0, 150, 145));
			break;
		case "idleLeft":
			this.setViewport(new Rectangle2D(900, 0, 150, 145));
			break;
		case "jump":
			this.setViewport(new Rectangle2D(1200, 0, 150, 145));
			break;
		case "jumpLeft":
			this.setViewport(new Rectangle2D(1350, 0, 150, 145));
			break;
		case "attack":
			this.setViewport(new Rectangle2D(0, 0, 150, 145));
			break;
		case "attackLeft":
			this.setViewport(new Rectangle2D(150, 0, 150, 145));
			break;
		case "die":
			this.setViewport(new Rectangle2D(450, 0, 150, 145));
			break;
		case "dieLeft":
			this.setViewport(new Rectangle2D(300, 0, 150, 145));
			break;
		case "run":
			this.setViewport(new Rectangle2D(1650, 0, 150, 145));
			break;
		case "runLeft":
			this.setViewport(new Rectangle2D(1500, 0, 150, 145));
			break;
		case "shoot":
			this.setViewport(new Rectangle2D(0, 0, 150, 145));
			break;
		case "shootLeft":
			this.setViewport(new Rectangle2D(150, 0, 150, 145));
			break;
		case "hurt":
			this.setViewport(new Rectangle2D(600, 0, 150, 145));
			break;
		case "hurtLeft":
			this.setViewport(new Rectangle2D(750, 0, 150, 145));
			break;
		}
	}

  }

