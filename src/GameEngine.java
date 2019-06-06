<<<<<<< HEAD
import java.io.File;
import javafx.scene.paint.Color;
import java.util.TimerTask;
=======
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
>>>>>>> branch 'master' of https://rishiskhare@bitbucket.org/2019_p4_group_2/gameproject.git

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameEngine extends Application {
	private static Player playerOne;
	private static Player playerTwo;
	public static Text playeOneHealth;
	public static Text playerTwoHealth;
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
	
	public static boolean gameOver = true;
	public static Button restartButton;
	public static Button menuButton;
	public static BorderPane root;
	static HBox restartBox;
	static Text winText;
<<<<<<< HEAD
	ImageView backgroundImageView;
=======

	private static HealthBar p1HealthBar;
	private static HealthBar p2HealthBar;
	private double p1healthX;
	private double p1healthY;
	private double p1healthWidth;
	private double p1healthHeight;
	private double p2healthX;
	private double p2healthY;
	private double p2healthWidth;
	private double p2healthHeight;

>>>>>>> branch 'master' of https://rishiskhare@bitbucket.org/2019_p4_group_2/gameproject.git
	public static void main(String[] args) {
		launch();
	}

	private static FighterWorld fWorld = new FighterWorld();

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Fighter");
		root = new BorderPane();
<<<<<<< HEAD
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

=======
		scene = new Scene(root, 1000, 500);
		playerOne = new Adventurer(true);
		playerTwo = new Minotaur(false);
		
		Media hurt = new Media(new File("src/resources/backgroundMusic.mp3").toURI().toString());
		sound = new MediaPlayer(hurt);		
		sound.setCycleCount(MediaPlayer.INDEFINITE);
		sound.setVolume(0.2);
		sound.play();	

		p1healthWidth = playerOne.defaultHealth;
		p1healthHeight = 20;
		p1healthX = 20;
		p1healthY = 20;

		p2healthWidth = playerTwo.defaultHealth;
		p2healthHeight = 20;
		p2healthX = 800;
		p2healthY = 20;
>>>>>>> branch 'master' of https://rishiskhare@bitbucket.org/2019_p4_group_2/gameproject.git

		p1HealthBar = new HealthBar(playerOne.defaultHealth, p1healthWidth, p1healthHeight, p1healthX, p1healthY,
				playerOne);
		p2HealthBar = new HealthBar(playerTwo.defaultHealth, p2healthWidth, p2healthHeight, p2healthX, p2healthY,
				playerTwo);

		Image image = new Image("resources/ArenaBackground.jpg");
		ImageView backgroundImageView = new ImageView(image);
		backgroundImageView.setFitHeight(scene.getHeight());
		backgroundImageView.setFitWidth(scene.getWidth());
		backgroundImageView.relocate(0, 0);
		root.getChildren().add(backgroundImageView);

		fWorld.add(playerOne);
		fWorld.add(playerTwo);
<<<<<<< HEAD
		
//		fWorld.add(cupcakeHealth);
//		fWorld.add(iceCreamHealth);
		fWorld.add(p1HealthBar);
		fWorld.add(p2HealthBar);

=======

		fWorld.add(p1HealthBar);
		fWorld.add(p2HealthBar);
>>>>>>> branch 'master' of https://rishiskhare@bitbucket.org/2019_p4_group_2/gameproject.git
		root.getChildren().add(fWorld);
		root.setAlignment(fWorld, Pos.CENTER);

		// Code for menu
		HBox menuBox = new HBox(10);
		Menu characterMenu = new Menu("Choose Characters");
		MenuBar menuBar = new MenuBar();
		MenuItem choosePlayerOne = new MenuItem("Choose Player One Character");
		choosePlayerOne.setOnAction((new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				playerOneSelectionScreen();
			}
		}));
		MenuItem choosePlayerTwo = new MenuItem("Choose Player Two Character");
		choosePlayerTwo.setOnAction((new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				playerTwoSelectionScreen();
			}
		}));
		menuBar.getMenus().add(characterMenu);
		characterMenu.getItems().add(choosePlayerOne);
		characterMenu.getItems().add(choosePlayerTwo);
		menuBox.getChildren().add(menuBar);
		menuBox.setAlignment(Pos.TOP_LEFT);
		menuBox.setHgrow(menuBar, Priority.ALWAYS);
		root.setTop(menuBox);
		//

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
				if (!playerOne.getWorld().isKeyDown(KeyCode.E) && gameOver) {
					playerOne.shoot();
				}
				fWorld.addKeyCode(KeyCode.E);
			}
			if (event.getCode() == KeyCode.P) {
				if (!playerOne.getWorld().isKeyDown(KeyCode.P) && gameOver) {
					playerTwo.shoot();
				}
				fWorld.addKeyCode(KeyCode.P);

			}
			if (event.getCode() == KeyCode.W) {
				if (!playerOne.getWorld().isKeyDown(KeyCode.W) && gameOver) {
					playerOne.jump();
				}
				fWorld.addKeyCode(KeyCode.W);
			}
			if (event.getCode() == KeyCode.UP) {
				if (!playerOne.getWorld().isKeyDown(KeyCode.UP) && gameOver) {
					playerTwo.jump();
				}
				fWorld.addKeyCode(KeyCode.UP);
			}
			if (event.getCode() == KeyCode.F) {
				if (!playerOne.getWorld().isKeyDown(KeyCode.F) && gameOver) {
					playerOne.attack();
				}
				fWorld.addKeyCode(KeyCode.F);
			}
			if (event.getCode() == KeyCode.O) {
				if (!playerOne.getWorld().isKeyDown(KeyCode.O) && gameOver) {
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
			
			
			

		});
	}

