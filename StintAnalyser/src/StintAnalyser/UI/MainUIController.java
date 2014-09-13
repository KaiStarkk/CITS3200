package StintAnalyser.UI;

import StintAnalyser.Grounds.GroundsIO;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private Button loadBtn;
    
    @FXML
    private AnchorPane pane;
    @FXML
    private AnchorPane processPane;
    @FXML
    private TreeView tree;
    
    DirectoryChooser directoryChooser = new DirectoryChooser();
    FileChooser fileChooser = new FileChooser();
    
    
    @FXML
    private Label statusLbl;
    @FXML
    private TextArea gamePeriods;
    @FXML
    private Button processBtn;
    @FXML
    private ProgressIndicator spinner;
    
    @FXML
    private ListView playerList;
    @FXML
    private ComboBox groundsBox;
    
    private String rootPath;
    private String selectedPath;
    private String fileExtension;
    private GroundsIO groundsIO;
    private File groundsFile;

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
            expandTreeItem(tree.getRoot(), file.listFiles());
        }
    }
        
    /*
     * Expand a tree item.
     */
    private void expandTreeItem(TreeItem item, File[] files) {
        for (File file : files) {
            if (file.isFile()) {
                item.getChildren().add(new TreeItem(file.getName()));
            }
            if (file.isDirectory()) {
                TreeItem folder = new TreeItem(file.getName());
                item.getChildren().add(folder);
                expandTreeItem(folder, file.listFiles());
            }
        }
    }
      
    /*
     * Show the grounds UI.
     */
    @FXML
    private void selectGroundsLibrary(ActionEvent event) throws Exception {
        Stage stage = (Stage) groundsBtn.getScene().getWindow();
        groundsFile = fileChooser.showOpenDialog(stage);
        groundsIO = new GroundsIO(groundsFile);
        
        groundsBox.setItems(FXCollections.observableArrayList(groundsIO.groundNames()));
    }
    
    /*
     * Load game
     */
    @FXML
    private void loadGame(ActionEvent event) {
        if (groundsIO == null) {
            statusLbl.setText("Please select a grounds library file, then load the chosen item again.");
        } else {
            try {
                TreeItem<String> currentItem = (TreeItem<String>) tree.getSelectionModel().getSelectedItems().get(0);
                fileExtension = "none";
                processPane.setDisable(true);

                ArrayList<String> players = new ArrayList<>();
                playerList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

                int separator = currentItem.getValue().lastIndexOf('.');
                if (separator > 0) {
                    fileExtension = currentItem.getValue().substring(separator + 1);
                }
                
                String relPath = "";

                if (!currentItem.isLeaf()) {
                    relPath += "\\";
                    fileExtension = "folder";
                }

                TreeItem<String> traceItem = currentItem;

                while (traceItem.getParent() != null) {
                    relPath = "\\" + traceItem.getValue() + relPath;
                    traceItem = traceItem.getParent();
                }

                selectedPath = rootPath + relPath;
                if (fileExtension.equals("csv")) {
                    statusLbl.setText("Single player selected:");
                    players.add(currentItem.getValue());
                    processPane.setDisable(false);
                } else if (fileExtension.equals("folder")) {
                    int found = explore(selectedPath, players);
                    statusLbl.setText((found == 1) ? "1 player found." : found + " players found.");
                    if (found > 0) {
                        processPane.setDisable(false);
                    }
                } else {
                    statusLbl.setText("No players found.");                
                }

                playerList.setItems(FXCollections.observableArrayList(players));
            } catch (NullPointerException e) {
                // closing a tree node above selected value
            }
        }
    }
    
    /*
     * Search inside selected game for .csv files
     */
    private int explore(String path, ArrayList<String> players) {
        File file = new File(path);
        int count = 0;
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            for (File child : children) {
            int separator = child.getAbsolutePath().lastIndexOf('.');
                if (child.getAbsolutePath().substring(separator + 1).equals("csv")) {
                    players.add(child.getName());
                    count++;
                }
            }
        }
        return count;
    }
      
    /*
     * Process selected players.
     */
    @FXML
    private void processPlayers(ActionEvent event) {
        int[][] periods = getPeriods();
        ObservableList selectedPlayers = playerList.getSelectionModel().getSelectedItems();
        if (selectedPlayers.isEmpty()) {
            statusLbl.setText("Select at least one player from the list.");
        } else if (groundsBox.getSelectionModel().isEmpty()) {
            statusLbl.setText("Select a ground from the dropdown menu."); 
        } else if (periods == null) {
            statusLbl.setText("Enter game periods in the correct format.");            
        } else {
            final Timeline timeline = new Timeline();
            spinner.setVisible(true);
            spinner.setProgress(0);

            timeline.setCycleCount(1);
            final KeyValue kv = new KeyValue(spinner.progressProperty(), 1);
            final KeyFrame kf1 = new KeyFrame(Duration.millis(5000), kv);
            timeline.getKeyFrames().add(kf1);
            timeline.play();  
        }
    }
    
    private int[][] getPeriods() {
        return null;
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
    }    
}
