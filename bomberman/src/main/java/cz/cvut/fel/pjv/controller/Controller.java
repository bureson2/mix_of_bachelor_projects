package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.view.ParentEnum;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * Class for starting the game and controll menu screen
 */
public class Controller extends Application{

    private Scene scene;
    @FXML
    private Button optionButton;
    @FXML
    private Button playButton;

    /**
     * method that exit the game
     * @param event click on exitButton
     */
    @FXML
    public void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    /**
     * method that direct player to options menu
     * @param event click on option button
     * @throws IOException
     */
    @FXML
    public void optionClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) optionButton.getScene().getWindow();
        scene = stage.getScene();
        scene.setRoot(ParentEnum.OptionPage.getPage());
    }

    /**
     * method that start game
     * it direct player by settings from options
     * @param actionEvent
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    @FXML
    public void startGame(ActionEvent actionEvent) throws IOException, InterruptedException {
        Stage stage = (Stage) playButton.getScene().getWindow();
        scene = stage.getScene();
        BufferedReader br = new BufferedReader(new FileReader(new File("serverConfig.txt")));
        String data = br.readLine();
        if (data.equals("S")) {
            scene.setRoot(ParentEnum.SingleGame.getPage());
        } else {
            scene.setRoot(ParentEnum.Server.getPage());
        }
    }

    /**
     * main method
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    /**
     * Method that set Stage and show game
     * override from Application
     * @param stage main game stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent mainPage = ParentEnum.MenuPage.getPage();
        scene = new Scene(mainPage, 800, 600);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        stage.setResizable(false);
    }

    /**
     *  Method that set parameters of end screen
     * @throws IOException
     */
    public void setEndScreen() throws IOException {
        Stage stage = new Stage();
        Parent mainPage = ParentEnum.EndGame.getPage();
        scene = new Scene(mainPage, 800, 600);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        stage.setResizable(false);
    }

    /**
     * Method that set parameters of win screen
     * @throws IOException
     */
    public void setWinScreen() throws IOException {
        Stage stage = new Stage();
        Parent mainPage = ParentEnum.WinGame.getPage();
        scene = new Scene(mainPage, 800, 600);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        stage.setResizable(false);
    }
}
