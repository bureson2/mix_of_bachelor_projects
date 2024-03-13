package cz.cvut.fel.pjv.model.Enemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Fake class of Speedy
 * for delete dependency of graphic GFX
 */
public class SpeedyForTest extends EnemyForTest{
    private int lifes = 1;
    private double speed = 2;
    private int direction = 0;

    
    private List<int[]> directionVector = new ArrayList<>();
    private List<Boolean> walkingDirection = new ArrayList<>();

    /**
     *
     * @param positionX
     * @param positionY
     * @param note
     */
    public SpeedyForTest(int positionX, int positionY, String note) {
        super(positionX, positionY, note);
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
     * @return
     */
    @Override
    public int getDirection() {
        return direction;
    }
    
    /**
     *
     * @return
     */
    @Override 
    public String getNote() {
        return note;
    }
    
    // OTHER METHODS

    /**
     *
     */
    
    @Override
    public void changeDirection() {
        this.direction = (direction + 1) % 4;
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
}

