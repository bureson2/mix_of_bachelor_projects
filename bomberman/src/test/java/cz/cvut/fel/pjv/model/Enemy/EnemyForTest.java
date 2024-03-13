package cz.cvut.fel.pjv.model.Enemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Fake class of Enemy
 * for delete dependency of graphic GFX
 */
public class EnemyForTest {

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
    private List<int[]> directionVector = new ArrayList<>();
    private List<Boolean> walkingDirection = new ArrayList<>();

    /**
     *
     */
    protected String note;

    /**
     *
     * @param positionX
     * @param positionY
     * @param note
     */
    public EnemyForTest(int positionX, int positionY, String note) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.directionVector = Arrays.asList(new int[]{1, 0}, new int[]{0, -1}, new int[]{-1, 0}, new int[]{0, 1});
        this.walkingDirection = Arrays.asList(true, false, false, false);
        this.note = note;
    }

    // GETTERY

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
     * @return
     */
    public String getNote() {
        return note;
    }
    
    // OTHER METHODS

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
     *
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
}
