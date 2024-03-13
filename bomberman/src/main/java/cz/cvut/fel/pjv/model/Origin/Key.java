package cz.cvut.fel.pjv.model.Origin;

import cz.cvut.fel.pjv.view.Gfx;
import javafx.scene.image.Image;

/**
 * class of geme item Key
 * key necessary for open the gate and winning the game 
 */
public class Key {
    private int positionX;
    private int positionY;
    private Image keyImage = Gfx.Key.getImage();

    /**
     *
     * @param i
     * @param i1
     */
    public Key(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
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
     * @return image of player from GFX
     */
    public Image getKeyImage() {
        return keyImage;
    }
    
    /**
     * this method read String data and convert them into new Key
     * Used for loading game and server comunication
     * @param stringKey StringDataOfKey
     * @return new instance of Key
     */
    static public Key readFromString(String stringKey){
        String[] array = stringKey.split(",");
        return new Key(
                Integer.parseInt(array[0]), 
                Integer.parseInt(array[1]));
    }  
}