//Selection Screen Methods

	public static void playerOneSelectionScreen() {
		VBox selectionBox = new VBox(10);
		HBox rowOne = new HBox(10);
		HBox rowTwo = new HBox(10);
		fWorld.stop();
		Button adventurerButton = new Button("Adventurer");
		Button minotaurButton = new Button("Minotaur");
		Button santaButton = new Button("Santa");
		Button cyclopsButton = new Button("Cyclops");
		Button dwarfButton = new Button("Dwarf");
		Button gladiatorButton = new Button("Gladiator");
		gladiatorButton.setPrefSize(100, 20);
		dwarfButton.setPrefSize(100, 20);
		cyclopsButton.setPrefSize(100, 20);
		santaButton.setPrefSize(100, 20);
		adventurerButton.setPrefSize(100, 20);
		minotaurButton.setPrefSize(100, 20);
		rowOne.getChildren().addAll(adventurerButton, minotaurButton, santaButton);
		rowOne.setAlignment(Pos.CENTER);
		rowTwo.getChildren().addAll(cyclopsButton, dwarfButton, gladiatorButton);
		rowTwo.setAlignment(Pos.CENTER);
		selectionBox.getChildren().addAll(rowOne, rowTwo);
		selectionBox.setAlignment(Pos.CENTER);
		root.setCenter(selectionBox);
		adventurerButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fWorld.remove(playerOne);
				playerOne = new Adventurer(true);
				fWorld.add(playerOne);
				root.getChildren().removeAll(selectionBox);
				restartGame();
			}
		});
		minotaurButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fWorld.remove(playerOne);
				playerOne = new Minotaur(true);
				fWorld.add(playerOne);
				root.getChildren().removeAll(selectionBox);
				restartGame();
			}
		});
		santaButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fWorld.remove(playerOne);
				playerOne = new Santa(true);
				fWorld.add(playerOne);
				root.getChildren().removeAll(selectionBox);
				restartGame();
			}
		});
		cyclopsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fWorld.remove(playerOne);
				playerOne = new Cyclops(true);
				fWorld.add(playerOne);
				root.getChildren().removeAll(selectionBox);
				restartGame();
			}
		});
		dwarfButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fWorld.remove(playerOne);
				playerOne = new Dwarf(true);
				fWorld.add(playerOne);
				root.getChildren().removeAll(selectionBox);
				restartGame();
			}
		});
		gladiatorButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fWorld.remove(playerOne);
				playerOne = new Gladiator(true);
				fWorld.add(playerOne);
				root.getChildren().removeAll(selectionBox);
				restartGame();
			}
		});
	}

	public static void playerTwoSelectionScreen() {
		VBox selectionBox = new VBox(10);
		HBox rowOne = new HBox(10);
		HBox rowTwo = new HBox(10);
		fWorld.stop();
		Button adventurerButton = new Button("Adventurer");
		Button minotaurButton = new Button("Minotaur");
		Button santaButton = new Button("Santa");
		Button cyclopsButton = new Button("Cyclops");
		Button dwarfButton = new Button("Dwarf");
		Button gladiatorButton = new Button("Gladiator");
		gladiatorButton.setPrefSize(100, 20);
		dwarfButton.setPrefSize(100, 20);
		cyclopsButton.setPrefSize(100, 20);
		santaButton.setPrefSize(100, 20);
		adventurerButton.setPrefSize(100, 20);
		minotaurButton.setPrefSize(100, 20);
		rowOne.getChildren().addAll(adventurerButton, minotaurButton, santaButton);
		rowOne.setAlignment(Pos.CENTER);
		rowTwo.getChildren().addAll(cyclopsButton, dwarfButton, gladiatorButton);
		rowTwo.setAlignment(Pos.CENTER);
		selectionBox.getChildren().addAll(rowOne, rowTwo);
		selectionBox.setAlignment(Pos.CENTER);
		root.setCenter(selectionBox);
		adventurerButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fWorld.remove(playerTwo);
				playerTwo = new Adventurer(false);
				fWorld.add(playerTwo);
				root.getChildren().removeAll(selectionBox);
				restartGame();
			}
		});
		minotaurButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fWorld.remove(playerTwo);
				playerTwo = new Minotaur(false);
				fWorld.add(playerTwo);
				root.getChildren().removeAll(selectionBox);
				restartGame();
			}
		});
		santaButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fWorld.remove(playerTwo);
				playerTwo = new Santa(false);
				fWorld.add(playerTwo);
				root.getChildren().removeAll(selectionBox);
				restartGame();
			}
		});
		cyclopsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fWorld.remove(playerTwo);
				playerTwo = new Cyclops(false);
				fWorld.add(playerTwo);
				root.getChildren().removeAll(selectionBox);
				restartGame();
			}
		});
		dwarfButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fWorld.remove(playerTwo);
				playerTwo = new Dwarf(false);
				fWorld.add(playerTwo);
				root.getChildren().removeAll(selectionBox);
				restartGame();
			}
		});
		gladiatorButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fWorld.remove(playerTwo);
				playerTwo = new Gladiator(false);
				fWorld.add(playerTwo);
				root.getChildren().removeAll(selectionBox);
				restartGame();
			}
		});
	}

	public static void restartGame() {
		sound.play();
		playerOne.setX(200);
		playerOne.setY(playerOne.yPos);
		playerTwo.setX(600);
		playerTwo.setY(playerTwo.yPos);
		playerOne.setHealth(playerOne.defaultHealth);
		playerTwo.setHealth(playerTwo.defaultHealth);
		p1HealthBar.player = playerOne;
		p2HealthBar.player = playerTwo;
		p1HealthBar.setFitWidth(playerOne.defaultHealth);
		p2HealthBar.setFitWidth(playerTwo.defaultHealth);
		playerOne.setDirection(true);
		playerTwo.setDirection(false);
		playerOne.setImage("idle");
		playerTwo.setImage("idleLeft");
		playerOne.setMeleeDamage(6);
		playerTwo.setMeleeDamage(8);
		playerOne.playerPowerDown();
		playerTwo.playerPowerDown();
		List<Bullet> bullets = fWorld.getObjects(Bullet.class);
		for(int i = 0 ; i< bullets.size(); i++) {
			fWorld.remove(bullets.get(i));
		}
		updatePlayerOneHealth();
		updatePlayerTwoHealth();
		root.getChildren().remove(restartBox);
		fWorld.remove(winText);
		gameOver = true;
		fWorld.start();
	}

