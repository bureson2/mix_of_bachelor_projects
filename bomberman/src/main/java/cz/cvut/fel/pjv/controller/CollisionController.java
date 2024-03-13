package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.model.Block.Block;
import cz.cvut.fel.pjv.model.Block.DestructibleBlock;
import cz.cvut.fel.pjv.model.Bonus.Bonus;
import cz.cvut.fel.pjv.model.Enemy.Enemy;
import cz.cvut.fel.pjv.model.Origin.*;
import cz.cvut.fel.pjv.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * General collision controller
 * Used for collision with enemy
 * Used for collision with explosion
 * Used for collision with game items
 */
public class CollisionController {

    private final int PLAYER_SIZE = 35;
    private final int BLOCK_SIZE = 45;
    private final int POSITION_CORRECTION = 1;
        private final static Logger LOGGER =
            Logger.getLogger(CollisionController.class.getName());

    private ArrayList<DestructibleBlock> blocksToRemove;
    private ArrayList<DestructibleBlock> blocksToChange;
    private ArrayList<? super Enemy> enemyToRemove = new ArrayList<>();

    /**
     * method that control direction right
     * @param coordinates HashMap of allObjects
     * @param player specific instance that is controlled
     * @return
     */
    public boolean isRightWayEmpty(HashMap coordinates, Player player) {
        boolean isOk = true;

        for (Block block : (ArrayList<Block>) coordinates.get("Destructible")) {
            boolean controlTopOfPlayer = block.getPositionY() < player.getPositionY() && player.getPositionY() < block.getPositionY() + BLOCK_SIZE;
            boolean controlBottomOfPlayer = block.getPositionY() < player.getPositionY() + PLAYER_SIZE && player.getPositionY() < block.getPositionY() + BLOCK_SIZE;
            if ((block.getPositionX() == player.getPositionX() + PLAYER_SIZE || block.getPositionX() == player.getPositionX() + PLAYER_SIZE + 1) && (controlTopOfPlayer || controlBottomOfPlayer)) {
                return false;
            }

        }
        for (Block block : (ArrayList<Block>) coordinates.get("Undestructible")) {
            boolean controlTopOfPlayer = block.getPositionY() < player.getPositionY() && player.getPositionY() < block.getPositionY() + BLOCK_SIZE;
            boolean controlBottomOfPlayer = block.getPositionY() < player.getPositionY() + PLAYER_SIZE && player.getPositionY() < block.getPositionY() + BLOCK_SIZE;
            if ((block.getPositionX() == player.getPositionX() + PLAYER_SIZE || block.getPositionX() == player.getPositionX() + PLAYER_SIZE + 1) && (controlTopOfPlayer || controlBottomOfPlayer)) {
                return false;
            }
        }

        for (Bomb bomb : (ArrayList<Bomb>) coordinates.get("Bombs")) {
            boolean controlTopOfPlayer = bomb.getPositionY() < player.getPositionY() && player.getPositionY() < bomb.getPositionY() + BLOCK_SIZE;
            boolean controlBottomOfPlayer = bomb.getPositionY() < player.getPositionY() + PLAYER_SIZE && player.getPositionY() < bomb.getPositionY() + BLOCK_SIZE;
            if ((bomb.getPositionX() == player.getPositionX() + PLAYER_SIZE || bomb.getPositionX() == player.getPositionX() + PLAYER_SIZE + 1) && (controlTopOfPlayer || controlBottomOfPlayer)) {
                return false;
            }
        }
        return isOk;
    }

