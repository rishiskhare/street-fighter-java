import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public abstract class World extends javafx.scene.layout.Pane{

	private AnimationTimer timer;
	private ArrayList<Actor> actors;
	private HashSet<KeyCode> hash;
	
	/*When a World is created, you should initialize a new AnimationTimer object using an anonymous 
	 * inner class. Inside the handle() method, you should tell the World to act() and then go through 
	 * the list of all Actors in the World and tell each of them to act()*/

	public World() {
		super();
		hash = new HashSet<KeyCode>();
		timer = new AnimationTimer(){
			@Override
			public void handle(long now){
				act(now);
				List<Actor> list = getObjects(Actor.class); 
				for(int i = 0; i < list.size(); i++){
					list.get(i).act(now);
				}
			}
		};
		timer.start();
	}

	public void addKeyCode(KeyCode k) {
    	hash.add(k);
    }
    
    public void removeKeyCode(KeyCode k) {
    	hash.remove(k);
    }
    
    public boolean isKeyDown(KeyCode k) {
    	return (hash.contains(k));
    }

	
	public abstract void act(long now);
	
	public void add(Actor actor) {
		this.getChildren().add(actor);
	}
	
	public void remove(Actor actor) {
		this.getChildren().remove(actor);
	}
	public void add(Text text){
		super.getChildren().add(text);
	}
	
	

	public <A extends Actor> java.util.List<A> getObjects(Class<A> cls){
		ObservableList<Node> list = this.getChildren();
		ArrayList<A> newList = new ArrayList<A>();
		for(int i = 0; i < list.size(); i++){
			if (cls.isInstance(list.get(i))){
				newList.add(cls.cast(list.get(i)));
			}
		}
		return newList;
	}
		
	
	
	public void start() {
		//starts timer
		timer.start();
	}
	
	public void stop() {
		//stops timer
		timer.stop();
	}
}

