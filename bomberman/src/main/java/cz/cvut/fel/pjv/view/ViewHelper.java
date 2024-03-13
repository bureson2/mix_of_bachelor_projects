package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.model.Block.Block;
import cz.cvut.fel.pjv.model.Block.DestructibleBlock;
import cz.cvut.fel.pjv.model.Bonus.Bonus;
import cz.cvut.fel.pjv.model.Enemy.Dummy;
import cz.cvut.fel.pjv.model.Enemy.Enemy;
import cz.cvut.fel.pjv.model.Enemy.Speedy;
import cz.cvut.fel.pjv.model.Origin.Gate;
import cz.cvut.fel.pjv.model.Origin.Key;
import cz.cvut.fel.pjv.model.Origin.Player;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * View helper for multiplayer game
 * based on general view
 * prepared for plannin network game
 * contains all veiw methods
 * generate map by configurated parameters
 * redraw by everz frame by game Coordinates
 * 
 */
public class ViewHelper implements Runnable {
    private Pane pane;
    private Scene scene;
    private BorderPane root = new BorderPane();

    private HashMap<? super Block, ImageView> blocksHashMap = new HashMap<>();
    private HashMap<? super Enemy, ImageView> enemiesHashMap = new HashMap<>();
    private HashMap<Player, ImageView> playersHashMap = new HashMap<>();
    private HashMap<Bonus, ImageView> bonusHashMap = new HashMap<>();
    private ImageView keyView;
    private ImageView gateView;
    private Image deathImage;

    final private int BLOCK_SIZE = 45;
    final private int PLAYER_SIZE = 35;

    /**
     * 
     * @param pane
     */
    public ViewHelper(Pane pane) {
        this.pane = pane;
    }

    /**
     * generate background map
     * @param sizeX
     * @param sizeY
     * @return
     */
    public static Canvas mapGenerate(int sizeX, int sizeY) {
        Canvas canvas = new Canvas(sizeX, sizeY);
        GraphicsContext map = canvas.getGraphicsContext2D();
        map.setFill(Color.BLACK);
        map.fillRect(0, 0, sizeX, sizeY);

        return canvas;
    }

    /**
     * set basic parameters of root
     * used in Client
     * @throws IOException
     */
    public void setWindow() throws IOException {
        root.setCenter(pane);
    }

