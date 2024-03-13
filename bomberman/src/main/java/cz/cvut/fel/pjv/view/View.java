package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.controller.Controller;
import cz.cvut.fel.pjv.model.Block.*;
import cz.cvut.fel.pjv.model.Bonus.Bonus;
import cz.cvut.fel.pjv.model.Enemy.*;
import cz.cvut.fel.pjv.model.Origin.*;
import java.io.IOException;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.HashMap;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * General view contains all veiw methods generate map by configurated
 * parameters redraw by everz frame by game Coordinates
 *
 */
public class View {

    private Map mapOrigin;
    private HashMap objectsPositions;
    private Scene scene;
    private Stage stage;

    private HashMap<? super Block, ImageView> blocksHashMap = new HashMap<>();
    private HashMap<? super Enemy, ImageView> enemiesHashMap = new HashMap<>();
    private HashMap<Player, ImageView> playersHashMap = new HashMap<>();
    private HashMap<Bonus, ImageView> bonusHashMap = new HashMap<>();
    private ImageView keyView;
    private ImageView gateView;
    private Image deathImage;

    private Label lifes;
    private Label bombCount;
    private Label explosionSize;
    private Label speed;
    private Label lifes2;
    private Label bombCount2;
    private Label explosionSize2;
    private Label speed2;

    private BorderPane root = new BorderPane();
    private Pane pane = new Pane();
    private Timeline timeline = new Timeline();

    final private int BLOCK_SIZE = 45;
    final private int PLAYER_SIZE = 35;

    /**
     *
     * @param mapOrigin map object with size params
     * @param objectPositions hashMap with game coordinates data
     */
    public View(Map mapOrigin, HashMap objectPositions) {
        this.mapOrigin = mapOrigin;
        this.objectsPositions = objectPositions;
    }

    /**
     * generate base background of map
     *
     * @param sizeX
     * @param sizeY
     * @return canvas for drwaing other elements
     */
    public Canvas mapGenerate(int sizeX, int sizeY) {
        Canvas canvas = new Canvas(sizeX, sizeY);
        GraphicsContext map = canvas.getGraphicsContext2D();
        map.setFill(Color.BLACK);
        map.fillRect(0, 0, sizeX, sizeY);

        return canvas;
    }

    /**
     * generate object to our pane it thorugh elemnt by element and draw them
     *
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
            ImageView playerView;
            if (player.getPlayerID() == 1) {
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
     * set image of gate when it is opened
     *
     * @param gate
     */
    public void openGate(Gate gate) {
        gateView.setImage(gate.getOpenGateImage());
    }

    /**
     * method that set header with actual atributes of player
     *
     * @return pane with set labels
     */
    public Pane setHeader() {
        Pane header = new Pane();

        Label playerLabel = new Label("Player 1:");
        playerLabel.setFont(new Font("Arial", 30));

        lifes = new Label("0");
        lifes.setFont(new Font("Arial", 30));
        Label lifesLabel = new Label("Lifes:");
        lifesLabel.setFont(new Font("Arial", 30));

        bombCount = new Label("0");
        bombCount.setFont(new Font("Arial", 30));
        Label bombCountLabel = new Label("Number of bombs:");
        bombCountLabel.setFont(new Font("Arial", 30));

        explosionSize = new Label("0");
        explosionSize.setFont(new Font("Arial", 30));
        Label explosionSizeLabel = new Label("Explostion size:");
        explosionSizeLabel.setFont(new Font("Arial", 30));

        speed = new Label("0");
        speed.setFont(new Font("Arial", 30));
        Label speedLabel = new Label("Speed:");
        speedLabel.setFont(new Font("Arial", 30));

        Canvas background = new Canvas(mapOrigin.getSizeX(), 100);
        GraphicsContext context = background.getGraphicsContext2D();
        context.setFill(Color.GRAY);
        context.fillRect(0, 0, mapOrigin.getSizeX(), 100);
        header.getChildren().add(background);

        VBox vBoxLifes = new VBox();
        vBoxLifes.getChildren().add(lifesLabel);
        vBoxLifes.getChildren().add(lifes);
        vBoxLifes.setAlignment(Pos.CENTER);

        VBox vBoxbombCount = new VBox();
        vBoxbombCount.getChildren().add(bombCountLabel);
        vBoxbombCount.getChildren().add(bombCount);
        vBoxbombCount.setAlignment(Pos.CENTER);

        VBox vBoxexplosionsize = new VBox();
        vBoxexplosionsize.getChildren().add(explosionSizeLabel);
        vBoxexplosionsize.getChildren().add(explosionSize);
        vBoxexplosionsize.setAlignment(Pos.CENTER);

        VBox vBoxSpeed = new VBox();
        vBoxSpeed.getChildren().add(speedLabel);
        vBoxSpeed.getChildren().add(speed);
        vBoxSpeed.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(20, playerLabel, vBoxLifes, vBoxbombCount, vBoxexplosionsize, vBoxSpeed);
        header.getChildren().add(hBox);
        return header;
    }

