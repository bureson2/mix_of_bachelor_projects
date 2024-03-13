package cz.cvut.fel.pjv.view;

import java.io.File;
import javafx.scene.image.Image;

/**
 * enum with images 
 * 
 */
public enum Gfx {

    /**
     *
     */
    Grass("src/main/resources/img/grass.png"),

    /**
     *
     */
    UndestructibleBlock("src/main/resources/img/solid_block.png"),

    /**
     *
     */
    DestructibleBlock("src/main/resources/img/brick.png"),

    /**
     *
     */
    DestructibleBlock1("src/main/resources/img/brick1.png"),

    /**
     *
     */
    Bomb("src/main/resources/img/Bomb.gif"),

    /**
     *
     */
    Player("src/main/resources/img/player.png"),

    /**
     *
     */
    Player2("src/main/resources/img/player2.png"),

    /**
     *
     */
    PlayerUP2("src/main/resources/img/playerUP2.png"),

    /**
     *
     */
    PlayerUP3("src/main/resources/img/playerUP3.png"),

    /**
     *
     */
    Enemy("src/main/resources/img/enemyBasic.gif"),

    /**
     *
     */
    Speedy("src/main/resources/img/speedy.gif"),

    /**
     *
     */
    EnemyDeath("src/main/resources/img/enemyBasicDeath.png"),

    /**
     *
     */
    SpeedyDeath("src/main/resources/img/speedyDeath.png"),

    /**
     *
     */
    Dummy("src/main/resources/img/Dummy.gif"),

    /**
     *
     */
    DummyDeath("src/main/resources/img/DummyDeath.png"),

    /**
     *
     */
    Explosion("src/main/resources/img/explosion.png"),

    /**
     *
     */
    Explosion1("src/main/resources/img/explosion1.png"),

    /**
     *
     */
    Explosion2("src/main/resources/img/explosion2.png"),

    /**
     *
     */
    Explosion3("src/main/resources/img/explosion3.png"),

    /**
     *
     */
    Explosion4("src/main/resources/img/explosion4.png"),

    /**
     *
     */
    Explosion5("src/main/resources/img/explosion5.png"),

    /**
     *
     */
    Explosion6("src/main/resources/img/explosion6.png"),

    /**
     *
     */
    RandomBonus("src/main/resources/img/randomBonus.png"),

    /**
     *
     */
    Gate("src/main/resources/img/Gate.png"),

    /**
     *
     */
    OpenGate("src/main/resources/img/Gate.gif"),

    /**
     *
     */
    Key("src/main/resources/img/Key.gif");
    String gfxName;
    
    private Gfx(String gfxName) {
        this.gfxName = gfxName;
    }

    /**
     * get blown image
     * @return
     */
    public Image getImage() {
        Image image = loadImage(gfxName);
        return image;
    }

    /**
     * load image from resource
     * @return image
     */
    private Image loadImage(String imgFileName) {
        File file = new File(imgFileName);
        return new Image(file.toURI().toString());
    }
}