<<<<<<< HEAD
	public static void playHurtSound() {
		Media hurt = new Media(new File("file:resources/hurtSoundEffect.mp3").toURI().toString());
		sound = new MediaPlayer(hurt);
		sound.play();
	}

	public static void playBulletSound() {
		Media hurt = new Media(new File("file:resources/bulletSoundEffect.mp3").toURI().toString());
		sound = new MediaPlayer(hurt);
		sound.play();
	}

	public static void updatePlayerOneHealth() {
		Media hurt;
		if (playerOne.getHealth() > 0) {
			p1HealthBar.setHealth(playerOne.getHealth());
		}
		else {
			fWorld.remove(p1HealthBar);
		}
		if (playerOne.getHealth() <= 0) {
			hurt = new Media(new File("deathSoundEffect.mp3").toURI().toString());
		if(playerOne.getHealth() <= 80) {
			playerOne.powerUp();
			System.out.println("updated");
=======
	public static void updatePlayerOneHealth() {
		if (playerOne.getHealth() <= 80) {
			playerOne.playerPowerUp();
>>>>>>> branch 'master' of https://rishiskhare@bitbucket.org/2019_p4_group_2/gameproject.git
		}
<<<<<<< HEAD
		if (playerOne.getHealth() <= 0&& gameOver) {
			hurt = new Media(new File("file:resources/deathSoundEffect.mp3").toURI().toString());
			sound = new MediaPlayer(hurt);
			sound.play();
=======
		if (playerOne.getHealth() <= 0 && gameOver) {
			sound.pause();
			p1HealthBar.setFitWidth(0);
>>>>>>> branch 'master' of https://rishiskhare@bitbucket.org/2019_p4_group_2/gameproject.git
			winText = new Text("Player Two Wins");
			winText.setX(310);
			winText.setY(200);
			winText.setFill(Color.MAROON);
			winText.setFont(Font.font(java.awt.Font.SERIF, 50));
			playerOne.playDeathSound();
			if (playerOne.getDirection()) {
				playerOne.setImage("die");
			} else {
				playerOne.setImage("dieLeft");
			}
			gameOver = false;
			playerOne.setY(playerOne.yPos);
			fWorld.add(winText);
			fWorld.stop();
			restartBox = new HBox();
			ImageView i = new ImageView("resources/restartButton.png");
			restartButton = new Button("", i);
			restartButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					restartGame();
				}

			});
			restartButton.setPrefSize(100, 20);
			restartBox.getChildren().addAll(restartButton);
			restartBox.setAlignment(Pos.CENTER);
			root.setCenter(restartBox);
		}
		}
	}
