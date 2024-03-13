package cz.cvut.fel.pjv.model.Bonus;

import cz.cvut.fel.pjv.model.Origin.Player;
import java.util.Random;

/**
 * Fake class of Bonus
 * for delete dependency of graphic GFX
 */
public class BonusForTest {
private int positionX;
    private int positionY;
    private String bonusType; 

    /**
     *
     * @param positionX
     * @param positionY
     */
    public BonusForTest(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.bonusType = randomChooseTypeOfBonus();
        System.out.println(bonusType);
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
     * @return
     */
    public String getBonusType() {
        return bonusType;
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
     * @param x
     * @param lower
     * @param upper
     * @return
     */
    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
    
    /**
     *
     * @return
     */
    public String randomChooseTypeOfBonus(){
        Random rnd = new Random();
        int pickedNumber = rnd.nextInt(100) + 1;
        if(isBetween(pickedNumber,1,2)){return "lifeAdd2";
        } else if(isBetween(pickedNumber,3,20)){return "lifeAdd1";
        } else if(isBetween(pickedNumber,21,40)){return "bombsAdd2";
        } else if(isBetween(pickedNumber,41,49)){return "bombsSet10";
        } else if(isBetween(pickedNumber,50,60)){return "speedSet2";
        }else if(isBetween(pickedNumber,61,74)){return "explosionAdd3";
        }else if(isBetween(pickedNumber,75,79)){return "bombsSet1";
        }else if(isBetween(pickedNumber,80,84)){return "explosionSet10";
        }else if(isBetween(pickedNumber,85,89)){return "explosionSet2";
        }else if(isBetween(pickedNumber,90,94)){return "speedSet1";
        }else if(isBetween(pickedNumber,95,99)){return "lifeMinus1";}       
        return "instantDeath";   
    }
    
    /**
     *
     * @param player
     * @return
     */
    public Player setBonusToPlayer(Player player){
        int playerLifes = player.getLifes();
        int playerExplosionSize = player.getExplosionSize();
        int playerBombs = player.getNumberOfBombs();
        switch(bonusType){
            case "lifeAdd2":
                playerLifes += 2;
                player.setLifes(playerLifes);
                break;
            case "lifeAdd1":
                playerLifes = player.getLifes() + 1;
                player.setLifes(playerLifes);
                break;
            case "speedSet2":
                player.setSpeed(2);
                break;
            case "explosionAdd3":
                playerExplosionSize += 3;
                player.setExplosionSize(playerExplosionSize);
                break;
            case "explosionSet10":
                player.setExplosionSize(10);
                break;
            case "explosionSet2":
                player.setExplosionSize(2);
                break;
            case "speedSet1":
                player.setSpeed(1);
                break;
            case "lifeMinus1":
                playerLifes -= 1;
                player.setLifes(playerLifes);
                break;
            case "instantDeath":
                player.setLifes(1);
                break;
            case "bombsAdd2":
                playerBombs += 2;
                player.setNumberOfBombs(playerBombs);
                break;
            case "bombsSet10":
                player.setNumberOfBombs(10);
                break;
            case "bombsSet1":
                player.setNumberOfBombs(1);
                break;          
        }
        return player;
    }
}




