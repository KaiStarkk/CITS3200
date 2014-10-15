package StintAnalyser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * CITS3200 Professional Computing
 * Main.java
 * 
 * Entry point for the application. Starts a JavaFX application,
 * creates a stage and scene based on MainUIController and MainUI.fxml,
 * then launches the GUI.
 *
 * @version 1.1 20/08/14
 * @author Group B
 */
public class Main extends Application {
    
    /**
     * Starts the FXML loading process.
     * @throws java.lang.Exception thrown if failed opening the GUI
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("UI/MainUI.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("StintAnalyser");
        primaryStage.show();
    }    

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}