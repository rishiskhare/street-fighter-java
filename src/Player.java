import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Player extends Actor implements Fighter{
	private int health;
	private int meleeDamage;
	private int projectileDamage;
	private boolean direction;
	int xPos;
	int yPos;
	public String projectileImage;
	public String leftProjectileImage;
	public double dy = 0;
	public int jumpDy;
	public int groundHeight;
	public int bulletY;
	public int bulletX;
	int bulletWidth;
	int bulletHeight;
	public double dx = 0;
	double currentX;
	boolean isHurt;
	private Image i;
	int aniCounter = 0;
	boolean poweredUp = false;
	public boolean isPlayerOne;
	int defaultHealth;
	
	public Player(int health, int melee, int projectile, String path, boolean direct,int jumpHeight) {
		i = new Image(path);
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
	void playAttackSound() {		
	}
	void playerPowerUp() {
	}
	void playerPowerDown() {
	}
	void playDeathSound() {
	}
	void playShootingSound() {	
	}
	void playJumpSound() {
		
	}
	@Override
	public void attack() {
	playAttackSound();
	Player player = getOneIntersectingObject(Player.class);
	if(direction) {
		setImage("attack");
	}else {
		setImage("attackLeft");
	}
	if(player!= null) {
		player.takeDamage(getMeleeDamage());
		player.setHurt(true);
		if(player.direction) {
			player.setImage("hurt");
		}else {
			player.setImage("hurtLeft");
		}
		GameEngine.updatePlayerOneHealth();
		GameEngine.updatePlayerTwoHealth();
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


	@Override
	public void shoot() {
		playShootingSound();
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
    	bullet.setFitWidth(bulletWidth);
    	bullet.setFitHeight(bulletHeight);
    	bullet.setX(bulletX);
    	bullet.setY(bulletY);
    	bullet.setImage(bulletImage);
    	getWorld().add(bullet);
    	
	}
	public void jump(){
		playJumpSound();
		setHurt(false);
		if(direction) {
			setImage("jump");
		}else {
			setImage("jumpLeft");
		}
		if(this.getY() >= groundHeight){
			dy = -jumpDy;
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
	public boolean getHurt() {
		return direction;
	}
	public void setHurt(boolean hurt) {
		this.isHurt = hurt;
	}
	public boolean getPlayerNum() {
		return isPlayerOne;
	}
	public void setPlayerNum(boolean num) {
		this.isPlayerOne = num;
	}
	public void setImage(int x, int y, int width, int height) {
		this.setViewport(new Rectangle2D(x, y, width, height));
	}
	
	
}