    /**
     * method that control direction left
     * @param coordinates HashMap of allObjects
     * @param player specific instance that is controlled
     * @return boolean value 
     * true = right is empty
     */
    public boolean isLeftWayEmpty(HashMap coordinates, Player player) {
        boolean isOk = true;

        for (Block block : (ArrayList<Block>) coordinates.get("Destructible")) {
            boolean controlTopOfPlayer = block.getPositionY() < player.getPositionY() && player.getPositionY() < block.getPositionY() + BLOCK_SIZE;
            boolean controlBottomOfPlayer = block.getPositionY() < player.getPositionY() + PLAYER_SIZE && player.getPositionY() < block.getPositionY() + BLOCK_SIZE;
            if ((block.getPositionX() + BLOCK_SIZE == player.getPositionX() || block.getPositionX() + BLOCK_SIZE == player.getPositionX() + 1) && (controlTopOfPlayer || controlBottomOfPlayer)) {
                return false;
            }
        }
        for (Block block : (ArrayList<Block>) coordinates.get("Undestructible")) {
            boolean controlTopOfPlayer = block.getPositionY() < player.getPositionY() && player.getPositionY() < block.getPositionY() + BLOCK_SIZE;
            boolean controlBottomOfPlayer = block.getPositionY() < player.getPositionY() + PLAYER_SIZE && player.getPositionY() < block.getPositionY() + BLOCK_SIZE;
            if ((block.getPositionX() + BLOCK_SIZE == player.getPositionX() || block.getPositionX() + BLOCK_SIZE == player.getPositionX() + 1) && (controlTopOfPlayer || controlBottomOfPlayer)) {
                return false;
            }
        }

        for (Bomb bomb : (ArrayList<Bomb>) coordinates.get("Bombs")) {
            boolean controlTopOfPlayer = bomb.getPositionY() < player.getPositionY() && player.getPositionY() < bomb.getPositionY() + BLOCK_SIZE;
            boolean controlBottomOfPlayer = bomb.getPositionY() < player.getPositionY() + PLAYER_SIZE && player.getPositionY() < bomb.getPositionY() + BLOCK_SIZE;
            if ((bomb.getPositionX() + BLOCK_SIZE == player.getPositionX() || bomb.getPositionX() + BLOCK_SIZE == player.getPositionX() + 1) && (controlTopOfPlayer || controlBottomOfPlayer)) {
                return false;
            }
        }
        return isOk;
    }

    /**
     * method that control direction up
     * @param coordinates HashMap of allObjects
     * @param player specific instance that is controlled
     * @return boolean value 
     * true = right is empty
     */
    public boolean isTopWayEmpty(HashMap coordinates, Player player) {
        boolean isOk = true;

        for (Block block : (ArrayList<Block>) coordinates.get("Destructible")) {
            boolean controlTopOfPlayer = block.getPositionX() < player.getPositionX() && player.getPositionX() < block.getPositionX() + BLOCK_SIZE;
            boolean controlBottomOfPlayer = block.getPositionX() < player.getPositionX() + PLAYER_SIZE && player.getPositionX() < block.getPositionX() + BLOCK_SIZE;
            if ((block.getPositionY() + BLOCK_SIZE == player.getPositionY() || block.getPositionY() + BLOCK_SIZE == player.getPositionY() + 1) && (controlTopOfPlayer || controlBottomOfPlayer)) {
                return false;
            }
        }
        for (Block block : (ArrayList<Block>) coordinates.get("Undestructible")) {
            boolean controlTopOfPlayer = block.getPositionX() < player.getPositionX() && player.getPositionX() < block.getPositionX() + BLOCK_SIZE;
            boolean controlBottomOfPlayer = block.getPositionX() < player.getPositionX() + PLAYER_SIZE && player.getPositionX() < block.getPositionX() + BLOCK_SIZE;
            if ((block.getPositionY() + BLOCK_SIZE == player.getPositionY() || block.getPositionY() + BLOCK_SIZE == player.getPositionY() + 1) && (controlTopOfPlayer || controlBottomOfPlayer)) {
                return false;
            }
        }

        for (Bomb bomb : (ArrayList<Bomb>) coordinates.get("Bombs")) {
            boolean controlTopOfPlayer = bomb.getPositionX() < player.getPositionX() && player.getPositionX() < bomb.getPositionX() + BLOCK_SIZE;
            boolean controlBottomOfPlayer = bomb.getPositionX() < player.getPositionX() + PLAYER_SIZE && player.getPositionX() < bomb.getPositionX() + BLOCK_SIZE;
            if ((bomb.getPositionY() + BLOCK_SIZE == player.getPositionY() || bomb.getPositionY() + BLOCK_SIZE == player.getPositionY() + 1) && (controlTopOfPlayer || controlBottomOfPlayer)) {
                return false;
            }
        }
        return isOk;

    }

