package stintanalyser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The Main main class. A JavaFX application.
 * @author Kieran
 */
public class Main extends Application {
    
    /**
     * Starts the FXML loading process.
     * @throws java.lang.Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("StintAnalyser");
        primaryStage.show();
    }

    /**
     * Begins program execution.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
