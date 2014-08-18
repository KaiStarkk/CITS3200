package StintAnalyser.UI;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import StintAnalyser.Utilities.IO;

/**
 * MainUI Controller class. Handles all logic for the main UI window.
 * @author Kieran
 */
public class MainUIController implements Initializable {
    
    private double initX;
    private double initY;
    private double initW;
    private double initH;
    
    @FXML
    private Button openBtn;
    @FXML
    private Button groundsBtn;
    @FXML
    private Hyperlink minBtn;
    @FXML
    private Hyperlink helpBtn;
    @FXML
    private Hyperlink exitBtn;
    
    @FXML
    private AnchorPane pane;
    @FXML
    private TreeView tree;
    
    DirectoryChooser directoryChooser = new DirectoryChooser();
    
    
    @FXML
    private Label statusLbl;
    @FXML
    private Label detailsLbl;
    @FXML
    private Button processBtn;
    @FXML
    private ProgressIndicator spinner;
    
    private IO io;
    
    private String rootPath;
    private String selectedPath;
    private String details;
    private String fileExtension;

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
        if (initY < 90 && initX < initW - 85 || initY < 90 && initY > 40 ) {
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
        if(newW < 1280) {
            newW = 1280;
        }
        if(newH < 720) {
            newH = 720;
        }
        stage.setWidth(newW);
        stage.setHeight(newH);
        pane.setCursor(Cursor.SE_RESIZE);
    }
    
    /*
     * Dragging ends.
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
     * Load a game.
     */
    @FXML
    private void processGame(ActionEvent event) {
        final Timeline timeline = new Timeline();
        spinner.setVisible(true);
        spinner.setProgress(0);
        
        timeline.setCycleCount(1);
        final KeyValue kv = new KeyValue(spinner.progressProperty(), 1);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(5000), kv);
        timeline.getKeyFrames().add(kf1);
        timeline.play();
    }
    
    /*
     * Load root directory.
     */
    @FXML
    private void openDirectory(ActionEvent event) {
        Stage stage = (Stage) openBtn.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        if (file != null && file.isDirectory()) {
            rootPath = file.getPath();
            tree.setRoot(new TreeItem(file.getName()));
            tree.getRoot().setExpanded(true);
            expand(tree.getRoot(), file.listFiles());
        }
    }
        
    /*
     * Expand a tree item.
     */
    private void expand(TreeItem item, File[] files) {
        for (File file : files) {
            if (file.isFile()) {
                item.getChildren().add(new TreeItem(file.getName()));
            }
            if (file.isDirectory()) {
                TreeItem folder = new TreeItem(file.getName());
                item.getChildren().add(folder);
                expand(folder, file.listFiles());
            }
        }
    }
      
    /*
     * Show the grounds UI.
     */
    @FXML
    private void showGroundsUI(ActionEvent event) throws Exception {
        final Stage groundsStage = new Stage(StageStyle.UNDECORATED);
        
        groundsStage.initModality(Modality.WINDOW_MODAL);
        groundsStage.initOwner(groundsBtn.getScene().getWindow());
        Parent root = FXMLLoader.load(getClass().getResource("GroundsUI.fxml"));
        
        Scene scene = new Scene(root);
        
        groundsStage.setScene(scene);
        groundsStage.show();
    }
    
    /*
     * Minimise the window.
     */
    @FXML
    private void minimise(ActionEvent event) {
        Stage stage = (Stage) minBtn.getScene().getWindow();
        stage.setIconified(true);
    }
    
    /*
     * Show help.
     */
    @FXML
    private void showHelp(ActionEvent event) {
        final Stage stage = (Stage) helpBtn.getScene().getWindow();
        final Popup popup = new Popup();
        Label message = new Label("Help is on its way!");
        popup.getContent().addAll(message);
        
        popup.setX(stage.getX() + stage.getWidth() - 15);
        popup.setY(stage.getY() + 35);
        
        popup.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_TOP_RIGHT);
        popup.setAutoHide(true);
        popup.show(helpBtn.getScene().getWindow());
    }
    
    /*
     * Close application.
     */
    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }
    
    /*
     * Initializes the controller class. No logic is performed after
     * initialization as the methods and objects are FXML injected.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        io = new IO();
        
        tree.getSelectionModel().selectedItemProperty().addListener( (ObservableValue observable, Object oldValue, Object newValue) -> {
            TreeItem<String> currentItem = (TreeItem<String>) newValue;
            
            try {
                fileExtension = "none";
                details = "";
                processBtn.setDisable(true);
                
                int separator = currentItem.getValue().lastIndexOf('.');
                if (separator > 0) {
                    fileExtension = currentItem.getValue().substring(separator + 1);
                }
                
                String relPath = "";
                
                if (!currentItem.isLeaf()) {
                    relPath += "\\";
                    fileExtension = "folder";
                }
                
                while (currentItem.getParent() != null) {
                    relPath = "\\" + currentItem.getValue() + relPath;
                    currentItem = currentItem.getParent();
                }
                
                selectedPath = rootPath + relPath;
                details = "Path: " + selectedPath + "\nExtension type: " + fileExtension + "\n";
                if (fileExtension.equals("csv")) {
                    details += "Type recognised as comma separated values.";
                    processBtn.setDisable(false);
                }
                
                statusLbl.setText("Selected Item Details:");
                detailsLbl.setText(details);
            } catch (NullPointerException e) {
                // closing a tree node above selected value
            }
        });
    }    
    
}
