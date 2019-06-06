import java.io.File;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Minotaur extends Player{
	private int speed = 3;
	private static MediaPlayer sound = null;
	static String path = Minotaur.class.getClassLoader().getResource("resources/minotaurSpritesheet.png").toString();

	public Minotaur(boolean playerNum) {
		super(150, 8, 4, path, false, 5);
		xPos = 600;
		yPos = 210;
		setImage("idleLeft");
		defaultHealth = 150;
		groundHeight = yPos;
		projectileImage = getClass().getClassLoader().getResource("resources/throwingAxe.png").toString();
		leftProjectileImage = getClass().getClassLoader().getResource("resources/throwingAxe-left.png").toString();
		setX(xPos);
		setY(yPos);
		setFitWidth(240);
		setFitHeight(230);
		bulletHeight = 100;
		bulletWidth = 60;
		isPlayerOne = playerNum;
	}
	@Override
	public void act(long now) {
		
		if(getDirection()) {
			bulletX = (int) (getX() + 120);
		}else {
			bulletX = (int) (getX());
		}
		bulletY = (int) (getY() + 95);
		if (getX() > 1100) {
			setX(-100);			
		}
		if (getX() < -200) {
			setX(1000);
		}
		if (getOneIntersectingObject(Player.class) != null) {
			speed = 1;
		} else {
			speed = 3;
		}

		move(dx,dy);
		if (dx != 0) {
			if(Math.abs(getX() - currentX) > 60) {
				dx = 0;
			}
		}

		if(this.getY() < groundHeight){
			dy = dy + 0.15;
		} else {
			dy = 0;
		}
		if(isPlayerOne) {
			if(getWorld().isKeyDown(KeyCode.D)) {
				if(getY() >= groundHeight && !getWorld().isKeyDown(KeyCode.F) && !getWorld().isKeyDown(KeyCode.E)) {
					setImage("run");
				}
	            move(speed,0);
	            setHurt(false);
	            setDirection(true);
	        }
	        if (getWorld().isKeyDown(KeyCode.A)) {
	        	if(getY() >= groundHeight && !getWorld().isKeyDown(KeyCode.F) && !getWorld().isKeyDown(KeyCode.E)) {
	        		setImage("runLeft");
	        	}
	        	move(-speed,0);
	        	setHurt(false);
	        	setDirection(false);
	        } 
	        if(!getWorld().isKeyDown(KeyCode.A) && !getWorld().isKeyDown(KeyCode.D) && !getWorld().isKeyDown(KeyCode.F) && getY() >= groundHeight && !getWorld().isKeyDown(KeyCode.E) && !isHurt){
	        	if(getDirection()) {
	        		setImage("idle");
	        	}else if(!getDirection()) {
	        		setImage("idleLeft");
	        	}
	        }
			}else if(!isPlayerOne) {
		        if (getWorld().isKeyDown(KeyCode.LEFT)) {
		        	if(getY() >= groundHeight && !getWorld().isKeyDown(KeyCode.O) && !getWorld().isKeyDown(KeyCode.P)) {
		        		setImage("runLeft");
		        	}
		        	move(-speed,0); 
		        	setHurt(false);
		        	setDirection(false);
		        } 		
		        if(getWorld().isKeyDown(KeyCode.RIGHT)){
					if(getY() >= groundHeight && !getWorld().isKeyDown(KeyCode.O) && !getWorld().isKeyDown(KeyCode.P)) {
						setImage("run");
					}
		            move(speed,0);  
		            setHurt(false);
		            setDirection(true);
		        }
		        if(!getWorld().isKeyDown(KeyCode.LEFT) && !getWorld().isKeyDown(KeyCode.RIGHT) && !getWorld().isKeyDown(KeyCode.O) && getY() >= groundHeight && !getWorld().isKeyDown(KeyCode.P) && !isHurt){
		        	if(getDirection()) {
		        		setImage("idle");
		        	}else if(!getDirection()) {
		        		setImage("idleLeft");
		        	}
		        }		
			}

	}

	public void playAttackSound() {
		Media hurt = new Media(new File("src/resources/minotaurAttackSound.mp3").toURI().toString());
		sound = new MediaPlayer(hurt);
		sound.play();		
	}
	
	public void playDeathSound() {
		Media hurt = new Media(new File("src/resources/MinotaurDeathSound.mp3").toURI().toString());
		sound = new MediaPlayer(hurt);
		sound.play();		
	}
	public void playShootingSound() {
		Media hurt = new Media(new File("src/resources/CyclopsThrow.mp3").toURI().toString());
		sound = new MediaPlayer(hurt);
		sound.play();	
	}	
	public void playJumpSound() {
			
	}

//	public void playerPowerUp() {
//		poweredUp = true;
//		setMeleeDamage(18);
//		setFitWidth(340);
//		setFitHeight(330);
//		setHealth(getHealth()+20);
//		speed = 6;
//		groundHeight = 132;
//		setY(132);
//	}
	
	public void playerPowerDown() {
		poweredUp = false;
		setMeleeDamage(8);
		setFitWidth(240);
		setFitHeight(230);
		speed = 3;
		groundHeight = 210;
		setY(210);
		setImage("powerDown");
	}
		

	public void playerPowerUp() {
		poweredUp = true;
		speed = 12;
		setMeleeDamage(50);
		if (getDirection()) {
			setImage("powerUpP2Right");
		} else {
			setImage("powerUpP2Left");
		}

	}


	@Override
	public void setImage(String str) {
		if(this.getImage().impl_getUrl().contentEquals(Player.class.getClassLoader().getResource("resources/minotaurSpritesheet.png").toString())) {
			switch (str) {
			case "attack":
				this.setViewport(new Rectangle2D(0, 0, 150, 145));
				break;
			case "attackLeft":
				this.setViewport(new Rectangle2D(0, 145, 150, 145));
				break;
			case "dieLeft":
				this.setViewport(new Rectangle2D(0, 290, 150, 145));
				break;
			case "die":
				this.setViewport(new Rectangle2D(0, 435, 150, 145));
				break;
			case "hurt":
				this.setViewport(new Rectangle2D(0, 580, 150, 145));
				break;
			case "hurtLeft":
				this.setViewport(new Rectangle2D(0, 725, 150, 145));
				break;
			case "idle":
				this.setViewport(new Rectangle2D(0, 870, 150, 145));
				break;
			case "idleLeft":
				this.setViewport(new Rectangle2D(0, 1015, 150, 145));
				break;
			case "jumpLeft":
				this.setViewport(new Rectangle2D(0, 1160, 150, 145));
				break;
			case "jump":
				this.setViewport(new Rectangle2D(0, 1305, 150, 145));
				break;
			case "runLeft":
				this.setViewport(new Rectangle2D(0, 1450, 150, 145));
				break;
			case "run":
				this.setViewport(new Rectangle2D(0, 1595, 150, 145));
				break;
			case "shoot":
				this.setViewport(new Rectangle2D(0, 1885, 150, 145));
				break;
			case "shootLeft":
				this.setViewport(new Rectangle2D(0, 1740, 150, 145));
				break;
			case "powerUpP2Left":
				String accessLeft = getClass().getClassLoader().getResource("resources/powerUpP2Left.png").toString();
				Image imageLeft = new Image(accessLeft);
				this.setImage(imageLeft);
				this.setViewport(null);
				break;
			case "powerUpP2Right":
				String accessRight = getClass().getClassLoader().getResource("resources/powerUpP2Right.png").toString();
				Image imageRight = new Image(accessRight);
				this.setImage(imageRight);
				this.setViewport(null);
				break;
			}
		} else if (this.getImage().impl_getUrl().contentEquals(Player.class.getClassLoader().getResource("resources/powerUpP2Right.png").toString()) || this.getImage().impl_getUrl().contentEquals(Player.class.getClassLoader().getResource("resources/powerUpP2Left.png").toString())){
			switch(str) {
			case "run":
				String accessRight = getClass().getClassLoader().getResource("resources/powerUpP2Right.png").toString();
				Image imageRight = new Image(accessRight);
				this.setImage(imageRight);
				this.setViewport(null);
				break;
			case "runLeft":
				String accessLeft = getClass().getClassLoader().getResource("resources/powerUpP2Left.png").toString();
				Image imageLeft = new Image(accessLeft);
				this.setImage(imageLeft);
				this.setViewport(null);
				break;
			case "powerDown":
				String path = Player.class.getClassLoader().getResource("resources/minotaurSpritesheet.png").toString();
				Image pathImage = new Image(path);
				this.setImage(pathImage);
				this.setViewport(new Rectangle2D(0, 870, 150, 145));
				break;
			}
		}
	}

}
