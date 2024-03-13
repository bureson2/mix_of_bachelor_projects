package cz.cvut.fel.pjv.model.Block;

/**
 * class of DestructibleBlock
 * Extends from Block
 * it is destructible by bomb
 * it could have more than one life
 */
public class DestructibleBlock extends Block{
    private int lifes;
    
    /**
     *
     * @param i
     * @param i2
     * @param i1
     */
    public DestructibleBlock(int lifes, int positionX, int positionY) {
        super(positionX, positionY);
        this.lifes = lifes;
    }
    
    /**
     * method for decreasing lifes of block
     */
    public void decraseLifes(){
        this.lifes -= 1;
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
     * this method read String data and convert them into new DestructibleBlock
     * Used for loading game and server comunication
     * @param stringBlock stringBonus
     * @return new instance of DestructibleBlock
     */
    static public DestructibleBlock readFromString(String stringBlock){
        String[] array = stringBlock.split(",");
        return new DestructibleBlock(
                Integer.parseInt(array[2]), 
                Integer.parseInt(array[0]), 
                Integer.parseInt(array[1]));
    }
}