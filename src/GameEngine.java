import java.io.File;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
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
	private static Player_Two playerTwo = new Player_Two(600, 185);
	public static Text playeOneHealth;
	public static Text playerTwoHealth;
	private static MediaPlayer sound = null;
	private static Scene scene;
	public static boolean gameOver = true;
	public static Button restartButton;
	public static BorderPane root;
	static HBox restartBox;
	static Text winText;
	Text time;
	public static void main(String[] args) {
		launch();
	}

	private static FighterWorld fWorld = new FighterWorld();

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Labrynith");
		root = new BorderPane();
		scene = new Scene(root, 1000, 500);

		playeOneHealth = new Text("Health: " + playerOne.getHealth());
		playeOneHealth.setFill(Color.CORNFLOWERBLUE);
		playeOneHealth.setFont(Font.font(java.awt.Font.SERIF, 25));
		playeOneHealth.setX(50);
		playeOneHealth.setY(30);

		playerTwoHealth = new Text("Health: " + playerTwo.getHealth());
		playerTwoHealth.setFill(Color.MAROON);
		playerTwoHealth.setFont(Font.font(java.awt.Font.SERIF, 25));
		playerTwoHealth.setX(650);
		playerTwoHealth.setY(30);

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
		fWorld.add(playeOneHealth);
		fWorld.add(playerTwoHealth);
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
				if(!playerOne.getWorld().isKeyDown(KeyCode.F)) {
					playerOne.attack();
				}
				fWorld.addKeyCode(KeyCode.F);			
			}
			if (event.getCode() == KeyCode.O) {
				if(!playerOne.getWorld().isKeyDown(KeyCode.O)) {
					playerTwo.attack();
				}
				fWorld.addKeyCode(KeyCode.O);	
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

	public static void restartGame() {
		playerOne.setX(200);
		playerOne.setY(250);
		playerTwo.setX(600);
		playerTwo.setY(185);
		playerOne.setHealth(100);
		playerTwo.setHealth(150);
		playerOne.setMeleeDamage(6);
		playerTwo.setMeleeDamage(8);
		updatePlayerOneHealth();
		updatePlayerTwoHealth();
		root.getChildren().remove(restartBox);
		fWorld.remove(winText);
		gameOver = true;
		fWorld.start();
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
		playeOneHealth.setText("Health: " + playerOne.getHealth());
		if (playerOne.getHealth() <= 0&& gameOver) {
			Media hurt = new Media(new File("deathSoundEffect.mp3").toURI().toString());
			sound = new MediaPlayer(hurt);
			sound.play();
			winText = new Text("Player Two Wins");
			winText.setX(310);
			winText.setY(200);
			winText.setFill(Color.MAROON);
			winText.setFont(Font.font(java.awt.Font.SERIF, 50));
			if(playerOne.getDirection()) {
				playerOne.setImage("die");
			}else {
				playerOne.setImage("dieLeft");
			}
			gameOver = false;
			playerOne.setY(playerOne.groundHeight);
			fWorld.add(winText);
			fWorld.stop();
			restartBox = new HBox();
			ImageView i = new ImageView("restartButton.png");
			restartButton = new Button("",i);
			restartButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					restartGame();					
				}

			});
			restartButton.setPrefSize(100,20);
			restartBox.getChildren().addAll(restartButton);
			restartBox.setAlignment(Pos.CENTER);
			root.setCenter(restartBox);
		}
	}

	public static void updatePlayerTwoHealth() {

		playerTwoHealth.setText("Health: " + playerTwo.getHealth());
		if (playerTwo.getHealth() <= 0 && gameOver) {

			playerTwoHealth.setText("Health: " + playerTwo.getHealth());
			if(playerTwo.getHealth() <= 100) {
				playerOne.powerUp();
				System.out.println("updated");
			}
			if (playerTwo.getHealth() <= 0) {
				Media hurt = new Media(new File("deathSoundEffect.mp3").toURI().toString());
				sound = new MediaPlayer(hurt);
				sound.play();
				winText = new Text("Player One Wins");
				winText.setX(310);
				winText.setY(200);
				winText.setFill(Color.CORNFLOWERBLUE);
				winText.setFont(Font.font(java.awt.Font.SERIF, 50));
				if(playerTwo.getDirection()) {
					playerTwo.setImage("die");
				}else {
					playerTwo.setImage("dieLeft");
				}
				gameOver = false;
				playerTwo.setY(225);
				fWorld.add(winText);
				fWorld.stop();
				restartBox = new HBox();
				ImageView i = new ImageView("restartButton.png");
				restartButton = new Button("",i);
				restartButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						restartGame();					
					}

				});
				restartButton.setPrefSize(100,20);
				restartBox.getChildren().addAll(restartButton);
				restartBox.setAlignment(Pos.CENTER);
				root.setCenter(restartBox);
			}
		}
	}
}
