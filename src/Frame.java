import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Frame extends Application {
	 
    @Override
    public void start(Stage primaryStage) throws IOException{
    	Parent root = FXMLLoader.load(getClass().getResource("introFrame.fxml"));
 
    	Scene scene = new Scene(root, 390, 490);
    	primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Snake And Ladder");
		primaryStage.show();
    }
}
