package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.view.ParentEnum;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * Options screen after press ESCAPE
 */
public class OptionFXMLController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private RadioButton sp;
    @FXML
    private RadioButton mp;
    @FXML
    private RadioButton sound;
    @FXML
    private Rectangle rectangle;
    @FXML
    private ComboBox<String> dropList;
    private ObservableList<String> list = FXCollections.observableArrayList("Map 1", "Map 2", "Map 3", "Map 4", "Map 5");
    String music = new File("src/main/resources/sound/sound.mp3").toURI().toString();
    MediaPlayer player = new MediaPlayer(new Media(music));

    /**
     *
     * @param actionEvent click back button
     * @throws IOException
     */
    @FXML
    public void backClick(ActionEvent actionEvent) throws IOException {
        FileWriter serverConfig = new FileWriter("serverConfig.txt");
        if(sp.isSelected()){
            serverConfig.write("S");
        } else {
            serverConfig.write("M");
        }
        serverConfig.close();
        
        FileWriter mapConfig = new FileWriter("mapInfo.txt");
        String chosenMap = (String) dropList.getValue();
        System.out.println(chosenMap);
        if(chosenMap.equals("Map 1")){
            mapConfig.write("1");
        } else if(chosenMap.equals("Map 2")){
            mapConfig.write("2");
        } else if(chosenMap.equals("Map 3")){
            mapConfig.write("3");
        } else if(chosenMap.equals("Map 4")){
            mapConfig.write("4");
        } else {
            mapConfig.write("5");
        }
        mapConfig.close();
        
        if(sound.isSelected()){
            player.play();
        }
           
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(ParentEnum.MenuPage.getPage());
    }

    /**
     *
     * @param actionEvent
     */
    @FXML
    public void selectSPControl(ActionEvent actionEvent) {
        if (sp.isSelected()) {
            mp.setSelected(false);
        }
    }

    /**
     *
     * @param actionEvent click multi player radio button
     */
    @FXML
    public void selectMPControl(ActionEvent actionEvent) {
        if (mp.isSelected()) {
            sp.setSelected(false);
        }
    }

    /**
     * Method that initialize items on Option screen
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File("serverConfig.txt")));
            String data = br.readLine();
            if (data.equals("S")) {
                sp.setSelected(true);
            } else {
                mp.setSelected(true);
            }   dropList.setItems(list);
            dropList.setValue("Map 1");
            rectangle.setArcWidth(30.0);
            rectangle.setArcHeight(20.0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OptionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OptionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(OptionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
