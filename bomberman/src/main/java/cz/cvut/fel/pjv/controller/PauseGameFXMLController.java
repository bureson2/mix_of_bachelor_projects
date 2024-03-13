package cz.cvut.fel.pjv.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * Pause screen after press ESCAPE
 */
public class PauseGameFXMLController {

    @FXML
    private Button saveButton;
    @FXML
    private Button continueButton;
    
    /**
     *
     * @param actionEvent click save game button
     */
    @FXML
    private void saveButtonClicked(ActionEvent event) {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();      
    }

    /**
     *
     * @param actionEvent click continue game button
     */
    @FXML
    private void continueButtonClicked(ActionEvent event) {
        Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();
    
    }

    /**
     *
     * @param actionEvent click exit button
     */
    @FXML
    private void exitButtionClicked(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}