<<<<<<< HEAD

	public static void updatePlayerTwoHealth() {
		if (playerTwo.getHealth() > 0) {
			p2HealthBar.setHealth(playerTwo.getHealth());
=======

	public static void updatePlayerTwoHealth() {
		if (playerTwo.getHealth() <= 80 && !playerTwo.poweredUp) {
			playerTwo.playerPowerUp();

>>>>>>> branch 'master' of https://rishiskhare@bitbucket.org/2019_p4_group_2/gameproject.git
		}
<<<<<<< HEAD
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

					playerTwo.setImage("dieLeft");
				}
				gameOver = false;
				playerTwo.setY(225);
				fWorld.add(winText);
				fWorld.stop();
				restartBox = new HBox();
				ImageView i = new ImageView("file:resources/restartButton.png");
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

=======
		if (playerTwo.getHealth() <= 0 && gameOver) {
			sound.pause();
			p2HealthBar.setFitWidth(0);
			winText = new Text("Player One Wins");
			winText.setX(310);
			winText.setY(200);
			winText.setFill(Color.CORNFLOWERBLUE);
			winText.setFont(Font.font(java.awt.Font.SERIF, 50));
			fWorld.add(winText);
			fWorld.stop();
			playerTwo.playDeathSound();
			if (playerTwo.getDirection()) {
				playerTwo.setImage("die");
			} else {
				playerTwo.setImage("dieLeft");
>>>>>>> branch 'master' of https://rishiskhare@bitbucket.org/2019_p4_group_2/gameproject.git
			}
			gameOver = false;
			playerTwo.setY(playerTwo.yPos);
			restartBox = new HBox();
			ImageView i = new ImageView("resources/restartButton.png");
			restartButton = new Button("", i);
			restartButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					restartGame();
				}
			});
			restartButton.setPrefSize(100, 20);
			restartBox.getChildren().addAll(restartButton);
			restartBox.setAlignment(Pos.CENTER);
			root.setCenter(restartBox);
		}
	}
}
//do cyclops
