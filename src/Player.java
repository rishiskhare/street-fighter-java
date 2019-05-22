import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Player extends Actor implements Fighter{
	private int health;
	private int meleeDamage;
	private int projectileDamage;
	private boolean direction;
	public String projectileImage;
	public String leftProjectileImage;
	public double dy = 0;
	public int jumpDy;
	public double dx = 0;
	double currentX;
	private Image i;
	int aniCounter = 0;
	
	public Player(int health, int melee, int projectile, String image, boolean direct,int jumpHeight) {
		i = new Image(image);
		setImage(i);
		this.health = health;
		meleeDamage = melee;
		projectileDamage = projectile;
		setDirection(direct);
		jumpDy = jumpHeight;
	}
	@Override
	void act(long now) {
	}
	void setImage(String image) {
		
	}
	
	@Override
	public void attack() {
	Player player = getOneIntersectingObject(Player.class);
	if(direction) {
		setImage("attack");
	}else {
		setImage("attackLeft");
	}
	if(player!= null) {
		player.takeDamage(getMeleeDamage());
		System.out.println("hit");
		GameEngine.updatePlayerOneHealth();
		GameEngine.updatePlayerTwoHealth();
		GameEngine.playHurtSound();
		if(player.getX()>4&&player.getX()<920) {
			if(getX()<player.getX()) {
				player.currentX = player.getX();
				player.dx = 5;
				player.dy = -2;
			}else if(getX()>player.getX()) {
				player.currentX = player.getX();
				player.dx = -5;
				player.dy = -2;
			}
		}
	}		
	}

	@Override
	public void shoot() {
		GameEngine.playBulletSound();
		if(direction) {
			setImage("shoot");
		}else {
			setImage("shootLeft");
		}
		if(getWorld().getObjects(Bullet.class)!=null) {
			int counter = 0;
			for(int i = 0; i< getWorld().getObjects(Bullet.class).size();i++) {
				if(getWorld().getObjects(Bullet.class).get(i).getShooter().equals(this)) {
					counter++;
					if(counter>=3) {
						return;
					}
				}
			}
		}
		Image bulletImage;
		int speed = 0;
		if(getDirection()) {
			bulletImage = new Image(projectileImage);
			speed = 4;
		}else {
			bulletImage= new Image(leftProjectileImage);
			speed = -4;
		}
    	Actor bullet = new Bullet(speed, this);
    	bullet.setFitWidth(60);
    	bullet.setFitHeight(100);
    	bullet.setX(getX());
    	bullet.setY(getY());
    	bullet.setImage(bulletImage);
    	getWorld().add(bullet);
	}
	public void jump(){
		if(direction) {
			setImage("jump");
		}else {
			setImage("jumpLeft");
		}
		if(this.getY() >= 250){
			dy = -jumpDy;
		}
	}
	public void knowbackRight() {
		if(getX()>4&&getX()<910) {
				dx = 5;
		  }	
		}
	public void knowbackLeft() {
		if(getX()>4&&getX()<910) {
				dx = -5;
		  }	
		}

	
	
	
	//Getter and Setters
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getMeleeDamage() {
		return meleeDamage;
	}
	public void setMeleeDamage(int meleeDamage) {
		this.meleeDamage = meleeDamage;
	}
	public int getProjectileDamage() {
		return projectileDamage;
	}
	public void setProjectileDamage(int projectileDamage) {
		this.projectileDamage = projectileDamage;
	}
	
	public void takeDamage(int damageTook) {
		health = health-damageTook;
		
	}
	public boolean getDirection() {
		return direction;
	}
	public void setDirection(boolean direction) {
		this.direction = direction;
	}
	public void setImage(int x, int y, int width, int height) {
		this.setViewport(new Rectangle2D(x, y, width, height));
	}
	
	
}
