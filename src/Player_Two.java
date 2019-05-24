import java.io.File;

import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Player_Two extends Player{
	private int speed = 2;
	private static MediaPlayer sound = null;
	public Player_Two(int xPos,int yPos) {
		super(10,8,4,"cow.png", false, 5);
		setImage("idleLeft");
		groundHeight = yPos;
		projectileImage = "throwingAxe.png";
		leftProjectileImage = "throwingAxe-left.png";
		setX(xPos);
		setY(yPos);
		setFitWidth(240);
		setFitHeight(230);
	}
	@Override
	public void act(long now) {
		bulletHeight = (int) (getY() +95);
		if(getX()> 1100) {
			setX(-100);			
		}
		if(getX()<-200) {
    		setX(1000);
		}
		if(getOneIntersectingObject(Player.class)!=null) {
			speed = 1;
		}else {
			speed = 2;
		}
		move(dx,dy);
		if(dx!=0) {
			if(Math.abs(getX()-currentX)> 60) {
				dx = 0;
			}
		}
		if(this.getY() < groundHeight){
			dy = dy + 0.15;
		}else{
			dy = 0;
		}
		if(getWorld().isKeyDown(KeyCode.RIGHT)){
			if(getY()>=groundHeight&&!getWorld().isKeyDown(KeyCode.O)&&!getWorld().isKeyDown(KeyCode.P)) {
				setImage("run");
			}
            move(speed,0);  
            setDirection(true);
        }
        if (getWorld().isKeyDown(KeyCode.LEFT)) {
        	if(getY()>=groundHeight&&!getWorld().isKeyDown(KeyCode.O)&&!getWorld().isKeyDown(KeyCode.P)) {
        		setImage("runLeft");
        	}
        	move(-speed,0); 
        	setDirection(false);
        } 
        if(!getWorld().isKeyDown(KeyCode.LEFT)&&!getWorld().isKeyDown(KeyCode.RIGHT)&&!getWorld().isKeyDown(KeyCode.O)&&
        		getY()>=groundHeight&&!getWorld().isKeyDown(KeyCode.P)){
        	if(getDirection()) {
        		setImage("idle");
        	}else if(!getDirection()) {
        		setImage("idleLeft");
        	}
        }
	}
	public void playAttackSound() {
		Media hurt = new Media(new File("minotaurAttackSound.mp3").toURI().toString());
		sound = new MediaPlayer(hurt);
		sound.play();		
	}
	@Override
	public void setImage(String str) {
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
			this.setViewport(new Rectangle2D(0, 1740, 150, 145));
			break;
		case "shootLeft":
			this.setViewport(new Rectangle2D(0, 1885, 150, 145));
			break;
			
		}
	}
}
