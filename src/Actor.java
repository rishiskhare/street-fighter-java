

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView {
	
	abstract void act(long now);
	
	void move (double dx, double dy) {
		this.setX(this.getX() + dx);
		this.setY(this.getY() + dy);
	}
	
	World getWorld() {
		return (World)getParent();
	}
	
	double getHeight() {
		return this.getBoundsInParent().getHeight();
	}
	
	double getWidth() {
		return this.getBoundsInParent().getWidth();
	}
	
	
	<A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> clas) {
		List finalList = new ArrayList<A>();
		for (A obj:getWorld().getObjects(clas)) {
			if (this!=obj && this.intersects(obj.getBoundsInLocal())) {
				finalList.add(obj);
			}
		}
		return finalList;
	}
	
	<A extends Actor> A getOneIntersectingObject(java.lang.Class<A> clas) {
		for (A obj : getWorld().getObjects(clas)) {
			if (this.intersects(obj.getBoundsInLocal()) && this!=obj) {
				return obj;
			}
		}
		return null;
	}
}


