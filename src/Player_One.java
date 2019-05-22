import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Player_One extends Player{
	private int speed = 10;
	public Player_One(int xPos, int yPos) {
		
		super(100,8,2,"sprite.png", true,8);
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
		if(this.getY() < 250){
			dy = dy + 0.15;
		}else{
			dy = 0;
		}
		if(getWorld().isKeyDown(KeyCode.D)) {
			if(getY()>=250&&!getWorld().isKeyDown(KeyCode.F)&&!getWorld().isKeyDown(KeyCode.E)) {
				setImage("run");
			}
            move(speed,0);
            setDirection(true);
        }
        if (getWorld().isKeyDown(KeyCode.A)) {
        	if(getY()>=250&&!getWorld().isKeyDown(KeyCode.F)&&!getWorld().isKeyDown(KeyCode.E)) {
        		setImage("runLeft");
        	}
        	move(-speed,0);  
        	setDirection(false);
        } 
        if(!getWorld().isKeyDown(KeyCode.A)&&!getWorld().isKeyDown(KeyCode.D)&&!getWorld().isKeyDown(KeyCode.F)&&
        		getY()>=250&&!getWorld().isKeyDown(KeyCode.E)){
        	if(getDirection()) {
        		setImage("idle");
        	}else if(!getDirection()) {
        		setImage("idleLeft");
        	}
        }

	}
	@Override
	public void setImage(String str) {
		switch (str) {
		case "idle":
			this.setViewport(new Rectangle2D(600, 0, 150, 145));
			break;
		case "idleLeft":
			this.setViewport(new Rectangle2D(750, 0, 150, 145));
			break;
		case "jump":
			this.setViewport(new Rectangle2D(1050, 0, 150, 145));
			break;
		case "jumpLeft":
			this.setViewport(new Rectangle2D(900, 0, 150, 145));
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
			this.setViewport(new Rectangle2D(1350, 0, 150, 145));
			break;
		case "runLeft":
			this.setViewport(new Rectangle2D(1200, 0, 150, 145));
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

