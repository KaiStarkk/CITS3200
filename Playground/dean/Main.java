package dean;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;


public class Main extends Application {
	@Override
	public void start(final Stage stage) throws Exception
	   {
	      final Circle circ = new Circle(40, 40, 30);
	      final Group root = new Group(circ);
	      final Scene scene = new Scene(root, 400, 300);

	      stage.setTitle("Hello JavaFX 2.0!");
	      stage.setScene(scene);
	      stage.show();
	   }
	
	public static void main(String[] args) {
		launch(args);
	}
}
