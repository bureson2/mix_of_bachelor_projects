package cz.cvut.fel.pjv.model.Enemy;

import cz.cvut.fel.pjv.view.Gfx;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;

/**
 * class of basic game Enemy
 * basic enemy use directional vectors for moving
 * first direction vector is chosen randomdomly
 * another moves is always picked randomly
 */
public class Enemy {

    /**
     *
     */
    protected int positionX;

    /**
     *
     */
    protected int positionY;
    private int lifes = 1;
    private int speed = 1;
    private int direction = 0;

    /**
     *
     */
    public List<int[]> directionVector = new ArrayList<>();

    /**
     *
     */
    public List<Boolean> walkingDirection = new ArrayList<>();
    private Image image = Gfx.Enemy.getImage();
    private Image imageDeath = Gfx.EnemyDeath.getImage();

    /**
     *
     */
    protected String note;

    /**
     *
     * @param i
     * @param i1
     * @param string
     */
    public Enemy(int positionX, int positionY, String note) {
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
     * @return number oof actual direction
     */
    public int getDirection() {
        return direction;
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
     * @return image from GFX
     */
    public Image getImage() {
        return image;
    }

    /**
     *
     * @return image of death enemy GFX
     */
    public Image getImageDeath() {
        return imageDeath;
    }

    /**
     *
     * @return note with type of enemy
     */
    public String getNote() {
        return note;
    }

    /**
     *
     * @param direction
     */
    public void changePositionX(int direction) {
        if (walkingDirection.get(direction) == true) {
            this.positionX += (this.speed * directionVector.get(direction)[0]);
        }
    }

    /**
     *
     * @param direction
     */
    public void changePositionY(int direction) {

        if (walkingDirection.get(direction) == true) {
            this.positionY += (this.speed * directionVector.get(direction)[1]);
        }
    }

    /**
     * Method that changes direction vector after collision
     */
    public void changeDirection() {
        this.direction = new Random().nextInt(4);
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
     * @param lifes
     */
    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    /**
     *
     * @return
     */
    public int getLifes() {
        return lifes;
    }
    
    /**
     * this method read String data and convert them into new Enemy
     * Used for loading game and server comunication
     * @param stringEnemy string data of Enemy
     * @return new instance of Enemy
     */
    static public Enemy readFromString(String stringEnemy){
        String[] array = stringEnemy.split(",");
        return new Enemy(
                Integer.parseInt(array[0]), 
                Integer.parseInt(array[1]),
                array[1]);
    }
}
