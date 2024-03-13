package cz.cvut.fel.pjv.model.Enemy;

import cz.cvut.fel.pjv.view.Gfx;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;

/**
 * class of game Enemy Dummy
 * basic enemy use directional vectors for moving
 * first direction vector is chosen randomdomly
 * dummy could move onlz horizontal or vertical
 */
public class Dummy extends Enemy {
    private int lifes = 1;
    private double speed = 1;
    private int direction = 0;
    
    private List<int[]> directionVector = new ArrayList<>();
    private List<Boolean> walkingDirection = new ArrayList<>();
    private Image image = Gfx.Dummy.getImage();
    private Image imageDeath = Gfx.DummyDeath.getImage();

    /**
     *
     * @param i
     * @param i1
     * @param string
     */
    public Dummy(int positionX, int positionY, String note) {
        super(positionX, positionY, note);
        this.positionX = positionX;
        this.positionY = positionY;
        this.directionVector = Arrays.asList(new int[]{1, 0}, new int[]{0, -1}, new int[]{-1, 0}, new int[]{0, 1});
        this.walkingDirection = Arrays.asList(true, false, false, false);
        this.note = note;
    }

    /**
     *
     * @return
     */
    @Override
    public int getPositionX() {
        return positionX;
    }

    /**
     *
     * @return
     */
    @Override
    public int getPositionY() {
        return positionY;
    }

    /**
     *
     * @return number oof actual direction
     */
    @Override
    public int getDirection() {
        return direction;
    }
    
    /**
     *
     * @return image from GFX
     */
    @Override
    public Image getImage() {
        return image;
    }
    
    /**
     *
     * @return image of death dummy enemy GFX
     */
    @Override
    public Image getImageDeath(){
        return imageDeath;
    }
    
    /**
     *
     * @return note with type of enemy
     */
    @Override 
    public String getNote() {
        return note;
    }

    /**
     * Method that changes direction vector to oposite collision
     */
    
    @Override
    public void changeDirection() {
        this.direction = (direction + 2) % 4;
        for (int i = 0; i < walkingDirection.size(); i++) {
            walkingDirection.set(i, false);
            if (i == direction) {
                walkingDirection.set(i, true);
                this.direction = i;
            }
        }
    }

    /**
     *
     * @param direction
     */
    @Override
    public void changePositionX(int direction) {
        if (walkingDirection.get(direction) == true) {
            this.positionX += (this.speed * directionVector.get(direction)[0]);
        }

    }

    /**
     *
     * @param direction
     */
    @Override
    public void changePositionY(int direction) {
        if (walkingDirection.get(direction) == true) {
            this.positionY += (this.speed * directionVector.get(direction)[1]);
        }
    }
    
    /**
     * this method read String data and convert them into new Dummy
     * Used for loading game and server comunication
     * @param stringEnemy string data of Dummy
     * @return new instance of Dummy
     */
    static public Dummy readFromString(String stringEnemy){
        String[] array = stringEnemy.split(",");
        return new Dummy(
                Integer.parseInt(array[0]), 
                Integer.parseInt(array[1]),
                array[1]);
    }
}
