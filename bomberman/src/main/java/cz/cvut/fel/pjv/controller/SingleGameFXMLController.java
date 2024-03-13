package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.view.ParentEnum;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * Single player menu
 */
public class SingleGameFXMLController {
    
    @FXML
    private Button backButton; 
    @FXML
    private Button newGame;
    
    /**
     * return to main Menu
     * @throws IOException
     */
    @FXML
    public void backButtonClicked() throws IOException{
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(ParentEnum.MenuPage.getPage());
    }
    
    /**
     * load savedata file and start game from time, where last end
     * @throws IOException
     */
    @FXML void loadButtonClicked() throws IOException{
        Stage stage = (Stage) newGame.getScene().getWindow();
        stage.close();
        MapSetter mapSetter = new MapSetter();
        File lastGameData = new File("savedGameData.txt");
        String savedData = "";
        savedData = scanMap(lastGameData, savedData);
        HashMap<String, ArrayList> coordinates = mapSetter.deserialize(savedData);
        Game game = new Game(coordinates);
        game.start();
    }
 
    /**
     * start a new game
     * @throws IOException
     */
    @FXML void newGameClicked() throws IOException{
        Stage stage = (Stage) newGame.getScene().getWindow();
        stage.close();
        Game game = new Game();
        game.start();
    }
    
    /**
     * Method that load game data
     * @param file with data from last saved game
     * @param data serialized String, which will be deserialized
     * @return data from file
     */
    public String scanMap(File file, String data) {
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                data += scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }
    
}
