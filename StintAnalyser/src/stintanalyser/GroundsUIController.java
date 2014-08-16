package stintanalyser;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * GroundsUI Controller class. Handles all logic for the Grounds Library window.
 * @author Kieran
 */
public class GroundsUIController implements Initializable {
    
    private double initX;
    private double initY;
    private double initW;
    private double initH;
    
    @FXML
    private Button editBtn;
    @FXML
    private Button selectBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button cancelBtn;
    
    @FXML
    private AnchorPane pane;
    @FXML
    private TableView table;
    
    /*
     * Dragging starts.
     */
    @FXML
    private void dragStart(MouseEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        initX = event.getSceneX();
        initY = event.getSceneY();
        initW = pane.getWidth();
        initH = pane.getHeight();
        stage.setMaximized(false);
    }
    
    /*
     * Moving continues.
     */
    @FXML
    private void moveContinue(MouseEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        if (initY < 90) {
            stage.setX(event.getScreenX() - stage.getWidth()*initX/initW);
            stage.setY(event.getScreenY() - stage.getHeight()*initY/initH);
        }
    }
    
    /*
     * Resizing continues.
     */
    @FXML
    private void resizeContinue(MouseEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        double newW = initW + event.getSceneX() - initX;
        double newH = event.getSceneY() + initH - initY;
        if(newW < 600) {
            newW = 600;
        }
        if(newH < 400) {
            newH = 400;
        }
        stage.setWidth(newW);
        stage.setHeight(newH);
        pane.setCursor(Cursor.SE_RESIZE);
    }
    
    /*
     * Dragging end.
     */
    @FXML
    private void dragEnd(MouseEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        if(event.getScreenY() < 30) {
            stage.setMaximized(true);
            stage.setX(0);
            stage.setY(0);
        }
        pane.setCursor(Cursor.DEFAULT);
    }
    
    /*
     * Enter resize zone (cursor effects).
     */
    @FXML
    private void hover(MouseEvent event) {
        pane.setCursor(Cursor.SE_RESIZE);
    }
    
    /*
     * Leave resize zone (cursor effects).
     */
    @FXML
    private void leave(MouseEvent event) {
        pane.setCursor(Cursor.DEFAULT);
    }
    
    /*
     * Close dialogue.
     */
    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    /*
     * Add a new ground to the database.
     */
    @FXML
    private void addGround() {
    }
    
    /*
     * Edit the current ground.
     */
    @FXML
    private void editGround() {
    }
    
    /*
     * Select the ground for the current analysis.
     */
    @FXML
    private void selectGround() {
    }

    /**
     * Initializes the controller class. No logic is performed after
     * initialization as the methods and objects are FXML injected.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
