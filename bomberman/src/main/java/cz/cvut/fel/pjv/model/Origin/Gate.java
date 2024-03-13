package cz.cvut.fel.pjv.model.Origin;

import cz.cvut.fel.pjv.view.Gfx;
import javafx.scene.image.Image;

/**
 * Class of Gate
 * Finding this gate allows win the game
 */
public class Gate {
    private boolean isOpen =false;
    private int positionX;
    private int positionY;
    private Image gateImage = Gfx.Gate.getImage();
    private Image openGateImage = Gfx.OpenGate.getImage();
    
    /**
     *
     * @param i
     * @param i1
     */
    public Gate(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     *
     * @param isOpen parameter which is activated when someone pick key
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
     * @return boolean value of gate state
     * if someone pick key the gate is open
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

    /**
     *
     * @return image from GFX
     */
    public Image getGateImage() {
        return gateImage;
    }

    /**
     *
     * @return image from GFX
     */
    public Image getOpenGateImage() {
        return openGateImage;
    }
    
    /**
     * this method read String data and convert them into new Gate
     * Used for loading game and server comunication
     * @param stringGate StringDataOfGate
     * @return new instance of Gate
     */
    static public Gate readFromString(String stringGate){
        String[] array = stringGate.split(",");
        return new Gate(
                Integer.parseInt(array[0]), 
                Integer.parseInt(array[1]));
    }
}
