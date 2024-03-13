package cz.cvut.fel.pjv.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * enum for fxmlPages
 * 
 */
public enum ParentEnum {

    /**
     *
     */
    MenuPage ("/fxml/Menu.fxml"),

    /**
     *
     */
    EndGame("/fxml/EndGame.fxml"),

    /**
     *
     */
    WinGame("/fxml/WinGame.fxml"),

    /**
     *
     */
    PauseGame("/fxml/PauseGame.fxml"),

    /**
     *
     */
    Server("/fxml/Server.fxml"),

    /**
     *
     */
    SingleGame("/fxml/SingleGame.fxml"),

    /**
     *
     */
    OptionPage("/fxml/Option.fxml");
    private String parentName;
    
    private ParentEnum(String parentName) {
        this.parentName = parentName;
    }

    /**
     *
     * @return Parent with page
     * @throws IOException
     */
    public Parent getPage() throws IOException {
        Parent parent = loadParent(parentName);
        return parent;
    }

    /**
     *
     * @return Parent load parrent from resource
     * @throws IOException
     */
    private Parent loadParent(String parent) throws IOException {
        return FXMLLoader.load(getClass().getResource(parent));
    }
    
}