    /**
     * method that control direction down
     * @param coordinates HashMap of allObjects
     * @param player specific instance that is controlled
     * @return boolean value 
     * true = right is empty
     */
    public boolean isBottomWayEmpty(HashMap coordinates, Player player) {
        boolean isOk = true;

        for (Block block : (ArrayList<Block>) coordinates.get("Destructible")) {
            boolean controlTopOfPlayer = block.getPositionX() < player.getPositionX() && player.getPositionX() < block.getPositionX() + BLOCK_SIZE;
            boolean controlBottomOfPlayer = block.getPositionX() < player.getPositionX() + PLAYER_SIZE && player.getPositionX() < block.getPositionX() + BLOCK_SIZE;
            if ((block.getPositionY() == player.getPositionY() + PLAYER_SIZE || block.getPositionY() == player.getPositionY() + PLAYER_SIZE + 1) && (controlTopOfPlayer || controlBottomOfPlayer)) {
                return false;
            }
        }
        for (Block block : (ArrayList<Block>) coordinates.get("Undestructible")) {
            boolean controlTopOfPlayer = block.getPositionX() < player.getPositionX() && player.getPositionX() < block.getPositionX() + BLOCK_SIZE;
            boolean controlBottomOfPlayer = block.getPositionX() < player.getPositionX() + PLAYER_SIZE && player.getPositionX() < block.getPositionX() + BLOCK_SIZE;
            if ((block.getPositionY() == player.getPositionY() + PLAYER_SIZE || block.getPositionY() == player.getPositionY() + PLAYER_SIZE + 1) && (controlTopOfPlayer || controlBottomOfPlayer)) {
                return false;
            }
        }

        for (Bomb bomb : (ArrayList<Bomb>) coordinates.get("Bombs")) {
            boolean controlTopOfPlayer = bomb.getPositionX() < player.getPositionX() && player.getPositionX() < bomb.getPositionX() + BLOCK_SIZE;
            boolean controlBottomOfPlayer = bomb.getPositionX() < player.getPositionX() + PLAYER_SIZE && player.getPositionX() < bomb.getPositionX() + BLOCK_SIZE;
            if ((bomb.getPositionY() == player.getPositionY() + PLAYER_SIZE || bomb.getPositionY() == player.getPositionY() + PLAYER_SIZE + 1) && (controlTopOfPlayer || controlBottomOfPlayer)) {
                return false;
            }
        }
        return isOk;
    }

    /**
     * Method tested if player find a key
     * @param players list of players
     * @param key item that will open the gate
     * @return boolean value
     * true = gate is opened
     */
    public boolean checkPlayerCollisionKey(ArrayList<Player> players, Key key) {
        for (Player player : players) {
            if (isCollisionWithObject(player.getPositionX(), player.getPositionY(), key.getPositionX(), key.getPositionY())) {
                LOGGER.info("Player open Gate");
                return true;           
            }
        }
        return false;
    }

