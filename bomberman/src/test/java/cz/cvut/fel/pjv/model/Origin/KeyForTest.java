package cz.cvut.fel.pjv.model.Origin;

/**
 * Fake class of Key
 * for delete dependency of graphic GFX
 */
public class KeyForTest {
    private Player owner;
    private int positionX;
    private int positionY;

    /**
     *
     * @param positionX
     * @param positionY
     */
    public KeyForTest(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     *
     * @param owner
     */
    public void setOwner(Player owner) {
        this.owner = owner;
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
    public Player getOwner() {
        return owner;
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
