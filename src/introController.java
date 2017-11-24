
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class introController {
	
	@FXML Button exitBtn;
	
	@FXML
	protected void setup() {
	      try{
	    	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("setupFrame.fxml"));
	            Parent root1 = (Parent) fxmlLoader.load();
	            Stage stage1 = new Stage();
	            stage1.setTitle("Snake And Ladder");
	            stage1.setScene(new Scene(root1, 390, 490));
	            stage1.setResizable(false);
	            exit();
	            stage1.show();
	          } catch(Exception e) {
	              e.printStackTrace();
	          }
	}
	
	
	@FXML
	protected void credits() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Credits");
		alert.setHeaderText(null);
		alert.setContentText("Program By ChunHei Yuen \nThank You For the Resource From The Internet!\n UI Pictures From: http://dycha.ne \n Board Pictures From: www.fun-free-party-games.com");

		alert.showAndWait();
	}
	 
	@FXML 
	protected void exit() {
		Stage stage = (Stage) exitBtn.getScene().getWindow();
		stage.close();
	}
	
	
}
