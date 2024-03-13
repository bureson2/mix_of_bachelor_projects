package cz.cvut.fel.pjv.model.Block;

/**
 * class of Block
 * In basic form the block is undestructible
 */
public class Block{

    private int positionX;
    private int positionY;

    /**
     *
     * @param i
     * @param i1
     */
    public Block(int positionX, int positionY) {
        this.positionX = positionX;
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
     * this method read String data and convert them into new Block
     * Used for loading game and server comunication
     * @param stringBlock stringBonus
     * @return new instance of Block
     */
    static public Block readFromString(String stringBlock){
        String[] array = stringBlock.split(",");
        return new Block(
                Integer.parseInt(array[0]), 
                Integer.parseInt(array[1]));
    }
}

