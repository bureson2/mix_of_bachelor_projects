package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.view.ParentEnum;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * Endgame screen after win game
 */
public class WinGameFXMLController implements Initializable{
    @FXML
    private Button continueButton;  
    @FXML
    private Rectangle rectangle;
    
    /**
     *
     * @param event click end and exit game
     */
    @FXML
    public void endClicked(ActionEvent event){
        Platform.exit();
        System.exit(0);
    }
    
    /**
     *
     * @param event click continue to menu
     * @throws IOException
     */
    @FXML
    public void continueClicked(ActionEvent event) throws IOException{
        Stage stage = (Stage) continueButton.getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(ParentEnum.MenuPage.getPage());
    }

    /**
     * Method that initialize screen
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rectangle.setArcWidth(30.0); 
        rectangle.setArcHeight(20.0); 
    }
}