    /**
     * generate object to our pane
     * it thorugh elemnt by element and draw them
     * @param objectsPositions
     */
    public void objectGenerate(HashMap objectsPositions) {
        ArrayList<Gate> gateList = (ArrayList<Gate>) objectsPositions.get("Gate");
        Gate exitGate = gateList.get(0);
        gateView = new ImageView(exitGate.getGateImage());
        gateView.setFitHeight(BLOCK_SIZE);
        gateView.setFitWidth(BLOCK_SIZE);
        gateView.setLayoutX(exitGate.getPositionX());
        gateView.setLayoutY(exitGate.getPositionY());
        pane.getChildren().add(gateView);

        ArrayList<Key> keyList = (ArrayList<Key>) objectsPositions.get("Key");
        Key key = keyList.get(0);
        keyView = new ImageView(key.getKeyImage());
        keyView.setFitHeight(BLOCK_SIZE);
        keyView.setFitWidth(BLOCK_SIZE);
        keyView.setLayoutX(key.getPositionX());
        keyView.setLayoutY(key.getPositionY());
        pane.getChildren().add(keyView);

        ArrayList<Bonus> bonuses = (ArrayList<Bonus>) objectsPositions.get("Bonus");
        for (Bonus bonus : bonuses) {
            ImageView bonusView = new ImageView(bonus.getImage());
            bonusView.setFitHeight(BLOCK_SIZE);
            bonusView.setFitWidth(BLOCK_SIZE);
            bonusView.setLayoutX(bonus.getPositionX());
            bonusView.setLayoutY(bonus.getPositionY());
            pane.getChildren().add(bonusView);
            bonusHashMap.put(bonus, bonusView);
        }

        ArrayList<Block> undestructibleBlocks = (ArrayList<Block>) objectsPositions.get("Undestructible");
        for (Block block : undestructibleBlocks) {
            ImageView blockView = new ImageView(Gfx.UndestructibleBlock.getImage());
            blockView.setFitHeight(BLOCK_SIZE);
            blockView.setFitWidth(BLOCK_SIZE);
            blockView.setLayoutX(block.getPositionX());
            blockView.setLayoutY(block.getPositionY());
            pane.getChildren().add(blockView);
        }

        ArrayList<DestructibleBlock> destructibleBlocks = (ArrayList<DestructibleBlock>) objectsPositions.get("Destructible");
        for (Block block : destructibleBlocks) {
            ImageView blockView = new ImageView(Gfx.DestructibleBlock.getImage());
            blockView.setFitHeight(BLOCK_SIZE);
            blockView.setFitWidth(BLOCK_SIZE);
            blockView.setLayoutX(block.getPositionX());
            blockView.setLayoutY(block.getPositionY());
            pane.getChildren().add(blockView);
            blocksHashMap.put(block, blockView);
        }

        ArrayList<Player> players = (ArrayList<Player>) objectsPositions.get("Player");
        for (Player player : players) {
            ImageView playerView = new ImageView(Gfx.Player.getImage());
            playerView.setFitHeight(PLAYER_SIZE);
            playerView.setFitWidth(PLAYER_SIZE);
            playerView.setLayoutX(player.getPositionX());
            playerView.setLayoutY(player.getPositionY());
            pane.getChildren().add(playerView);
            playersHashMap.put(player, playerView);
        }

        ArrayList<Enemy> enemies = (ArrayList<Enemy>) objectsPositions.get("Basic Enemy");
        for (Enemy enemy : enemies) {
            ImageView enemyView = new ImageView(enemy.getImage());
            enemyView.setFitHeight(BLOCK_SIZE);
            enemyView.setFitWidth(BLOCK_SIZE);
            enemyView.setLayoutX(enemy.getPositionX());
            enemyView.setLayoutY(enemy.getPositionY());
            pane.getChildren().add(enemyView);
            enemiesHashMap.put(enemy, enemyView);
        }

        ArrayList<Dummy> dummyEnemies = (ArrayList<Dummy>) objectsPositions.get("Dummy Enemy");
        for (Dummy enemy : dummyEnemies) {
            ImageView enemyView = new ImageView(enemy.getImage());
            enemyView.setFitHeight(BLOCK_SIZE);
            enemyView.setFitWidth(BLOCK_SIZE);
            enemyView.setLayoutX(enemy.getPositionX());
            enemyView.setLayoutY(enemy.getPositionY());
            pane.getChildren().add(enemyView);
            enemiesHashMap.put(enemy, enemyView);
        }

        ArrayList<Speedy> speedyEnemies = (ArrayList<Speedy>) objectsPositions.get("Speedy Enemy");
        for (Speedy enemy : speedyEnemies) {
            ImageView enemyView = new ImageView(enemy.getImage());
            enemyView.setFitHeight(BLOCK_SIZE);
            enemyView.setFitWidth(BLOCK_SIZE);
            enemyView.setLayoutX(enemy.getPositionX());
            enemyView.setLayoutY(enemy.getPositionY());
            pane.getChildren().add(enemyView);
            enemiesHashMap.put(enemy, enemyView);
        }
    }

