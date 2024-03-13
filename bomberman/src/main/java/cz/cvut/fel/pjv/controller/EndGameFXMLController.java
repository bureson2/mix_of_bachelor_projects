
package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.view.ParentEnum;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * Endgame screen after player death
 */
public class EndGameFXMLController {
    @FXML
    private Button continueButton;
    
    /**
     *  
     * @param event event click on button
     */
    @FXML
    public void endClicked(ActionEvent event){
        Platform.exit();
        System.exit(0);
    }
    
    /**
     *
     * @param event event click on button
     * @throws IOException
     */
    @FXML
    public void continueClicked(ActionEvent event) throws IOException{
        Stage stage = (Stage) continueButton.getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(ParentEnum.MenuPage.getPage());
    }
}
