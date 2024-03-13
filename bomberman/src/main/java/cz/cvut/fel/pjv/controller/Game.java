package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.model.Block.Block;
import cz.cvut.fel.pjv.model.Block.DestructibleBlock;
import cz.cvut.fel.pjv.model.Bonus.Bonus;
import cz.cvut.fel.pjv.model.Enemy.*;
import cz.cvut.fel.pjv.model.Origin.*;
import cz.cvut.fel.pjv.view.ParentEnum;
import cz.cvut.fel.pjv.view.View;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Game class
 * Controller of game
 * Computed, stores and directs all game actions
 * 
 */
public class Game {

    private View view;
    private MapSetter mapSetter = new MapSetter();
    private CollisionController collisionController = new CollisionController();
    private AnimationTimer timer;
    private final static Logger LOGGER
            = Logger.getLogger(Game.class.getName());

    private HashMap<String, ArrayList> coordinates;
    private ArrayList<Enemy> enemyList;
    private ArrayList<Dummy> enemyDummyList;
    private ArrayList<Speedy> enemySpeedyList;
    private ArrayList<Player> playersList;
    private ArrayList<Bomb> bombList;

    private boolean goUp, goDown, goLeft, goRight;
    private Stage stage;

    final private int BLOCK_SIZE = 45;

    /**
     * constructor for new game
     */
    public Game() {
        coordinates = mapSetter.getObjectCoordinates();  
    }
    
    /**
     * constructor for load game 
     * @param hm
     */
    public Game(HashMap<String, ArrayList> coordinates) {
        this.coordinates = coordinates;
        
    }
    
