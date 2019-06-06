import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
<<<<<<< HEAD

public class HealthBar extends Actor {

	private int health;
	private double tick;
	private Player player;
	
	public HealthBar (int health, double width, double height, double x, double y, Color c, Player p) {
		this.health = health;
		WritableImage img = new WritableImage(1, 1);
		player = p;
		setImage(img);
		setColor(c);
		
		setFitWidth(width);
		setFitHeight(height);
		setX(x);
		setY(y);
		tick = health/width;
	}
	
	public void setColor(Color c) {
		(((WritableImage) getImage()).getPixelWriter()).setColor(0, 0, c);
	}
	
	public void setHealth(int h) {
		health = h;
		setFitWidth(getFitWidth() - tick);
	}
	
	public int getHealth() {
		return health;
	}
	

	@Override
	void act(long now) {
		setFitWidth(tick * player.getHealth());
		
	}

=======
public class HealthBar extends Actor{
	Player player;
>>>>>>> branch 'master' of https://rishiskhare@bitbucket.org/2019_p4_group_2/gameproject.git
	
	public HealthBar (int health, double width, double height, double x, double y, Player p) {
		Color healthColor = Color.rgb(0, 255, 0);
		WritableImage img = new WritableImage(1, 1);
		player = p; 	
		setImage(img);
		setColor(healthColor);
		setFitWidth(width);
		setFitHeight(height);
		setX(x);
		setY(y);
	}
	
	public void setColor(Color c) {
		(((WritableImage) getImage()).getPixelWriter()).setColor(0, 0, c);
	}
	
	@Override
	void act(long now) {
		setFitWidth(player.getHealth());
		
	}
}