    /**
     * method that set header with actual atributes of player
     *
     * @return pane with set labels
     */
    public Pane setHeader2() {
        Pane header = new Pane();

        Label playerLabel = new Label("Player 2:");
        playerLabel.setFont(new Font("Arial", 30));

        lifes2 = new Label("0");
        lifes2.setFont(new Font("Arial", 30));
        Label lifesLabel = new Label("Lifes:");
        lifesLabel.setFont(new Font("Arial", 30));

        bombCount2 = new Label("0");
        bombCount2.setFont(new Font("Arial", 30));
        Label bombCountLabel = new Label("Number of bombs:");
        bombCountLabel.setFont(new Font("Arial", 30));

        explosionSize2 = new Label("0");
        explosionSize2.setFont(new Font("Arial", 30));
        Label explosionSizeLabel = new Label("Explostion size:");
        explosionSizeLabel.setFont(new Font("Arial", 30));

        speed2 = new Label("0");
        speed2.setFont(new Font("Arial", 30));
        Label speedLabel = new Label("Speed:");
        speedLabel.setFont(new Font("Arial", 30));

        Canvas background = new Canvas(mapOrigin.getSizeX(), 100);
        GraphicsContext context = background.getGraphicsContext2D();
        context.setFill(Color.GRAY);
        context.fillRect(0, 0, mapOrigin.getSizeX(), 100);
        header.getChildren().add(background);

        VBox vBoxLifes = new VBox();
        vBoxLifes.getChildren().add(lifesLabel);
        vBoxLifes.getChildren().add(lifes2);
        vBoxLifes.setAlignment(Pos.CENTER);

        VBox vBoxbombCount = new VBox();
        vBoxbombCount.getChildren().add(bombCountLabel);
        vBoxbombCount.getChildren().add(bombCount2);
        vBoxbombCount.setAlignment(Pos.CENTER);

        VBox vBoxexplosionsize = new VBox();
        vBoxexplosionsize.getChildren().add(explosionSizeLabel);
        vBoxexplosionsize.getChildren().add(explosionSize2);
        vBoxexplosionsize.setAlignment(Pos.CENTER);

        VBox vBoxSpeed = new VBox();
        vBoxSpeed.getChildren().add(speedLabel);
        vBoxSpeed.getChildren().add(speed2);
        vBoxSpeed.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(20, playerLabel, vBoxLifes, vBoxbombCount, vBoxexplosionsize, vBoxSpeed);
        header.getChildren().add(hBox);
        return header;
    }

    /**
     * method that refresh header
     *
     * @param player
     */
    public void redrawHeader(Player player) {
        lifes.setText(String.valueOf(player.getLifes()));
        bombCount.setText(String.valueOf(player.getNumberOfBombs()));
        explosionSize.setText(String.valueOf(player.getExplosionSize()));
        speed.setText(String.valueOf(player.getSpeed()));
    }

