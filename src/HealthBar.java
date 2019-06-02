import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class HealthBar extends Actor{
	Player player;
	
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
