import java.io.File;
import javafx.scene.paint.Color;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameEngine extends Application {
	private static Player_One playerOne = new Player_One(200, 250);
	private static Player_Two playerTwo = new Player_Two(600, 250);
	public static Text cupcakeHealth;
	public static Text iceCreamHealth;
	private static MediaPlayer sound = null;
	private static Scene scene;
	private static HealthBar p1HealthBar;
	private static HealthBar p2HealthBar;
	private int health;
	private double p1healthX;
	private double p1healthY;
	private double p1healthWidth;
	private double p1healthHeight;
	private double p2healthX;
	private double p2healthY;
	private double p2healthWidth;
	private double p2healthHeight;
	private Color healthColor;
	

	public static void main(String[] args) {
		launch();
	}

	private static FighterWorld fWorld = new FighterWorld();

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Food Fighter");
		BorderPane root = new BorderPane();
		scene = new Scene(root, 1000, 500);
		
		health = 100;
		healthColor = Color.rgb(0, 255, 0);
		
		p1healthWidth = health;
		p1healthHeight = 20;
		p1healthX = 20;
		p1healthY = 20;
		
		p2healthWidth = health;
		p2healthHeight = 20;
		p2healthX = 800;
		p2healthY = 20;
		
		p1HealthBar = new HealthBar(health, p1healthWidth, p1healthHeight, p1healthX, p1healthY, healthColor, playerOne);
		p2HealthBar = new HealthBar(health, p2healthWidth, p2healthHeight, p2healthX, p2healthY, healthColor, playerTwo);
		
		
//		cupcakeHealth = new Text("Health: " + playerOne.getHealth());
//		cupcakeHealth.setFill(Color.LIGHTGREEN);
//		cupcakeHealth.setFont(Font.font(java.awt.Font.SERIF, 25));
//		cupcakeHealth.setX(50);
//		cupcakeHealth.setY(30);
//
//		iceCreamHealth = new Text("Health: " + playerTwo.getHealth());
//		iceCreamHealth.setFill(Color.MAROON);
//		iceCreamHealth.setFont(Font.font(java.awt.Font.SERIF, 25));
//		iceCreamHealth.setX(650);
//		iceCreamHealth.setY(30);

		ImageView backgroundImageView = new ImageView("ArenaBackground.jpg");
		backgroundImageView.setFitHeight(scene.getHeight());
		backgroundImageView.setFitWidth(scene.getWidth());
		backgroundImageView.relocate(0, 0);
		root.getChildren().add(backgroundImageView);

		
		  
//		Media sounds = new Media(new File("themeSong.mp3").toURI().toString()); 
//		song= new MediaPlayer(sounds); 
//		song.play();
		 

		fWorld.add(playerOne);
		fWorld.add(playerTwo);
//		fWorld.add(cupcakeHealth);
//		fWorld.add(iceCreamHealth);
		fWorld.add(p1HealthBar);
		fWorld.add(p2HealthBar);
		root.getChildren().add(fWorld);
		root.setAlignment(fWorld, Pos.CENTER);
		stage.setScene(scene);
		stage.show();
		
		scene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.D) {
				fWorld.addKeyCode(KeyCode.D);
			}
			if (event.getCode() == KeyCode.A) {
				fWorld.addKeyCode(KeyCode.A);
			}
			if (event.getCode() == KeyCode.LEFT) {
				fWorld.addKeyCode(KeyCode.LEFT);
			}
			if (event.getCode() == KeyCode.RIGHT) {
				fWorld.addKeyCode(KeyCode.RIGHT);
			}
			if (event.getCode() == KeyCode.E) {
				fWorld.addKeyCode(KeyCode.E);
				playerOne.shoot();
			}
			if (event.getCode() == KeyCode.P) {
				fWorld.addKeyCode(KeyCode.P);
				playerTwo.shoot();
			}
			if (event.getCode() == KeyCode.W) {
				fWorld.addKeyCode(KeyCode.W);	
				playerOne.jump();
			}
			if (event.getCode() == KeyCode.UP) {
				fWorld.addKeyCode(KeyCode.UP);
				playerTwo.jump();
			}
			if (event.getCode() == KeyCode.F) {
				fWorld.addKeyCode(KeyCode.F);
				playerOne.attack();
			}
			if (event.getCode() == KeyCode.O) {
				fWorld.addKeyCode(KeyCode.O);	
				playerTwo.attack();
			}

		});

		scene.setOnKeyReleased(event -> {
			if (event.getCode() == KeyCode.D) {
				fWorld.removeKeyCode(KeyCode.D);

			}
			if (event.getCode() == KeyCode.A) {
				fWorld.removeKeyCode(KeyCode.A);
			}
			if (event.getCode() == KeyCode.LEFT) {
				fWorld.removeKeyCode(KeyCode.LEFT);
			}
			if (event.getCode() == KeyCode.RIGHT) {
				fWorld.removeKeyCode(KeyCode.RIGHT);
			}
			if (event.getCode() == KeyCode.W) {
				fWorld.removeKeyCode(KeyCode.W);
			}
			if (event.getCode() == KeyCode.E) {
				fWorld.removeKeyCode(KeyCode.E);
			}
			if (event.getCode() == KeyCode.F) {
				fWorld.removeKeyCode(KeyCode.F);
			}
			if (event.getCode() == KeyCode.O) {
				fWorld.removeKeyCode(KeyCode.O);
			}
			if (event.getCode() == KeyCode.P) {
				fWorld.removeKeyCode(KeyCode.P);
			}
			if (event.getCode() == KeyCode.UP) {
				fWorld.removeKeyCode(KeyCode.UP);
			}

		});
		

	}

	public static void playHurtSound() {
		Media hurt = new Media(new File("hurtSoundEffect.mp3").toURI().toString());
		sound = new MediaPlayer(hurt);
		sound.play();
	}
	
	public static void playBulletSound() {
		Media hurt = new Media(new File("bulletSoundEffect.mp3").toURI().toString());
		sound = new MediaPlayer(hurt);
		sound.play();
	}
	
	public static void updatePlayerOneHealth() {
		if (playerOne.getHealth() > 0) {
			p1HealthBar.setHealth(playerOne.getHealth());
		}
		else {
			fWorld.remove(p1HealthBar);
		}
		if (playerOne.getHealth() <= 0) {
			Media hurt = new Media(new File("deathSoundEffect.mp3").toURI().toString());
			sound = new MediaPlayer(hurt);
			sound.play();
			Text winText = new Text("Player Two Wins");
			winText.setX(310);
			winText.setY(250);
			winText.setFill(Color.CORNFLOWERBLUE);
			winText.setFont(Font.font(java.awt.Font.SERIF, 50));
			if(playerOne.getDirection()) {
				playerOne.setImage("die");
			}else {
				playerOne.setImage("dieLeft");
			}
			fWorld.add(winText);
			fWorld.stop();
		}
	}

	public static void updatePlayerTwoHealth() {
		if (playerTwo.getHealth() > 0) {
			p2HealthBar.setHealth(playerTwo.getHealth());
		}
		else {
			fWorld.remove(p2HealthBar);
		}
		
		if (playerTwo.getHealth() <= 0) {
			Media hurt = new Media(new File("deathSoundEffect.mp3").toURI().toString());
			sound = new MediaPlayer(hurt);
			sound.play();
			Text winText = new Text("Player One Wins");
			winText.setX(310);
			winText.setY(250);
			winText.setFill(Color.LIGHTGREEN);
			winText.setFont(Font.font(java.awt.Font.SERIF, 50));
			if(playerTwo.getDirection()) {
				playerTwo.setImage("die");
			}else {
				playerTwo.setImage("dieLeft");
			}
			
			fWorld.add(winText);
			fWorld.stop();
		}
	}

}