    /**
     * method for redrawing map after receive game coordinates
     * method needs hashMap generated by deserialization
     * @param objectsPositions
     * @throws IOException
     */
    public void redrawMap(HashMap objectsPositions) throws IOException {
        ImageView playerView = new ImageView(Gfx.Player.getImage());
        int x = ((ArrayList<Player>)objectsPositions.get("Player")).get(0).getPositionX();
        int y = ((ArrayList<Player>)objectsPositions.get("Player")).get(0).getPositionY();
        playerView.relocate(x, y);
        ArrayList<Key> keyList = (ArrayList<Key>) objectsPositions.get("Key");
        Key key = keyList.get(0);
        keyView = new ImageView(key.getKeyImage());
        keyView.setFitHeight(BLOCK_SIZE);
        keyView.setFitWidth(BLOCK_SIZE);
        keyView.setLayoutX(key.getPositionX());
        keyView.setLayoutY(key.getPositionY());
        pane.getChildren().add(keyView);

        ArrayList<Bonus> bonuses = (ArrayList<Bonus>) objectsPositions.get("Bonus");
        for (Bonus bonus : bonuses) {
            ImageView bonusView = new ImageView(bonus.getImage());
            bonusView.setFitHeight(BLOCK_SIZE);
            bonusView.setFitWidth(BLOCK_SIZE);
            bonusView.setLayoutX(bonus.getPositionX());
            bonusView.setLayoutY(bonus.getPositionY());
            pane.getChildren().add(bonusView);
            bonusHashMap.put(bonus, bonusView);
        }

        ArrayList<DestructibleBlock> destructibleBlocks = (ArrayList<DestructibleBlock>) objectsPositions.get("Destructible");
        for (Block block : destructibleBlocks) {
            ImageView blockView = new ImageView(Gfx.DestructibleBlock.getImage());
            blockView.setFitHeight(BLOCK_SIZE);
            blockView.setFitWidth(BLOCK_SIZE);
            blockView.setLayoutX(block.getPositionX());
            blockView.setLayoutY(block.getPositionY());
            pane.getChildren().add(blockView);
            blocksHashMap.put(block, blockView);
        }

        ArrayList<Player> players = (ArrayList<Player>) objectsPositions.get("Player");
        for (Player player : players) {
            if(player.getPlayerID() == 1){
                playerView = new ImageView(Gfx.Player.getImage());
            } else {
                playerView = new ImageView(Gfx.Player2.getImage());
            }
            playerView.setFitHeight(PLAYER_SIZE);
            playerView.setFitWidth(PLAYER_SIZE);
            playerView.setLayoutX(player.getPositionX());
            playerView.setLayoutY(player.getPositionY());
            pane.getChildren().add(playerView);
            playersHashMap.put(player, playerView);
        }

        ArrayList<Enemy> enemies = (ArrayList<Enemy>) objectsPositions.get("Basic Enemy");
        for (Enemy enemy : enemies) {
            ImageView enemyView = new ImageView(enemy.getImage());
            enemyView.setFitHeight(BLOCK_SIZE);
            enemyView.setFitWidth(BLOCK_SIZE);
            enemyView.setLayoutX(enemy.getPositionX());
            enemyView.setLayoutY(enemy.getPositionY());
            pane.getChildren().add(enemyView);
            enemiesHashMap.put(enemy, enemyView);
        }

        ArrayList<Dummy> dummyEnemies = (ArrayList<Dummy>) objectsPositions.get("Dummy Enemy");
        for (Dummy enemy : dummyEnemies) {
            ImageView enemyView = new ImageView(enemy.getImage());
            enemyView.setFitHeight(BLOCK_SIZE);
            enemyView.setFitWidth(BLOCK_SIZE);
            enemyView.setLayoutX(enemy.getPositionX());
            enemyView.setLayoutY(enemy.getPositionY());
            pane.getChildren().add(enemyView);
            enemiesHashMap.put(enemy, enemyView);
        }

        ArrayList<Speedy> speedyEnemies = (ArrayList<Speedy>) objectsPositions.get("Speedy Enemy");
        for (Speedy enemy : speedyEnemies) {
            ImageView enemyView = new ImageView(enemy.getImage());
            enemyView.setFitHeight(BLOCK_SIZE);
            enemyView.setFitWidth(BLOCK_SIZE);
            enemyView.setLayoutX(enemy.getPositionX());
            enemyView.setLayoutY(enemy.getPositionY());
            pane.getChildren().add(enemyView);
            enemiesHashMap.put(enemy, enemyView);
        }
    }

    /**
     * Override from Runnable
     * method that set parameters of stage
     * show screen
     */
    @Override
    public void run() {
        scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