    /**
     * Method that test if player escaped and win the game
     * @param players list of players
     * @param exitGate exit point from map
     * @return value for winning the game
     */
    public boolean isPlayerWinner(ArrayList<Player> players, Gate exitGate) {
        for (Player player : players) {
            if (isCollisionWithObject(player.getPositionX(), player.getPositionY(), exitGate.getPositionX(), exitGate.getPositionY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that test if player picked some bonus
     * @param bonus tested bonus
     * @param player tested player
     * @return
     */
    public boolean collisionWithBonus(Bonus bonus, Player player) {
        if (isCollisionWithObject(player.getPositionX(), player.getPositionY(), bonus.getPositionX(), bonus.getPositionY())) {
            player = bonus.setBonusToPlayer(player);
            LOGGER.info("player get bonus " +  bonus.getBonusType());
            return true;
        }
        return false;
    }

    /**
     * help method for detection collision with key and gate
     * @param playerPositionX
     * @param playerPositionY
     * @param objectPositionX X position of kez or gate
     * @param objectPositionY Y position of kez or gate
     * @return
     */
    public boolean isCollisionWithObject(int playerPositionX, int playerPositionY, int objectPositionX, int objectPositionY) {
        if ((playerPositionX / 45) == (objectPositionX / 45)) {
            if ((playerPositionY / 45) == (objectPositionY / 45)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that control if  player has a small contact with enemy
     * there is small reserve, that dont kill player
     * it is counted by mid coordinates value from player and enemy
     * @param player who is tested for collision with enemy
     * @param coordinates HashMap of allObjects
     * @param startPositions corners position to respawn player
     * @param view
     */
    public void controllEnemyColision(Player player, HashMap coordinates, ArrayList<int[]> startPositions, View view) {
        for (Enemy enemy : (ArrayList<Enemy>) coordinates.get("Basic Enemy")) {
            int playerAvgX = (2 * player.getPositionX() + PLAYER_SIZE) / 2;
            int playerAvgY = (2 * player.getPositionY() + PLAYER_SIZE) / 2;
            int enemyAvgX = (2 * enemy.getPositionX() + BLOCK_SIZE) / 2;
            int enemyAvgY = (2 * enemy.getPositionY() + BLOCK_SIZE) / 2;
            if(Math.abs(playerAvgX - enemyAvgX) < 28){
                if(Math.abs(playerAvgY - enemyAvgY) < 28){
                    restartPlayerAfterDead(player, coordinates, startPositions, view);  
            }
            }
        }
        
        for (Enemy enemy : (ArrayList<Enemy>) coordinates.get("Dummy Enemy")) {
            int playerAvgX = (2 * player.getPositionX() + PLAYER_SIZE) / 2;
            int playerAvgY = (2 * player.getPositionY() + PLAYER_SIZE) / 2;
            int enemyAvgX = (2 * enemy.getPositionX() + BLOCK_SIZE) / 2;
            int enemyAvgY = (2 * enemy.getPositionY() + BLOCK_SIZE) / 2;
            if(Math.abs(playerAvgX - enemyAvgX) < 28){
                if(Math.abs(playerAvgY - enemyAvgY) < 28){
                    restartPlayerAfterDead(player, coordinates, startPositions, view);  
            }
            }
        }
        
        for (Enemy enemy : (ArrayList<Enemy>) coordinates.get("Speedy Enemy")) {
            int playerAvgX = (2 * player.getPositionX() + PLAYER_SIZE) / 2;
            int playerAvgY = (2 * player.getPositionY() + PLAYER_SIZE) / 2;
            int enemyAvgX = (2 * enemy.getPositionX() + BLOCK_SIZE) / 2;
            int enemyAvgY = (2 * enemy.getPositionY() + BLOCK_SIZE) / 2;
            if(Math.abs(playerAvgX - enemyAvgX) < 28){
                if(Math.abs(playerAvgY - enemyAvgY) < 28){
                    restartPlayerAfterDead(player, coordinates, startPositions, view);  
            }
            }
        }
    }

    /**
     * Method that control direction right for instance of enemy
     * @param enemy which is controlled
     * @param coordinates HashMap of allObjects
     * @return
     */
    public boolean isRightWayEmptyForEnemy(Enemy enemy, HashMap coordinates) {
        boolean isOk = true;

        for (Block block : (ArrayList<Block>) coordinates.get("Destructible")) {
            if ((enemy.getPositionX() / BLOCK_SIZE) + 1 == block.getPositionX() / BLOCK_SIZE) {
                if (enemy.getPositionY() / BLOCK_SIZE == block.getPositionY() / BLOCK_SIZE) {
                    return false;
                }
            }
        }
        for (Block block : (ArrayList<Block>) coordinates.get("Undestructible")) {
            if ((enemy.getPositionX() / BLOCK_SIZE) + 1 == block.getPositionX() / BLOCK_SIZE) {
                if (enemy.getPositionY() / BLOCK_SIZE == block.getPositionY() / BLOCK_SIZE) {
                    return false;
                }
            }
        }
        for (Bomb bomb : (ArrayList<Bomb>) coordinates.get("Bombs")) {
            if ((enemy.getPositionX() / BLOCK_SIZE) + 1 == bomb.getPositionX() / BLOCK_SIZE) {
                if (enemy.getPositionY() / BLOCK_SIZE == bomb.getPositionY() / BLOCK_SIZE) {
                    return false;
                }
            }
        }
        return isOk;
    }

    /**
     * Method that control direction left for instance of enemy
     * @param enemy which is controlled
     * @param coordinates HashMap of allObjects
     * @return
     */
    public boolean isLeftWayEmptyForEnemy(Enemy enemy, HashMap coordinates) {
        boolean isOk = true;

        for (Block block : (ArrayList<Block>) coordinates.get("Destructible")) {
            if (enemy.getPositionX() / BLOCK_SIZE == (block.getPositionX() / BLOCK_SIZE)) {
                if (enemy.getPositionY() / BLOCK_SIZE == block.getPositionY() / BLOCK_SIZE) {
                    return false;
                }
            }
        }
        for (Block block : (ArrayList<Block>) coordinates.get("Undestructible")) {
            if (enemy.getPositionX() / BLOCK_SIZE == (block.getPositionX() / BLOCK_SIZE)) {
                if (enemy.getPositionY() / BLOCK_SIZE == block.getPositionY() / BLOCK_SIZE) {
                    return false;
                }
            }
        }

        for (Bomb bomb : (ArrayList<Bomb>) coordinates.get("Bombs")) {
            if (enemy.getPositionX() / BLOCK_SIZE == (bomb.getPositionX() / BLOCK_SIZE)) {
                if (enemy.getPositionY() / BLOCK_SIZE == bomb.getPositionY() / BLOCK_SIZE) {
                    return false;
                }
            }
        }
        return isOk;
    }

    /**
     * Method that control direction up for instance of enemy
     * @param enemy which is controlled
     * @param coordinates HashMap of allObjects
     * @return
     */
    public boolean isTopWayEmptyForEnemy(Enemy enemy, HashMap coordinates) {
        boolean isOk = true;

        for (Block block : (ArrayList<Block>) coordinates.get("Destructible")) {
            if (enemy.getPositionY() / BLOCK_SIZE == (block.getPositionY() / BLOCK_SIZE)) {
                if (enemy.getPositionX() / BLOCK_SIZE == block.getPositionX() / BLOCK_SIZE) {
                    return false;
                }
            }
        }
        for (Block block : (ArrayList<Block>) coordinates.get("Undestructible")) {
            if (enemy.getPositionY() / BLOCK_SIZE == (block.getPositionY() / BLOCK_SIZE)) {
                if (enemy.getPositionX() / BLOCK_SIZE == block.getPositionX() / BLOCK_SIZE) {
                    return false;
                }
            }
        }

        for (Bomb bomb : (ArrayList<Bomb>) coordinates.get("Bombs")) {
            if (enemy.getPositionY() / BLOCK_SIZE == (bomb.getPositionY() / BLOCK_SIZE)) {
                if (enemy.getPositionX() / BLOCK_SIZE == bomb.getPositionX() / BLOCK_SIZE) {
                    return false;
                }
            }
        }
        return isOk;
    }

    /**
     * Method that control direction down for instance of enemy
     * @param enemy which is controlled
     * @param coordinates HashMap of allObjects
     * @return
     */
    public boolean isBottomWayEmptyForEnemy(Enemy enemy, HashMap coordinates) {
        boolean isOk = true;

        for (Block block : (ArrayList<Block>) coordinates.get("Destructible")) {
            if ((enemy.getPositionY() / BLOCK_SIZE) + 1 == block.getPositionY() / BLOCK_SIZE) {
                if (enemy.getPositionX() / BLOCK_SIZE == block.getPositionX() / BLOCK_SIZE) {
                    return false;
                }
            }
        }
        for (Block block : (ArrayList<Block>) coordinates.get("Undestructible")) {
            if ((enemy.getPositionY() / BLOCK_SIZE) + 1 == block.getPositionY() / BLOCK_SIZE) {
                if (enemy.getPositionX() / BLOCK_SIZE == block.getPositionX() / BLOCK_SIZE) {
                    return false;
                }
            }
        }

        for (Bomb bomb : (ArrayList<Bomb>) coordinates.get("Bombs")) {
            if ((enemy.getPositionY() / BLOCK_SIZE) + 1 == bomb.getPositionY() / BLOCK_SIZE) {
                if (enemy.getPositionX() / BLOCK_SIZE == bomb.getPositionX() / BLOCK_SIZE) {
                    return false;
                }
            }
        }
        return isOk;
    }

    /**
     * method that prepare coordinates for explosion
     * it used direction vectors adding explosion
     * there is set size of explosion and in all directions is controlled stop collision with blocks
     * @param bomb instance of bomb
     * @param coordinates HashMap of allObjects
     * @param player who put the bomb
     * @param startPositions list of corners of map
     * @param view
     * @return vectors = help value for coordinates
     */
    public List listOfVectorForExplosion(Bomb bomb, HashMap coordinates, Player player, ArrayList<int[]> startPositions, View view) {
        List<int[]> vectorDirections = new ArrayList<>();
        vectorDirections.add(new int[]{bomb.getPositionX(), bomb.getPositionY()});

        blocksToRemove = new ArrayList<>();
        blocksToChange = new ArrayList<>();

        for (int j = 1; j <= player.getExplosionSize(); j++) {
            int sizeOfListBlocksbeforeRemove = blocksToRemove.size();
            int sizeOfListBlocksbeforeChange = blocksToChange.size();
            int positionX = bomb.getPositionX() + (j * BLOCK_SIZE);
            int positionY = bomb.getPositionY();
            int[] vector = VectorsDirectionForExplode(bomb, coordinates, player, view, positionX, positionY);
            if (vector.length > 0) {
                vectorDirections.add(vector);
                if (blocksToRemove.size() > sizeOfListBlocksbeforeRemove || blocksToChange.size() > sizeOfListBlocksbeforeChange) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int j = 1; j <= player.getExplosionSize(); j++) {
            int sizeOfListBlocksbeforeRemove = blocksToRemove.size();
            int sizeOfListBlocksbeforeChange = blocksToChange.size();
            int positionX = bomb.getPositionX() - (j * BLOCK_SIZE);
            int positionY = bomb.getPositionY();
            int[] vector = VectorsDirectionForExplode(bomb, coordinates, player, view, positionX, positionY);
            if (vector.length > 0) {
                vectorDirections.add(vector);
                if (blocksToRemove.size() > sizeOfListBlocksbeforeRemove || blocksToChange.size() > sizeOfListBlocksbeforeChange) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int j = 1; j <= player.getExplosionSize(); j++) {
            int sizeOfListBlocksbeforeRemove = blocksToRemove.size();
            int sizeOfListBlocksbeforeChange = blocksToChange.size();
            int positionX = bomb.getPositionX();
            int positionY = bomb.getPositionY() - (j * BLOCK_SIZE);
            int[] vector = VectorsDirectionForExplode(bomb, coordinates, player, view, positionX, positionY);
            if (vector.length > 0) {
                vectorDirections.add(vector);
                if (blocksToRemove.size() > sizeOfListBlocksbeforeRemove || blocksToChange.size() > sizeOfListBlocksbeforeChange) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int j = 1; j <= player.getExplosionSize(); j++) {
            int sizeOfListBlocksbeforeRemove = blocksToRemove.size();
            int sizeOfListBlocksbeforeChange = blocksToChange.size();
            int positionX = bomb.getPositionX();
            int positionY = bomb.getPositionY() + (j * BLOCK_SIZE);
            int[] vector = VectorsDirectionForExplode(bomb, coordinates, player, view, positionX, positionY);
            if (vector.length > 0) {
                vectorDirections.add(vector);
                if (blocksToRemove.size() > sizeOfListBlocksbeforeRemove || blocksToChange.size() > sizeOfListBlocksbeforeChange) {
                    break;
                }
            } else {
                break;
            }
        }
        explodeEnemyColision(coordinates, vectorDirections);
        explodePlayerColision(coordinates, vectorDirections, startPositions, view);
        return vectorDirections;
    }

    /**
     * method that test collision of explosion with blocks
     * @param bomb instance of Bomb
     * @param coordinates HashMap of allObjects
     * @param player who put the bomb
     * @param view
     * @param positionX
     * @param positionY
     * @return
     */
    public int[] VectorsDirectionForExplode(Bomb bomb, HashMap coordinates, Player player, View view, int positionX, int positionY) {
        boolean x = false;
        boolean y = false;
        ArrayList<Block> undestructibleBlocks = (ArrayList<Block>) coordinates.get("Undestructible");
        ArrayList<DestructibleBlock> destructibleBlocks = (ArrayList<DestructibleBlock>) coordinates.get("Destructible");
        for (Block block : undestructibleBlocks) {
            if (positionX == block.getPositionX() && positionY == block.getPositionY()) {
                return new int[]{};
            } else {
                x = true;
            }
        }
        if (destructibleBlocks.size() == 0) {
            y = true;
        }
        for (DestructibleBlock destructibleBlock : destructibleBlocks) {
            if (positionX == destructibleBlock.getPositionX() && positionY == destructibleBlock.getPositionY()) {
                y = false;
                destructibleBlock.decraseLifes();
                if (destructibleBlock.getLifes() == 0) {
                    blocksToRemove.add(destructibleBlock);
                    destructibleBlocks.remove(destructibleBlock);
                    return new int[]{positionX, positionY};
                } else {
                    blocksToChange.add(destructibleBlock);
                    return new int[]{positionX, positionY};
                }
            } else {
                y = true;
            }
        }
        if (x && y) {
            return new int[]{positionX, positionY};
        }
        return new int[]{};
    }
    
    /**
     * method that test collision of explosion with players
     * @param coordinates HashMap of allObjects
     * @param vectorDirections help values for counting explosion coordinates
     * @param startPositions list of corners to respawn player
     * @param view
     */
    public void explodePlayerColision(HashMap coordinates, List<int[]> vectorDirections, ArrayList<int[]> startPositions, View view){
        for(Player player : (ArrayList<Player>)coordinates.get("Player")){
                
            int playerAvgX = (2 * player.getPositionX() + PLAYER_SIZE) / 2 / BLOCK_SIZE;
            int playerAvgY = (2 * player.getPositionY() + PLAYER_SIZE) / 2 / BLOCK_SIZE;       
            
            for (int i = 0; i < vectorDirections.size(); i++) {
                int positionX = vectorDirections.get(i)[0] / BLOCK_SIZE;
                int positionY = vectorDirections.get(i)[1] / BLOCK_SIZE;
 
                if(playerAvgX == positionX && playerAvgY == positionY){
                    restartPlayerAfterDead(player, coordinates, startPositions, view);
                }
            }
        }
    }
    
    /**
     * method that test collision of explosion with enemies
     * only redirect enemies of different type
     * @param coordinates HashMap of allObjects
     * @param vectorDirections help values for counting explosion coordinates
     */
    public void explodeEnemyColision(HashMap coordinates, List<int[]> vectorDirections) {
        enemyToRemove = new ArrayList<Enemy>();
        for (int i = 0; i < vectorDirections.size(); i++) {

            int positionX = vectorDirections.get(i)[0] / BLOCK_SIZE;
            int positionY = vectorDirections.get(i)[1] / BLOCK_SIZE;

            addEnemyToRemoveList(positionX, positionY, (ArrayList<Enemy>) coordinates.get("Basic Enemy"));
            addEnemyToRemoveList(positionX, positionY, (ArrayList<Enemy>) coordinates.get("Dummy Enemy"));
            addEnemyToRemoveList(positionX, positionY, (ArrayList<Enemy>) coordinates.get("Speedy Enemy"));
        }
    }
    
    /**
     * method that prepare list of enemies to remove from game and view
     * the enemies id directed from explodeEnemyColision
     * @param positionX
     * @param positionY
     * @param enemies list of all enemies
     */
    public void addEnemyToRemoveList(int positionX, int positionY, ArrayList<Enemy> enemies) {
        for (Enemy enemy : (ArrayList<Enemy>) enemies) {
            int enemyPositionX = enemy.getPositionX() / BLOCK_SIZE;
            int enemyPositionY = enemy.getPositionY() / BLOCK_SIZE;

            if (enemyPositionX == positionX && positionY == enemyPositionY) {
                enemyToRemove.add(enemy);
            } else if (enemyPositionX + POSITION_CORRECTION == positionX) {
                if (enemy.getPositionX() % 45 > 0) {
                    if (positionY == enemyPositionY) {
                        enemyToRemove.add(enemy);
                    }
                }
            } else if (enemyPositionY + POSITION_CORRECTION == positionY) {
                if (enemy.getPositionY() % 45 > 0) {
                    if (positionX == enemyPositionX) {
                        enemyToRemove.add(enemy);
                    }
                }
            }
        }
    }

    /**
     * method that control if the place for bomb doesnt contain bomb at this moment
     * @param player who put the bomb
     * @param coordinates HashMap of allObjects
     * @return boolean value
     */
    public boolean isPossibleToPutBomb(Player player, HashMap coordinates) {
        int positionX = player.getPositionX() - (player.getPositionX() % BLOCK_SIZE);
        int positionY = player.getPositionY() - (player.getPositionY() % BLOCK_SIZE);
        int playerActiveBomb = 0;
        for (Bomb bomb : (ArrayList<Bomb>) coordinates.get("Bombs")) {
            if (positionX == bomb.getPositionX() && positionY == bomb.getPositionY()) {
                LOGGER.info("Bomb was not putted");
                return false;
            }
            if (bomb.getPlayerID() == player.getPlayerID()) {
                playerActiveBomb += 1;
                if (playerActiveBomb >= player.getNumberOfBombs()) {
                    LOGGER.info("Player has only "+ player.getNumberOfBombs() + " bombs");
                    return false;
                }
            }
        }
        LOGGER.info("Bomb was putted succesfull");
        return true;
    }


    /**
     * method that restart player on random start position after his death
     * @param player which died
     * @param coordinates HashMap of allObjects
     * @param startPositions list of corners
     * @param view 
     */
    public void restartPlayerAfterDead(Player player, HashMap coordinates, ArrayList<int[]> startPositions, View view) {
        player.setLifes(player.getLifes() - 1);
        if (player.getLifes() > 0) {
            int randomStartPosition = new Random().nextInt(startPositions.size());
            ((ArrayList<Player>) coordinates.get("Player")).remove(player);
            player.setPositionX(startPositions.get(randomStartPosition)[0]);
            player.setPositionY(startPositions.get(randomStartPosition)[1]);
            ((ArrayList<Player>) coordinates.get("Player")).add(player);
            coordinates.put("Player", coordinates.get("Player"));
            view.playerRelocate(player);
            view.playerRespanAnimation(player);
            LOGGER.info("Player lost 1 life");
        } else {
            ((ArrayList<Player>) coordinates.get("Player")).remove(player);
            LOGGER.info("Player died");
        }
    }
    
    /**
     *
     * @return blocks which were destroyed by bomb explosion to remove from view
     */
    public ArrayList<DestructibleBlock> getBlocksToRemove() {
        return blocksToRemove;
    }

    /**
     * 
     * @return list of blocks that were damaged
     */
    public ArrayList<DestructibleBlock> getBlocksToChange() {
        return blocksToChange;
    }

    /**
     *
     * @return list of enemy to remove from view after their death
     */
    public ArrayList<? super Enemy> getEnemyToRemove() {
        return enemyToRemove;
    }
}
 