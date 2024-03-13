package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.view.ParentEnum;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * Multiplayer menu screen
 */
public class ServerFXMLController {
    private Server server;
    private Client client;
    private MultiplayerGameServer multiplayerGameServer;
    private boolean clientOnly;

    @FXML
    private Button backButton;
    @FXML
    private Button serverButton;

    /**
     * return to Menu page
     * @throws IOException
     */
    @FXML
    public void backButtonClicked() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(ParentEnum.MenuPage.getPage());
    }

    /**
     * start server and multiplayer game
     * @throws IOException
     */
    @FXML
    public void createServerClicked() throws IOException {
        server = new Server(this, 4444);
        startServerThenClient();
    }

    /**
     * start connection option for client
     * @throws IOException
     */
    @FXML
    public void joinServerClicked() throws IOException {
        client = new Client("localhost", 4444);
        startClient();
    }

    /**
     *
     * @throws IOException
     */
    private void startServerThenClient() throws IOException {
        server.run();
    }

    /**
     *
     * @throws IOException
     */
    private void startClient() throws IOException {
        client.run();
    }

    /**
     * start Multiplayer game
     * @throws IOException
     */
    public void runGame() throws IOException {
        MultiplayerGameServer multiplayerGameServer = new MultiplayerGameServer(server);
        multiplayerGameServer.startGame();
    }
}
