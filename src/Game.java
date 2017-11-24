import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import java.util.ArrayList;


public class Game {
	@FXML private Text currentPlayer;
	@FXML private Text currentPlayerPlace;
	@FXML private Text nameLabel = new Text();
	@FXML private Button rollDice;
	@FXML private Pane currentPlayerPane;
	@FXML private AnchorPane gamePane;
	@FXML private Circle playerColor = new Circle();
	@FXML private StackPane rotaryBoard = new StackPane();
	@FXML private GridPane rotaryBase = new GridPane();
	@FXML private Text dicePoint = new Text();
	
	@FXML private Text player1Score = new Text();
	@FXML private Text player2Score = new Text();
	@FXML private Text player3Score = new Text();
	@FXML private Text player4Score = new Text();
	
	@FXML private Label player1Name = new Label();
	@FXML private Label player2Name = new Label();
	@FXML private Label player3Name = new Label();
	@FXML private Label player4Name = new Label();
	
	private ArrayList<Player> playerList;
	private int turn = 0;
	private Board board = new Board();
	private Circle[] player;
	private String[][] pos = new String[10][10];
	private ImageView b2;
	
	
	@FXML
	public void roll() {
		mainGame();
	}
	
	public void mainGame() {
		Dice dice = new Dice();
		dicePoint.setText("" + dice.getDicePoint());
		playerList.get(turn).addCurrentScore(dice.getDicePoint());
		playerList.get(turn).setOldScore(playerList.get(turn).getCurrentScore());
		if(checkWin()) {

			playerList.get(turn).setCurrentScore(100);
			updateCurrentPlayer(turn);
//			move(turn);
		
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Winner");
			alert.setContentText(playerList.get(turn).getName() + " Won!");
			alert.showAndWait();
			
			Stage stage = (Stage) rollDice.getScene().getWindow();
			stage.close();
			
		} else {
			moveDelay(1, turn);
			int newScore = calScore(checkCellEvent(calCell(turn)));
			playerList.get(turn).setCurrentScore(newScore);
			System.out.println("Player: " + turn + " place: "+ playerList.get(turn).getCurrentScore());
			if(newScore != playerList.get(turn).getOldScore()) {
				moveDelay(1, turn);
			}

			turn++;
			if(turn == playerList.size()) {
				turn = 0;
			}
			updateCurrentPlayer(turn);
			if(playerList.get(turn).isBot()) {
				Timeline moveTime = new Timeline(
						new KeyFrame(Duration.seconds(1),
						new EventHandler<ActionEvent>() {
						@Override 
						public void handle(ActionEvent actionEvent) {
							mainGame();
							}
						}));
				moveTime.setCycleCount(1);
				moveTime.play();
			} 
		}

	}
	
	public int[] calCell(int turn) {
		int[] cell = new int[2];
		int x = (playerList.get(turn).getCurrentScore() - 1) % 10;
		int y = (playerList.get(turn).getCurrentScore() - 1) / 10;
		cell[0] = y;
		if(y % 2 == 0) {
			cell[1] = x;
		} else {
			cell[1] = 9 - x;
		}
		return cell;
	}
	
	public int calScore(int[] cell) {
		int y = cell[0];
		int x = cell[1];
		int score = 0;
		if(y % 2 == 0) {
			score = x + 1 + y * 10;
		} else {
			score = (y + 1) * 10 - x;
		}
		return score;
	}
	
	public int[] checkCellEvent(int[] cell){
		int[] dest = new int[2];
		if(board.checkEvent(cell).isLadderBottom()) {
			dest = board.checkEvent(cell).getDestination();
		} else {
			if(board.checkEvent(cell).isSnakeHead()) {
				dest = board.checkEvent(cell).getDestination();
			} else {
				dest = cell;
			}
		}
		return dest;
	}
	
