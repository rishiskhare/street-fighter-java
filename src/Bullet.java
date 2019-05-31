

public class Bullet extends Actor{
	private double dx;
	private double dy;
	private Player shooter;
	
	public Bullet(int speed, Player shoot)
	{
		super();
		dx = speed;
		dy = 0;
		shooter = shoot;
	}

	@Override
	public void act(long now) {
		if(this.getX()<0 || this.getX()> 1000) {
			getWorld().remove(this);
			return;
		}
		move(dx, dy);
		Player player = getOneIntersectingObject(Player.class);
		Bullet bull = getOneIntersectingObject(Bullet.class);
		
		if(player!= null&& player!= shooter) {
			player.setHurt(true);
			if(player.getDirection()) {
				player.setImage("hurt");
			}else {
				player.setImage("hurtLeft");
			}
			getWorld().remove(this);
			player.takeDamage(shooter.getProjectileDamage());
			if(player.getClass() == Player_One.class) {
				GameEngine.updatePlayerOneHealth();
			}else if(player.getClass() == Player_Two.class) {
				GameEngine.updatePlayerTwoHealth();
			}
		}else if(bull!= null&& shooter!= bull.shooter) {
			getWorld().remove(this);
			bull.getWorld().remove(bull);
			return;
		}
	}

	public void setDX(int i) {
		dx = i;
	}
	
	public void setDY(int i){
		dy = i;
	}
	public Player getShooter() {
		return shooter;
	}
	
}
