package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.model.Block.Block;
import cz.cvut.fel.pjv.model.Block.DestructibleBlock;
import cz.cvut.fel.pjv.model.Bonus.Bonus;
import cz.cvut.fel.pjv.model.Enemy.Dummy;
import cz.cvut.fel.pjv.model.Enemy.Enemy;
import cz.cvut.fel.pjv.model.Enemy.Speedy;
import cz.cvut.fel.pjv.model.Origin.Bomb;
import cz.cvut.fel.pjv.model.Origin.Gate;
import cz.cvut.fel.pjv.model.Origin.Key;
import cz.cvut.fel.pjv.model.Origin.Map;
import cz.cvut.fel.pjv.model.Origin.Player;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Class for creating connection
 * serialize data
 * send data
 */
public class Server implements Runnable {
    private final int PORT_NUMBER;
    private ServerFXMLController controller;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private final static Logger LOGGER
            = Logger.getLogger(Game.class.getName());

    /**
     *
     * @param controller
     * @param PORT_NUMBER
     */
    public Server(ServerFXMLController controller, int PORT_NUMBER) {
        this.controller = controller;
        this.PORT_NUMBER = PORT_NUMBER;
    }

    /**
     * Override from Runnable
     * creating server socket
     * 
     */
    @Override
    public void run() {
        try {
            LOGGER.info("running server");
            serverSocket = new ServerSocket(PORT_NUMBER);
            clientSocket = serverSocket.accept();
            LOGGER.info("client connected");
            controller.runGame();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * method that send messeage to client with serialized coordinates
     * @param coordinates
     * @throws IOException
     */
    public void sendMessage(HashMap coordinates) throws IOException {
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
        printWriter.println(serializeHashMap(coordinates));
        printWriter.flush();
    }
    
    /**
     * method that send initilalize messeage to client with map
     * @param mapOrigin
     * @throws IOException
     */
    public void sendMap(Map mapOrigin) throws IOException{
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
        printWriter.println(serializeMap(mapOrigin));
        printWriter.flush();
    }

    /**
     * method that serialized game coordinates to String
     * We use our own serialization method
     * @param coordinates game coordinates
     * @return string with all game objects
     */
    public String serializeHashMap(HashMap coordinates) {
        String serializedData = "";
        String string = "";

        ArrayList<Block> blocks = (ArrayList<Block>) coordinates.get("Undestructible");
        for (Block block : blocks) {
            string = string + block.getPositionX() + "," + block.getPositionY() + ":";
        }

        StringBuffer sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<DestructibleBlock> dBlocks = (ArrayList<DestructibleBlock>) coordinates.get("Destructible");
        if (dBlocks.size() != 0) {
            for (DestructibleBlock block : dBlocks) {
                string = string + block.getPositionX() + "," + block.getPositionY() + "," + block.getLifes() + ":";
            }
        } else {
            string = "xx";
        }

        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<Bonus> bonuses = (ArrayList<Bonus>) coordinates.get("Bonus");
        if (bonuses.size() != 0) {
            for (Bonus bonus : bonuses) {
                string = string + bonus.getPositionX() + "," + bonus.getPositionY() + "," + bonus.getBonusType() + ":";
            }
        } else {
            string = "xx";
        }

        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<Enemy> enemies = (ArrayList<Enemy>) coordinates.get("Basic Enemy");
        if (enemies.size() != 0) {
            for (Enemy enemy : enemies) {
                string = string + enemy.getPositionX() + "," + enemy.getPositionY() + "," + enemy.getNote() + ":";
            }
        } else {
            string = "xx";
        }

        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<Dummy> dummies = (ArrayList<Dummy>) coordinates.get("Dummy Enemy");
        if (dummies.size() != 0) {
            for (Dummy enemy : dummies) {
                string = string + enemy.getPositionX() + "," + enemy.getPositionY() + "," + enemy.getNote() + ":";
            }
        } else {
            string = "xx";
        }

        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<Speedy> speedies = (ArrayList<Speedy>) coordinates.get("Speedy Enemy");
        if (speedies.size() != 0) {
            for (Speedy enemy : speedies) {
                string = string + enemy.getPositionX() + "," + enemy.getPositionY() + "," + enemy.getNote() + ":";
            }
        } else {
            string = "xx";
        }

        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<Bomb> bombs = (ArrayList<Bomb>) coordinates.get("Bombs");
        if (bombs.size() != 0) {
            for (Bomb bomb : bombs) {
                string = string + bomb.getPositionX() + "," + bomb.getPositionY() + "," + bomb.getPlayerID() + ":";
            }
        } else {
            string = "xx";
        }
        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<Player> players = (ArrayList<Player>) coordinates.get("Player");
        for (Player player : players) {
            string = string + player.getLifes() + "," + player.getNumberOfBombs() + "," + player.getExplosionSize() + ","
                    + player.getPositionX() + "," + player.getPositionY() + "," + player.getSpeed() + "," + player.getPlayerID() + ":";
        }

        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<Gate> gates = (ArrayList<Gate>) coordinates.get("Gate");
        for (Gate gate : gates) {
            string = string + gate.getPositionX() + "," + gate.getPositionY() + ":";
        }

        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";

        string = "";
        ArrayList<Key> keys = (ArrayList<Key>) coordinates.get("Key");
        if (keys.size() != 0) {
            for (Key key : keys) {
                string = string + key.getPositionX() + "," + key.getPositionY() + ":";
            }
        } else {
            string = "xx";
        }

        sb = new StringBuffer(string);
        sb.deleteCharAt(sb.length() - 1);
        serializedData += sb.toString() + ";";
        return serializedData;
    }

    /**
     * method that serialize map to String
     * @param mapParams
     * @return
     */
    public String serializeMap(Map mapParams) {

        String serializedData = mapParams.getSizeX()/45 + "," + mapParams.getSizeY()/45;       
        return serializedData;
    }

    /**
     *
     * @return
     */
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
