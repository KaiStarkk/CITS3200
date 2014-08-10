/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stinteditor;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kieran
 */
public class UIController implements Initializable {
    
    private double initX;
    private double initY;
    private double initW;
    private double initH;
    
    @FXML
    private Button load;
    @FXML
    private Button grounds;
    @FXML
    private Button help;
    @FXML
    private Button exit;
    
    @FXML
    private AnchorPane pane;
    @FXML
    private TreeView tree;
    @FXML
    DirectoryChooser directoryChooser = new DirectoryChooser();
    
    /**
     * Show the grounds UI.
     */
    @FXML
    private void groundsUI(ActionEvent event) {
        // Stuff happens here.
    }
    
    /**
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
    
    /**
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
    
    /**
     * Resizing continues.
     */
    @FXML
    private void resizeContinue(MouseEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        double newW = initW + event.getSceneX() - initX;
        double newH = event.getSceneY() + initH - initY;
        if(newW < 800) {
            newW = 800;
        }
        if(newH < 600) {
            newH = 600;
        }
        stage.setWidth(newW);
        stage.setHeight(newH);
        pane.setCursor(Cursor.SE_RESIZE);
    }
    
    /**
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
    
    /**
     * Load root directory.
     */
    @FXML
    private void loadRootDirectory(ActionEvent event) {
        Stage stage = (Stage) load.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        if (file != null && file.isDirectory()) {
            tree.setRoot(new TreeItem(file.getName()));
            tree.getRoot().setExpanded(true);
            expand(tree.getRoot(), file.listFiles());
        }
    }
    
    /**
     * Enter resize zone (cursor effects).
     */
    @FXML
    private void hover(MouseEvent event) {
        pane.setCursor(Cursor.SE_RESIZE);
    }
    
    /**
     * Leave resize zone.
     */
    @FXML
    private void leave(MouseEvent event) {
        pane.setCursor(Cursor.DEFAULT);
    }
    
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
    
    /**
     * Show help.
     */
    @FXML
    private void showHelp(ActionEvent event) {
        System.out.println("Help information.");
    }
    
    /**
     * Close application.
     */
    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
