package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.model.Bonus.Bonus;
import cz.cvut.fel.pjv.model.Enemy.*;
import cz.cvut.fel.pjv.model.Origin.*;
import cz.cvut.fel.pjv.view.View;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;

/**
 * mulitplaer game class
 * prepared for online game
 * Controller of game
 * Computed, stores and directs all game actions
 * 
 */
public class MultiplayerGameServer{
    
    private Server server;

    private View view;
    private MapSetter mapSetter = new MapSetter();
    private CollisionController collisionController = new CollisionController();
    private int frameCounter = 0;
    private AnimationTimer timer;
    private Player player1;
    private Player player2;
        private final static Logger LOGGER
            = Logger.getLogger(Game.class.getName());

    private HashMap<String, ArrayList> coordinates = new HashMap<>();
    private ArrayList<Enemy> enemyList;
    private ArrayList<Dummy> enemyDummyList;
    private ArrayList<Speedy> enemySpeedyList;
    private ArrayList<Player> playersList;
    private ArrayList<Bomb> bombList = new ArrayList<Bomb>();

    private boolean goUp, goDown, goLeft, goRight, goUp2, goDown2, goLeft2, goRight2;

    final private int BLOCK_SIZE = 45;

    /**
     *
     * @param server
     */
    public MultiplayerGameServer(Server server) {
        this.server = server;
    }

    /**
     *
     */
    public MultiplayerGameServer() {
    }

    /**
     * Method that set map and enemies
     * start frame timer 
     * every frame controll collisions, movings, putting bombs and deaths
     * send every 20th frame to client
     * check if player pause game
     * @throws IOException
     */
    public void startGame() throws IOException {
        LOGGER.info("GAME START");  
        coordinates = mapSetter.getObjectCoordinates();
        Map map = mapSetter.getMap();

        enemyList = coordinates.get("Basic Enemy");
        enemyDummyList = coordinates.get("Dummy Enemy");
        enemySpeedyList = coordinates.get("Speedy Enemy");
        playersList = coordinates.get("Player");
        player1 = playersList.get(0);
        player2 = new Player(3, 3, 3, 0, 0, 2, 2);
        player2 = mapSetter.setPlayerOnStartPosition(player2);
        playersList.add(player2);
        bombList = coordinates.get("Bombs");

        view = new View(map, coordinates);
        view.setWindow();
        server.sendMap(map);
        
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (playersList.size() > 0) {
                    keyMovementListener();
                    view.playerRelocate(player1);
                    view.redrawHeader(player1);
                    view.playerRelocate(player2);
                    view.redrawHeader2(player2); 
                    if(player1.getLifes() == 0 || player2.getLifes() == 0){
                        try {
                            stopGameForLose();
                            LOGGER.info("GAME OVER");  
                        } catch (IOException ex) {
                            Logger.getLogger(MultiplayerGameServer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    try {
                        if(frameCounter == 20){
                            server.sendMessage(coordinates);
                            frameCounter = 0;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(MultiplayerGameServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    frameCounter++;
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
                    collisionController.controllEnemyColision(player1, coordinates, mapSetter.getStartPositions(), view);
                    collisionController.controllEnemyColision(player2, coordinates, mapSetter.getStartPositions(), view);
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
                            Player bombPutter;
                            if(bomb.getPlayerID() == 1){
                                bombPutter = player1;
                            } else {
                                bombPutter = player2;
                            }
                            
                            view.explodeBomb(collisionController.listOfVectorForExplosion(
                                    bomb, coordinates, bombPutter, mapSetter.getStartPositions(), view));                       
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
        timer.stop();
        view.closeStage();
        view.endGame();
    }

    /**
     * method that end game for win
     * @throws IOException
     */
    public void stopGameWin() throws IOException {
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
    public void moveEnemy() { //tohle musi jit dostat do inteligentnejsi podoby, idealne do modelu
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
     * player can move, put bombs
     * there is tested if player press or released a key
     */
    public void keyMovementListener() {      

        if (playersList.size() != 0) {

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
                            if (collisionController.isPossibleToPutBomb(player1, coordinates)) {
                                Bomb bomb = player1.putBomb();
                                coordinates.get("Bombs").add(bomb);
                                bomb.activateTimer();
                                view.setBombToView(bomb.getPositionX(), bomb.getPositionY());
                            }
                            break;
                        case W:
                            goUp2 = true;
                            break;
                        case S:
                            goDown2 = true;
                            break;
                        case A:
                            goLeft2 = true;
                            break;
                        case D:
                            goRight2 = true;
                            break;
                        case SHIFT:
                            if (collisionController.isPossibleToPutBomb(player2, coordinates)) {
                                Bomb bomb = player2.putBomb();
                                coordinates.get("Bombs").add(bomb);
                                bomb.activateTimer();
                                view.setBombToView(bomb.getPositionX(), bomb.getPositionY());
                            }  
                            break;
                          
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
                        case W:
                            goUp2 = false;
                            break;
                        case S:
                            goDown2 = false;
                            break;
                        case A:
                            goLeft2 = false;
                            break;
                        case D:
                            goRight2 = false;
                            break;
                    }
                }
            });

            int delta = player1.getSpeed();
            int currX = player1.getPositionX();
            int currY = player1.getPositionY();

            if (goUp) {
                if (collisionController.isTopWayEmpty(coordinates, player1)) {
                    currY -= delta;
                }
            }
            if (goDown) {
                if (collisionController.isBottomWayEmpty(coordinates, player1)) {
                    currY += delta;
                }
            }
            if (goLeft) {
                if (collisionController.isLeftWayEmpty(coordinates, player1)) {
                    currX -= delta;
                }
            }
            if (goRight) {
                if (collisionController.isRightWayEmpty(coordinates, player1)) {
                    currX += delta;
                }
            }
            player1.setPositionX(currX);
            player1.setPositionY(currY);
            
            delta = player2.getSpeed();
            currX = player2.getPositionX();
            currY = player2.getPositionY();

            if (goUp2) {
                if (collisionController.isTopWayEmpty(coordinates, player2)) {
                    currY -= delta;
                }
            }
            if (goDown2) {
                if (collisionController.isBottomWayEmpty(coordinates, player2)) {
                    currY += delta;
                }
            }
            if (goLeft2) {
                if (collisionController.isLeftWayEmpty(coordinates, player2)) {
                    currX -= delta;
                }
            }
            if (goRight2) {
                if (collisionController.isRightWayEmpty(coordinates, player2)) {
                    currX += delta;
                }
            }
            player2.setPositionX(currX);
            player2.setPositionY(currY);
        }
    }
}
