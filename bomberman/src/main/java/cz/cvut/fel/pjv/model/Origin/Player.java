package cz.cvut.fel.pjv.model.Origin;

import cz.cvut.fel.pjv.model.Bonus.*;

/**
 * class of game hero
 * 
 */
public class Player {

    private int lifes;
    private int numberOfBombs;
    private int explosionSize;
    private int positionX;
    private int positionY;
    private int speed;
    private int PLAYER_SIZE = 35;
    private int BLOCK_SIZE = 45;
    private int playerID;

    /**
     *
     * @param i
     * @param i1
     * @param i2
     * @param i3
     * @param i4
     * @param i6
     * @param i5
     */
    public Player(int lifes, int numberOfBombs, int explosionSize, int positionX, int positionY, int speed, int playerID) {
        this.lifes = lifes;
        this.numberOfBombs = numberOfBombs;
        this.explosionSize = explosionSize; //NAHRAT Z CONFIGU
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        this.playerID = playerID;
    }

    /**
     *
     * @return
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     *
     * @return
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     *
     * @return
     */
    public int getSpeed() {
        return speed;
    }

    /**
     *
     * @return
     */
    public int getExplosionSize() {
        return explosionSize;
    }

    /**
     *
     * @param positionX
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     *
     * @param positionY
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    
    /**
     * method that put plazers bomb into map
     * @return new Instance of Bomb
     */
    public Bomb putBomb() { 
        int positionX = getPositionX();
        int positionY = getPositionY();

        positionX = (2 * positionX + PLAYER_SIZE) / 2;
        positionY = (2 * positionY + PLAYER_SIZE) / 2;
        positionX -= positionX % BLOCK_SIZE;
        positionY -= positionY % BLOCK_SIZE;
        return new Bomb(positionX, positionY, playerID);
    }

    /**
     *
     * @param lifes
     */
    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    /**
     *
     * @param numberOfBombs
     */
    public void setNumberOfBombs(int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
    }

    /**
     *
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     *
     * @param explosionSize
     */
    public void setExplosionSize(int explosionSize) {
        this.explosionSize = explosionSize;
    }

    /**
     *
     * @return
     */
    public int getLifes() {
        return lifes;
    }

    /**
     *
     * @return
     */
    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    /**
     *
     * @return
     */
    public int getPlayerID() {
        return playerID;
    }
    
    /**
     * this method read String data and convert them into new Player
     * Used for loading game and server comunication
     * @param stringPlayer StringDataOfPlayer
     * @return new instance of Player
     */
    static public Player readFromString(String stringPlayer){
        String[] array = stringPlayer.split(",");
        return new Player(
                Integer.parseInt(array[0]), 
                Integer.parseInt(array[1]),
                Integer.parseInt(array[2]),
                Integer.parseInt(array[3]),
                Integer.parseInt(array[4]),
                Integer.parseInt(array[5]),
                Integer.parseInt(array[6]));
    }
}