    /**
     * method that refresh header
     *
     * @param player
     */
    public void redrawHeader2(Player player) {
        lifes2.setText(String.valueOf(player.getLifes()));
        bombCount2.setText(String.valueOf(player.getNumberOfBombs()));
        explosionSize2.setText(String.valueOf(player.getExplosionSize()));
        speed2.setText(String.valueOf(player.getSpeed()));
    }

    /**
     * set map size parameters and properties of stage call objectGenerate and
     * mapGenerate
     *
     * @throws IOException
     */
    public void setWindow() throws IOException {
        VBox box = new VBox();
        stage = new Stage();
        Canvas map = mapGenerate(mapOrigin.getSizeX(), mapOrigin.getSizeY());
        pane.setMaxSize(mapOrigin.getSizeX(), mapOrigin.getSizeY());
        pane.getChildren().add(map);
        root.setCenter(pane);
        if (((ArrayList<Player>) objectsPositions.get("Player")).size() <= 1) {
            root.setTop(setHeader());
            scene = new Scene(root, mapOrigin.getSizeX(), mapOrigin.getSizeY() + 100);
        } else {
            box.getChildren().addAll(setHeader(), setHeader2());
            root.setTop(box);
            scene = new Scene(root, mapOrigin.getSizeX(), mapOrigin.getSizeY() + 200);
        }
        objectGenerate(objectsPositions);
        stage.setScene(scene);
        stage.setTitle("Bomberman");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * method that end game
     */
    public void closeStage() {
        stage.close();
    }

    /**
     * method that set game over screen
     *
     * @throws IOException
     */
    public void endGame() throws IOException {
        Controller controller = new Controller();
        controller.setEndScreen();
    }

    /**
     * method that set game won screen
     *
     * @throws IOException
     */
    public void winGame() throws IOException {
        Controller controller = new Controller();
        controller.setWinScreen();
    }

    /**
     * set bomb to view
     *
     * @param positionX
     * @param positionY
     */
    public void setBombToView(int positionX, int positionY) {
        ImageView bombView = new ImageView(Gfx.Bomb.getImage());
        bombView.setFitHeight(BLOCK_SIZE);
        bombView.setFitWidth(BLOCK_SIZE);
        bombView.setLayoutX(positionX);
        bombView.setLayoutY(positionY);

        pane.getChildren().add(bombView);
    }

    /**
     * draw an explosion to view
     *
     * @param explosionToDraw explosion set send from collision controller
     */
    public void explodeBomb(List explosionToDraw) {
        Timeline explodeTimeline = new Timeline();
        Group explosion = new Group();
        for (int[] coordinate : (List<int[]>) explosionToDraw) {
            ImageView explosionView = new ImageView(Gfx.Explosion.getImage());
            explosionView.setFitHeight(BLOCK_SIZE);
            explosionView.setFitWidth(BLOCK_SIZE);
            explosionView.setLayoutX(coordinate[0]);
            explosionView.setLayoutY(coordinate[1]);
            explosion.getChildren().add(explosionView);
        }

        explodeTimeline.setCycleCount(1);
        pane.getChildren().add(explosion);
        explodeTimeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(500),
                (ActionEvent event)
                -> pane.getChildren().remove(explosion)
        ));
        explodeTimeline.play();
    }

    /**
     * redraw player after death get player who died player will blick for
     * indicate new position
     *
     * @param player
     */
    public void playerRespanAnimation(Player player) {
        ImageView playerToAnimate = playersHashMap.get(player);
        Timeline respanAnimation = new Timeline();
        respanAnimation.setCycleCount(1);
        respanAnimation.getKeyFrames().add(new KeyFrame(
                Duration.millis(100),
                (ActionEvent event) -> playerToAnimate.setOpacity(80)
        ));
        respanAnimation.getKeyFrames().add(new KeyFrame(
                Duration.millis(200),
                (ActionEvent event) -> playerToAnimate.setOpacity(0)
        ));
        respanAnimation.getKeyFrames().add(new KeyFrame(
                Duration.millis(300),
                (ActionEvent event) -> playerToAnimate.setOpacity(50)
        ));
        respanAnimation.getKeyFrames().add(new KeyFrame(
                Duration.millis(400),
                (ActionEvent event) -> playerToAnimate.setOpacity(100)
        ));
        respanAnimation.play();
    }

    /**
     * remove player after death from view from his last position
     *
     * @param player
     */
    public void removePlayer(Player player) {
        ImageView playerToRemove = playersHashMap.get(player);
        pane.getChildren().remove(playerToRemove);
    }

    /**
     * relocate player on new actual position
     *
     * @param player
     */
    public void playerRelocate(Player player) {
        ImageView playerToRelocate = playersHashMap.get(player);
        playerToRelocate.relocate(player.getPositionX(), player.getPositionY());
    }

    /**
     * remove enemy after death from view from his last position change img to
     * death image enemy will blick for indicate new position
     *
     * @param enemyToRemove
     */
    public void removeEnemy(ArrayList<? extends Enemy> enemyToRemove) {
        for (int i = 0; i < enemyToRemove.size(); i++) {
            ImageView enemy = enemiesHashMap.get(enemyToRemove.get(i));
            Timeline animation = new Timeline();
            if (((enemyToRemove.get(i)).getNote()) == "Basic") {
                deathImage = enemyToRemove.get(i).getImageDeath();
            } else if ((enemyToRemove.get(i).getNote()) == "Dummy") {
                deathImage = enemyToRemove.get(i).getImageDeath();
            } else if ((enemyToRemove.get(i).getNote()) == "Speedy") {
                deathImage = enemyToRemove.get(i).getImageDeath();
            }

            animation.setCycleCount(1);
            animation.getKeyFrames().add(new KeyFrame(
                    Duration.millis(100),
                    (ActionEvent event) -> enemy.setImage(deathImage)
            ));
            animation.getKeyFrames().add(new KeyFrame(
                    Duration.millis(500),
                    (ActionEvent event) -> enemy.setOpacity(80)
            ));
            animation.getKeyFrames().add(new KeyFrame(
                    Duration.millis(600),
                    (ActionEvent event) -> enemy.setOpacity(0)
            ));
            animation.getKeyFrames().add(new KeyFrame(
                    Duration.millis(700),
                    (ActionEvent event) -> pane.getChildren().remove(enemy)
            ));
            animation.play();
        }
    }

    /**
     * relocate enemy on new actual position
     *
     * @param enemy
     */
    public void enemyRelocate(Enemy enemy) {
        ImageView enemyToRelocate = enemiesHashMap.get(enemy);
        enemyToRelocate.relocate(enemy.getPositionX(), enemy.getPositionY());
    }

    /**
     *
     * @param blocksToRemove
     */
    public void blockRemove(ArrayList<DestructibleBlock> blocksToRemove) {
        for (DestructibleBlock destructibleBlock : blocksToRemove) {
            ImageView block = blocksHashMap.get(destructibleBlock);
            pane.getChildren().remove(block);
            blocksHashMap.remove(destructibleBlock);
        }
    }

    /**
     * change block image after partial destruction
     *
     * @param blocksToChange
     */
    public void changeBlock(ArrayList<DestructibleBlock> blocksToChange) {
        for (DestructibleBlock destructibleBlock : blocksToChange) {
            ImageView block = blocksHashMap.get(destructibleBlock);
            block.setImage(Gfx.DestructibleBlock1.getImage());
        }
    }

    /**
     * method that remove key from general view
     */
    public void removeKeyfromMap() {
        pane.getChildren().remove(keyView);
    }

    /**
     * method that remove bonus from general view
     *
     * @param bonus
     */
    public void removeBonusfromMap(Bonus bonus) {
        pane.getChildren().remove(bonusHashMap.get(bonus));
    }

    /**
     *
     * @return
     */
    public Scene getScene() {
        return scene;
    }
}