    /**
     * Method that set map and enemies
     * start frame timer 
     * every frame controll collisions, movings, putting bombs and deaths
     * check if player pause game
     * @throws IOException
     */
    public void start() throws IOException {
        LOGGER.info("GAME START");       
        Map map = mapSetter.getMap();

        enemyList = coordinates.get("Basic Enemy");
        enemyDummyList = coordinates.get("Dummy Enemy");
        enemySpeedyList = coordinates.get("Speedy Enemy");
        playersList = coordinates.get("Player");
        bombList = coordinates.get("Bombs");

        view = new View(map, coordinates);
        view.setWindow();

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (playersList.size() > 0) {
                    keyMovementListener();
                    view.playerRelocate(playersList.get(0));
                    view.redrawHeader(playersList.get(0));
                    moveEnemy();
                    for (Player player : (ArrayList<Player>) coordinates.get("Player")) {
                        for (Bonus bonus : (ArrayList<Bonus>) coordinates.get("Bonus")) {
                            if (collisionController.collisionWithBonus(bonus, player)) {
                                view.removeBonusfromMap(bonus);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((ArrayList<Bonus>) coordinates.get("Bonus")).remove(bonus);
                                    }
                                });
                            }
                        }
                    }
                    collisionController.controllEnemyColision(playersList.get(0), coordinates, mapSetter.getStartPositions(), view);
                    if (coordinates.get("Key").size() > 0) {
                        if (collisionController.checkPlayerCollisionKey(coordinates.get("Player"), ((ArrayList<Key>) coordinates.get("Key")).get(0))) {
                            view.removeKeyfromMap();
                            view.openGate(((ArrayList<Gate>) coordinates.get("Gate")).get(0));
                            coordinates.get("Key").remove(0);
                            ((ArrayList<Gate>) coordinates.get("Gate")).get(0).setIsOpen(true);
                        }
                    }
                    if (((ArrayList<Gate>) coordinates.get("Gate")).get(0).isIsOpen()) {
                        if (collisionController.isPlayerWinner(coordinates.get("Player"), ((ArrayList<Gate>) coordinates.get("Gate")).get(0))) {
                            try {
                                stopGameWin();
                            } catch (IOException ex) {
                                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                    for (Bomb bomb : (ArrayList<Bomb>) coordinates.get("Bombs")) {
                        if (bomb.getTimeToExplosion() == 0) {
                            bomb.deactivateTimer();
                            view.explodeBomb(collisionController.listOfVectorForExplosion(
                                    bomb, coordinates, playersList.get(0), mapSetter.getStartPositions(), view));
                            view.blockRemove(collisionController.getBlocksToRemove());
                            view.changeBlock(collisionController.getBlocksToChange());
                            removeEnemyfromList((ArrayList<Enemy>) collisionController.getEnemyToRemove());
                            view.removeEnemy((ArrayList<Enemy>) collisionController.getEnemyToRemove());
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    coordinates.get("Bombs").remove(bomb);
                                }
                            });
                        }
                    }
                } else {
                    try {
                        stopGameForLose();
                    } catch (IOException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        timer.start();
    }

    /**
     * method that end game after loose
     * @throws IOException
     */
    public void stopGameForLose() throws IOException {
        LOGGER.info("Player loose the game");
        timer.stop();
        view.closeStage();
        view.endGame();
    }

    /**
     * method that end game for win
     * @throws IOException
     */
    public void stopGameWin() throws IOException {
        LOGGER.info("Player win the game");
        timer.stop();
        view.closeStage();
        view.winGame();
    }

    /**
     * method that remove death enemies
     * @param enemies list of all enemies
     * the type of enemy is knowned by his note
     */
    public void removeEnemyfromList(ArrayList<Enemy> enemies) {
        for (int i = 0; i < enemies.size(); i++) {
            switch (enemies.get(i).getNote()) {
                case "Dummy":
                    enemyDummyList.remove(enemies.get(i));
                    break;
                case "Speedy":
                    enemySpeedyList.remove(enemies.get(i));
                    break;
                case "Basic":
                    enemyList.remove(enemies.get(i));
                    break;
            }
        }
    }

    /**
     * method that passes through enemies and change their position by their move mehcanics
     * method controll free directions and choose free ways
     */
    public void moveEnemy() { 
        for (int i = 0; i < enemyList.size(); i++) {
            boolean right = collisionController.isRightWayEmptyForEnemy(enemyList.get(i), coordinates);
            boolean up = collisionController.isTopWayEmptyForEnemy(enemyList.get(i), coordinates);
            boolean left = collisionController.isLeftWayEmptyForEnemy(enemyList.get(i), coordinates);
            boolean down = collisionController.isBottomWayEmptyForEnemy(enemyList.get(i), coordinates);
            int direction = enemyList.get(i).getDirection();

            if (direction == 0 && right) {
                enemyList.get(i).changePositionX(direction);
                enemyList.get(i).changePositionY(direction);
                view.enemyRelocate(enemyList.get(i));
            } else if (direction == 1 && up) {
                enemyList.get(i).changePositionX(direction);
                enemyList.get(i).changePositionY(direction);
                view.enemyRelocate(enemyList.get(i));
            } else if (direction == 2 && left) {
                enemyList.get(i).changePositionX(direction);
                enemyList.get(i).changePositionY(direction);
                view.enemyRelocate(enemyList.get(i));
            } else if (direction == 3 && down) {
                enemyList.get(i).changePositionX(direction);
                enemyList.get(i).changePositionY(direction);
                view.enemyRelocate(enemyList.get(i));
            } else {
                enemyList.get(i).changeDirection();
            }
        }

        for (int i = 0; i < enemyDummyList.size(); i++) {
            boolean right = collisionController.isRightWayEmptyForEnemy(enemyDummyList.get(i), coordinates);
            boolean up = collisionController.isTopWayEmptyForEnemy(enemyDummyList.get(i), coordinates);
            boolean left = collisionController.isLeftWayEmptyForEnemy(enemyDummyList.get(i), coordinates);
            boolean down = collisionController.isBottomWayEmptyForEnemy(enemyDummyList.get(i), coordinates);
            int direction = enemyDummyList.get(i).getDirection();

            if (direction == 0 && right) {
                enemyDummyList.get(i).changePositionX(direction);
                enemyDummyList.get(i).changePositionY(direction);
                view.enemyRelocate(enemyDummyList.get(i));
            } else if (direction == 1 && up) {
                enemyDummyList.get(i).changePositionX(direction);
                enemyDummyList.get(i).changePositionY(direction);
                view.enemyRelocate(enemyDummyList.get(i));
            } else if (direction == 2 && left) {
                enemyDummyList.get(i).changePositionX(direction);
                enemyDummyList.get(i).changePositionY(direction);
                view.enemyRelocate(enemyDummyList.get(i));
            } else if (direction == 3 && down) {
                enemyDummyList.get(i).changePositionX(direction);
                enemyDummyList.get(i).changePositionY(direction);
                view.enemyRelocate(enemyDummyList.get(i));
            } else {
                enemyDummyList.get(i).changeDirection();
            }
        }

        for (int i = 0; i < enemySpeedyList.size(); i++) {
            boolean right = collisionController.isRightWayEmptyForEnemy(enemySpeedyList.get(i), coordinates);
            boolean up = collisionController.isTopWayEmptyForEnemy(enemySpeedyList.get(i), coordinates);
            boolean left = collisionController.isLeftWayEmptyForEnemy(enemySpeedyList.get(i), coordinates);
            boolean down = collisionController.isBottomWayEmptyForEnemy(enemySpeedyList.get(i), coordinates);
            int direction = enemySpeedyList.get(i).getDirection();

            if (direction == 0 && right) {
                enemySpeedyList.get(i).changePositionX(direction);
                enemySpeedyList.get(i).changePositionY(direction);
                view.enemyRelocate(enemySpeedyList.get(i));
            } else if (direction == 1 && up) {
                enemySpeedyList.get(i).changePositionX(direction);
                enemySpeedyList.get(i).changePositionY(direction);
                view.enemyRelocate(enemySpeedyList.get(i));
            } else if (direction == 2 && left) {
                enemySpeedyList.get(i).changePositionX(direction);
                enemySpeedyList.get(i).changePositionY(direction);
                view.enemyRelocate(enemySpeedyList.get(i));
            } else if (direction == 3 && down) {
                enemySpeedyList.get(i).changePositionX(direction);
                enemySpeedyList.get(i).changePositionY(direction);
                view.enemyRelocate(enemySpeedyList.get(i));
            } else {
                enemySpeedyList.get(i).changeDirection();
            }
        }

    }

    /**
     * Method that controls players movings
     * listeners for player action
     * player can move, put bombs and call pause menu
     * there is tested if player press or released a key
     */
    public void keyMovementListener() {
        Player playerToMove = playersList.size() != 0 ? playersList.get(0) : null;
        if (playerToMove != null) {
            view.getScene().setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
                @Override
                public void handle(javafx.scene.input.KeyEvent event) {
                    switch (event.getCode()) {
                        case UP:
                            goUp = true;
                            break;
                        case DOWN:
                            goDown = true;
                            break;
                        case LEFT:
                            goLeft = true;
                            break;
                        case RIGHT:
                            goRight = true;
                            break;
                        case SPACE:
                            if (collisionController.isPossibleToPutBomb(playerToMove, coordinates)) {
                                Bomb bomb = playerToMove.putBomb();
                                coordinates.get("Bombs").add(bomb);
                                bomb.activateTimer();
                                view.setBombToView(bomb.getPositionX(), bomb.getPositionY());
                            }
                            break;
                        case ESCAPE: {
                            try {
                                stage = setPauseMenu();
                            } catch (IOException ex) {
                                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            stage.show();
                        }
                        break;
                        case ENTER: {
                            if (!stage.isShowing()) {
                                timer.start();
                            }
                        }
                    }                
                }
            });

            view.getScene().setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
                @Override
                public void handle(javafx.scene.input.KeyEvent event) {
                    switch (event.getCode()) {
                        case UP:
                            goUp = false;
                            break;
                        case DOWN:
                            goDown = false;
                            break;
                        case LEFT:
                            goLeft = false;
                            break;
                        case RIGHT:
                            goRight = false;
                            break;
                    }
                }
            });

            int delta = playerToMove.getSpeed();
            int currX = playerToMove.getPositionX();
            int currY = playerToMove.getPositionY();

            if (goUp) {
                if (collisionController.isTopWayEmpty(coordinates, playerToMove)) {
                    currY -= delta;
                }
            }
            if (goDown) {
                if (collisionController.isBottomWayEmpty(coordinates, playerToMove)) {
                    currY += delta;
                }
            }
            if (goLeft) {
                if (collisionController.isLeftWayEmpty(coordinates, playerToMove)) {
                    currX -= delta;
                }
            }
            if (goRight) {
                if (collisionController.isRightWayEmpty(coordinates, playerToMove)) {
                    currX += delta;
                }
            }
            playerToMove.setPositionX(currX);
            playerToMove.setPositionY(currY);
        }
    }
    
    /**
     * method that call Pause menu
     * @return pauseMenu stage
     * @throws IOException
     */
    public Stage setPauseMenu() throws IOException{
        timer.stop();
        
        String savedMapParams = serializeMap(mapSetter.getMap());
        System.out.println(savedMapParams);
        String savedGameData = serializeHashMap(coordinates);
        
        BufferedWriter writer = new BufferedWriter(new FileWriter("savedMap.txt"));
        writer.write(savedMapParams);
        writer.close();
        writer = new BufferedWriter(new FileWriter("savedGameData.txt"));
        writer.write(savedGameData);
        writer.close();
        
        Scene scene = new Scene(ParentEnum.PauseGame.getPage());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        return stage;
    }
    
    /**
     * method that serialize game coordinates to String for saving the game
     * method of serialization is called from models and it is our own serialization
     * @param coordinates game coordinates
     * @return serialized string of data for loading
     */
    public String serializeHashMap(HashMap coordinates) {
        String serializedData = "";
        String string = "";

        ArrayList<Block> blocks = (ArrayList<Block>) coordinates.get("Undestructible");
        for (Block block : blocks) {
            string = string + block.getPositionX() + "," + block.getPositionY() + ":";
        }

        StringBuffer sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<DestructibleBlock> dBlocks = (ArrayList<DestructibleBlock>) coordinates.get("Destructible");
        if (dBlocks.size() != 0) {
            for (DestructibleBlock block : dBlocks) {
                string = string + block.getPositionX() + "," + block.getPositionY() + "," + block.getLifes() + ":";
            }
        } else {
            string = "xx";
        }

        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<Bonus> bonuses = (ArrayList<Bonus>) coordinates.get("Bonus");
        if (bonuses.size() != 0) {
            for (Bonus bonus : bonuses) {
                string = string + bonus.getPositionX() + "," + bonus.getPositionY() + "," + bonus.getBonusType() + ":";
            }
        } else {
            string = "xx";
        }

        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<Enemy> enemies = (ArrayList<Enemy>) coordinates.get("Basic Enemy");
        if (enemies.size() != 0) {
            for (Enemy enemy : enemies) {
                string = string + enemy.getPositionX() + "," + enemy.getPositionY() + "," + enemy.getNote() + ":";
            }
        } else {
            string = "xx";
        }

        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<Dummy> dummies = (ArrayList<Dummy>) coordinates.get("Dummy Enemy");
        if (dummies.size() != 0) {
            for (Dummy enemy : dummies) {
                string = string + enemy.getPositionX() + "," + enemy.getPositionY() + "," + enemy.getNote() + ":";
            }
        } else {
            string = "xx";
        }

        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<Speedy> speedies = (ArrayList<Speedy>) coordinates.get("Speedy Enemy");
        if (speedies.size() != 0) {
            for (Speedy enemy : speedies) {
                string = string + enemy.getPositionX() + "," + enemy.getPositionY() + "," + enemy.getNote() + ":";
            }
        } else {
            string = "xx";
        }

        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<Bomb> bombs = (ArrayList<Bomb>) coordinates.get("Bombs");
        if (bombs.size() != 0) {
            for (Bomb bomb : bombs) {
                string = string + bomb.getPositionX() + "," + bomb.getPositionY() + "," + bomb.getPlayerID() + ":";
            }
        } else {
            string = "xx";
        }
        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<Player> players = (ArrayList<Player>) coordinates.get("Player");
        for (Player player : players) {
            string = string + player.getLifes() + "," + player.getNumberOfBombs() + "," + player.getExplosionSize() + ","
                    + player.getPositionX() + "," + player.getPositionY() + "," + player.getSpeed() + "," + player.getPlayerID() + ":";
        }

        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<Gate> gates = (ArrayList<Gate>) coordinates.get("Gate");
        for (Gate gate : gates) {
            string = string + gate.getPositionX() + "," + gate.getPositionY() + ":";
        }

        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<Key> keys = (ArrayList<Key>) coordinates.get("Key");
        if (keys.size() != 0) {
            for (Key key : keys) {
                string = string + key.getPositionX() + "," + key.getPositionY() + ":";
            }
        } else {
            string = "xx";
        }

        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";
        return serializedData;
    }

    /**
     * Method that save map params for loading game
     * @param mapParams height and length map params
     * @return
     */
    public String serializeMap(Map mapParams) {
        String serializedData = mapParams.getSizeX()/45 + "," + mapParams.getSizeY()/45;       
        return serializedData;
    }
    
    
}
