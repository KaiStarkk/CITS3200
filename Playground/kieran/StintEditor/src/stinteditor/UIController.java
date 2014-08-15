/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stinteditor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    private Button loadBtn;
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
    @FXML
    DirectoryChooser directoryChooser = new DirectoryChooser();
    
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
        if (initY < 90 && initX < initW - 85 || initY < 90 && initY > 40 ) {
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
        if(newH < 450) {
            newH = 450;
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
    
    /**
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
    
    /**
     * Load root directory.
     */
    @FXML
    private void loadRootDirectory(ActionEvent event) {
        Stage stage = (Stage) loadBtn.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        if (file != null && file.isDirectory()) {
            tree.setRoot(new TreeItem(file.getName()));
            tree.getRoot().setExpanded(true);
            expand(tree.getRoot(), file.listFiles());
        }
    }
    
    /**
     * Show the grounds UI.
     */
    @FXML
    private void showGroundsUI(ActionEvent event) throws Exception {
        final Stage groundsStage = new Stage(StageStyle.UNDECORATED);
        
        groundsStage.initModality(Modality.WINDOW_MODAL);
        groundsStage.initOwner(pane.getScene().getWindow());
        Parent root = FXMLLoader.load(getClass().getResource("groundsUI.fxml"));
        
        Scene scene = new Scene(root);
        
        groundsStage.setScene(scene);
        groundsStage.show();
    }
    
    /**
     * Show help.
     */
    @FXML
    private void minimise(ActionEvent event) {
        Stage stage = (Stage) minBtn.getScene().getWindow();
        stage.setIconified(true);
    }
    
    /**
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
    
    /**
     * Close application.
     */
    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