	public void move(int turn) {
		double cx = 0;
		double cy = 0;
			int[] posXY = calPos(calCell(turn));
			if(((int)(Math.random() * 10 + 1)) % 2 == 0) {
			    cx = posXY[1] + (Math.random() * 20);
			    cy = posXY[0] + (Math.random() * 20);
			} else {
			    cx = posXY[1] - (Math.random() * 20);
			    cy = posXY[0] - (Math.random() * 20);
			}
			 
		     Path path = new Path();
		     path.getElements().add (new MoveTo(player[turn].getCenterX(), player[turn].getCenterY()));

		     path.getElements().add(new LineTo(cx, cy));
		     
		     PathTransition pathTransition = new PathTransition();
		     pathTransition.setDuration(Duration.millis(900));
		     pathTransition.setNode(player[turn]);
		     pathTransition.setPath(path);
		     pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
		     pathTransition.setCycleCount(0);
		     pathTransition.setAutoReverse(false);
		 
		     pathTransition.play();
		     
		     player[turn].setCenterX(cx);
		     player[turn].setCenterY(cy);


	}
	
	public void moveDelay(int second, int turn) {
		rollDice.setDisable(true);
		move(turn);
		Timeline moveTime = new Timeline(
				new KeyFrame(Duration.seconds(second),
				new EventHandler<ActionEvent>() {
				@Override 
				public void handle(ActionEvent actionEvent) {
					rollDice.setDisable(false);	
					}
				}));
		moveTime.setCycleCount(1);
		moveTime.play();
	}
	
	public boolean checkWin() {
		boolean flag = false;
		for(Player p : playerList) {
			if(p.getCurrentScore() >= 100 || p.getCurrentScore() == 80) {
				flag = true;
			}
		}
		return flag;
	}
	
	public int[] calPos(int[] cell) {
		int[] toPos = new int[2];
		String[] posCo = pos[cell[0]][cell[1]].split(",");
		for(int i = 0; i < posCo.length; i++) {
			toPos[i] = Integer.parseInt(posCo[i]);
		}
		
		return toPos;
	}
	
	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}
	
	public void updateCurrentPlayer(int num) {
		currentPlayer.setText(playerList.get(num).getName());
		playerColor.setFill(playerList.get(num).getColor());
		nameLabel.setText("" + playerList.get(num).getName().charAt(0));
		try {
			player1Score.setText("" + playerList.get(0).getCurrentScore());
			player2Score.setText("" + playerList.get(1).getCurrentScore());
			player3Score.setText("" + playerList.get(2).getCurrentScore());
			player4Score.setText("" + playerList.get(3).getCurrentScore());
		} catch(Exception e) {
			
		}
		
	}
	
//	public void createRoll() {
//		ImageView b1 = new ImageView();
//		b1.setImage(new Image("img/rotary.png"));
//
//		b2 = new ImageView(); 
//		b2.setImage(new Image("img/l.png"));
//		b2.setScaleY(0.8);
//		b2.setTranslateY(-50);
//		
//		Circle b3 = new Circle(15, Color.DARKGOLDENROD);
//		b3.setStrokeType(StrokeType.OUTSIDE);
//		b3.setStroke(Color.BLACK);
//		b3.setStrokeWidth(1);
//
//		rotaryBoard.getChildren().addAll(b1, b2, b3);
//		rotaryBase.toFront();
//	}
	
	public void initiate() {
		updateCurrentPlayer(0);
		player = new Circle[playerList.size()];
		for(int i = 0; i < playerList.size(); i++) {
			System.out.println(playerList.get(i).getName());
			player[i] = new Circle();
			player[i].setRadius(20);
			Color color = Color.rgb((int)(Math.random() * 255  + 1), (int)(Math.random() * 255  + 1), (int)(Math.random() * 255  + 1));
			player[i].setFill(color);
			playerList.get(i).setColor(color);
			player[i].setStroke(Color.BLACK);
			gamePane.getChildren().add(player[i]);
			player[i].setCenterY(45 + 80 * 9 + 60);
			player[i].setCenterX(-45 + Math.random() * 25 + 1);
			playerColor.setFill(playerList.get(i).getColor());
			}

		for(int i = 0; i < 10; i++) {
			for(int p = 0; p < 10; p++) {
				pos[9 - i][p] = "" + (45 + 80 * i) + "," + (45 + 80 * p);
			}
		}

		try {
			player1Name.setText(playerList.get(0).getName());
			player2Name.setText(playerList.get(1).getName());
			player3Name.setText(playerList.get(2).getName());
			player4Name.setText(playerList.get(3).getName());
		} catch(Exception e) {
			
		}
	}
	
}
