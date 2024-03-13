package cz.cvut.fel.pjv.model.Origin;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class of item Bomb
 * 
 */
public class Bomb {

    private int timeToExplosion;
    private int positionX;
    private int positionY;
    private int playerID;
    private Timer timer;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            timeToExplosion--;
        }
    };

    /**
     * 
     * @param i
     * @param i1
     * @param i2
     */
    public Bomb(int positionX, int positionY, int playerID) {
        this.timeToExplosion = 4;
        this.positionX = positionX;
        this.positionY = positionY;
        this.playerID = playerID;
    }

    /**
     * method that deactivate timer when bomb explode
     */
    public void deactivateTimer() {
        timer.cancel();
        timerTask.cancel();
    }

    /**
     *  method that activete timer of bomb explosion
     */
    public void activateTimer() {
        timer = new Timer();
        timer.schedule(timerTask, 1000, 1000);
    }

    /**
     *
     * @return int value of time to explode
     */
    public int getTimeToExplosion() {
        return timeToExplosion;
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
     * @return int player identifier
     */
    public int getPlayerID() {
        return playerID;
    }
    
    
    /**
     * this method read String data and convert them into new Bomb
     * Used for loading game and server comunication
     * @param stringBomb StringDataOfBomb
     * @return new instance of Bomb
     */
    static public Bomb readFromString(String stringBomb){
    String[] array = stringBomb.split(",");
        return new Bomb(
                Integer.parseInt(array[0]), 
                Integer.parseInt(array[1]), 
                Integer.parseInt(array[2]));
    } 
    
}
