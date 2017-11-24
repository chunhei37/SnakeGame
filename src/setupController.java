import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Slider;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;
import java.util.Optional;

public class setupController {
	@FXML private Slider playerSlider;
	@FXML private CheckBox fillBots;
	
	private int playerNumbers; 
	private boolean fills;
	private ArrayList<Player> playerList = new ArrayList<Player>();

	@FXML
	protected void startGame(){
	      try{
	    	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainFrame.fxml"));
	            Parent root1 = (Parent) fxmlLoader.load();
	            Stage stage1 = new Stage();
	            stage1.setTitle("Snake And Ladder");
	            stage1.setScene(new Scene(root1, 1330, 795)); 
	            playerNumbers = (int)playerSlider.getValue();
	            fills = fillBots.selectedProperty().getValue();
	            addPlayer();
	            Game controller = fxmlLoader.<Game>getController();
	            controller.setPlayerList(playerList);
	            controller.initiate();
	            stage1.show();
	            stage1.setResizable(false);
	            Stage stage = (Stage) playerSlider.getScene().getWindow();
	            stage.close();
	          } catch(Exception e) {
	              e.printStackTrace();
	          }
	}
	
	protected void addPlayer() {
		TextInputDialog[] dialog = new TextInputDialog[playerNumbers];
		for(int i = 0; i < playerNumbers; i++) {
			dialog[i] = new TextInputDialog("Player " + (i + 1));
			dialog[i].setTitle("Input Player " + (i + 1) + " Name");
			dialog[i].setHeaderText("Player " + (i + 1) + " Name ?");
			dialog[i].setContentText("Please Enter Your Name:");
			dialog[i].setResizable(false);
			Optional<String> result = dialog[i].showAndWait();
			if (result.isPresent()) {
				if(dialog[i].getResult().equals("")) {
					playerList.add(new Player("Player " + (i + 1)));
				} else {
					playerList.add(new Player(dialog[i].getResult().toUpperCase()));
				}
			} else {
				playerList.add(new Player("Player " + (i + 1)));
			}

		}
		if(fills) {
			for(int i = dialog.length; i < 4; i++) {
				playerList.add(new Player("Bot " + i, true));
			}

		}
	}
	
}



