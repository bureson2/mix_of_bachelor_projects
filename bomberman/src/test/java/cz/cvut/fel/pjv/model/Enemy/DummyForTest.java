
package cz.cvut.fel.pjv.model.Enemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Fake class of Dummy
 * for delete dependency of graphic GFX
 */
class DummyForTest extends EnemyForTest{
    private int lifes = 1;
    private double speed = 1;
    private int direction = 0;
    
    private List<int[]> directionVector = new ArrayList<>();
    private List<Boolean> walkingDirection = new ArrayList<>();

    public DummyForTest(int positionX, int positionY, String note) {
        super(positionX, positionY, note);
        this.positionX = positionX;
        this.positionY = positionY;
        this.directionVector = Arrays.asList(new int[]{1, 0}, new int[]{0, -1}, new int[]{-1, 0}, new int[]{0, 1});
        this.walkingDirection = Arrays.asList(true, false, false, false);
        this.note = note;
    }

    @Override
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }

    @Override
    public int getDirection() {
        return direction;
    }
    
    @Override 
    public String getNote() {
        return note;
    }
    
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

    @Override
    public void changePositionX(int direction) {
        if (walkingDirection.get(direction) == true) {
            this.positionX += (this.speed * directionVector.get(direction)[0]);
        }

    }

    @Override
    public void changePositionY(int direction) {
        if (walkingDirection.get(direction) == true) {
            this.positionY += (this.speed * directionVector.get(direction)[1]);
        }
    }  
}
