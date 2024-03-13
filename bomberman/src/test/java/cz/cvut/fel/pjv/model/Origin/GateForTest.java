package cz.cvut.fel.pjv.model.Origin;

/**
 * Fake class of Gate
 * for delete dependency of graphic GFX
 */
public class GateForTest {
private boolean isOpen =false;
    private int positionX;
    private int positionY;
    
    /**
     *
     * @param positionX
     * @param positionY
     */
    public GateForTest(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     *
     * @param isOpen
     */
    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
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
    public boolean isIsOpen() {
        return isOpen;
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
}
