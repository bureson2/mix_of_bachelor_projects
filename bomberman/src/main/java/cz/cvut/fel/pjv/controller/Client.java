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
import cz.cvut.fel.pjv.view.ViewHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import javafx.scene.layout.Pane;

/**
 *
 * Class for client connection
 * sending information to server
 * deserialize data from server
 */
public class Client implements Runnable {

    private final int port;
    private final String host;
    private Socket socket;

    private final static Logger LOGGER
            = Logger.getLogger(MapSetter.class.getName());

    /**
     *
     * @param host
     * @param port
     */
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Overide from Runnable
     * make connection
     * create map
     * deserialize
     * comunicate with class viewHelper
     */
    @Override
    public void run() {
        try {
            LOGGER.info("trying connect to server");
            socket = new Socket(host, port);
            LOGGER.info("connected to server");
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = reader.readLine();
            Map map = Map.readFromString(line);

            Pane pane = new Pane(ViewHelper.mapGenerate(map.getSizeX(), map.getSizeY()));
            ViewHelper viewHelper = new ViewHelper(pane);
            viewHelper.setWindow();

            int c = 0;
            while ((line = reader.readLine()) != null) {
                HashMap<String, ArrayList> coordinates = deserialize(line);
                if (c == 0) {
                    viewHelper.objectGenerate(coordinates);
                    viewHelper.run();
                    c++;
                }

                LOGGER.info(line);
                viewHelper.redrawMap(coordinates);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method that deserialize serialiyed data and create object based their data
     * @param strMain
     * @return
     * @throws IOException
     */
    public HashMap<String, ArrayList> deserialize(String strMain) throws IOException {
        HashMap<String, ArrayList> coordinates = new HashMap<>();
        ArrayList<Block> blockList = new ArrayList<>();
        ArrayList<DestructibleBlock> dBlockList = new ArrayList<>();
        ArrayList<Enemy> enemyList = new ArrayList<>();
        ArrayList<Dummy> enemyDummyList = new ArrayList<>();
        ArrayList<Speedy> enemySpeedyList = new ArrayList<>();
        ArrayList<Player> playersList = new ArrayList<>();
        ArrayList<Bomb> bombList = new ArrayList<>();
        ArrayList<Bonus> bonusList = new ArrayList<>();
        ArrayList<Gate> gateList = new ArrayList<>();
        ArrayList<Key> keyList = new ArrayList<>();

        String[] arrSplitCategory = strMain.split(";");
        String[] units = arrSplitCategory[0].split(":");
        for (String stringBlock : units) {
            blockList.add(Block.readFromString(stringBlock));
        }

        units = arrSplitCategory[1].split(":");
        for (String stringBlock : units) {
            dBlockList.add(DestructibleBlock.readFromString(stringBlock));
        }

        units = arrSplitCategory[2].split(":");
        for (String stringBonus : units) {
            bonusList.add(Bonus.readFromString(stringBonus));
        }

        units = arrSplitCategory[3].split(":");
        for (String stringEnemy : units) {
            enemyList.add(Enemy.readFromString(stringEnemy));
        }

        units = arrSplitCategory[4].split(":");
        for (String stringEnemy : units) {
            enemyDummyList.add(Dummy.readFromString(stringEnemy));
        }

        units = arrSplitCategory[5].split(":");
        for (String stringEnemy : units) {
            enemySpeedyList.add(Speedy.readFromString(stringEnemy));
        }

        units = arrSplitCategory[6].split(":");
        if (!units[0].equals("x")) {
            for (String stringBomb : units) {
                bombList.add(Bomb.readFromString(stringBomb));
            }
        }

        units = arrSplitCategory[7].split(":");
        for (String stringPlayer : units) {
            playersList.add(Player.readFromString(stringPlayer));
        }

        units = arrSplitCategory[8].split(":");
        for (String stringGate : units) {
            gateList.add(Gate.readFromString(stringGate));
        }

        units = arrSplitCategory[9].split(":");
        if (!units[0].equals("x")) {
            for (String stringKey : units) {
                keyList.add(Key.readFromString(stringKey));
            }
        }
        coordinates.put("Destructible", dBlockList);
        coordinates.put("Undestructible", blockList);
        coordinates.put("Bonus", bonusList);
        coordinates.put("Bombs", bombList);
        coordinates.put("Player", playersList);
        coordinates.put("Gate", gateList);
        coordinates.put("Key", keyList);
        coordinates.put("Basic Enemy", enemyList);
        coordinates.put("Dummy Enemy", enemyDummyList);
        coordinates.put("Speedy Enemy", enemySpeedyList);
        return coordinates;
    }
}
