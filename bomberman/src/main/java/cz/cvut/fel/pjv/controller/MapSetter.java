package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.model.Block.Block;
import cz.cvut.fel.pjv.model.Block.DestructibleBlock;
import cz.cvut.fel.pjv.model.Enemy.Dummy;
import cz.cvut.fel.pjv.model.Enemy.Enemy;
import cz.cvut.fel.pjv.model.Bonus.Bonus;
import cz.cvut.fel.pjv.model.Enemy.Speedy;
import cz.cvut.fel.pjv.model.Origin.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Method for setting game after their starts
 * sets player on start position, set enemies, set blocks by configuration map
 * contains method and parameters for restarting plazers
 */
public class MapSetter {
    
    private Player player1 = new Player(3, 3, 3, 0, 0, 1, 1);
    
    private File mapFile; 
    private File mapFile1 = new File("hackMap.txt");
    private File mapFile2 = new File("Mapa.txt");
    private File mapFile3 = new File("MapaEasterEgg.txt");     
    private File mapFile4 = new File("MapaTest.txt");
    private File mapFile5 = new File("NewTestMap.txt"); 
    private File mapParamsFile = new File("Config-MapParameters.txt");
    private File chosenMap = new File("mapInfo.txt");
    
    private ArrayList<String> inputMapFileList = new ArrayList<>(); 
    private ArrayList<String> inputMapSizeParams = new ArrayList<>(); 
    private ArrayList<String> objectToSortList = new ArrayList<>();    
    private ArrayList<Block> undestructibleBlocks = new ArrayList<>();
    private ArrayList<DestructibleBlock> destructibleBlocks = new ArrayList<>();
    private ArrayList<Player> playersList = new ArrayList<Player>();
    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private ArrayList<Dummy> enemyDummyList = new ArrayList<>();
    private ArrayList<Speedy> enemySpeedyList = new ArrayList<>();
    private ArrayList<int[]> startPositions = new ArrayList<>();
    private ArrayList<int[]> freePositionList = new ArrayList<>();
    private HashMap<String, ArrayList> coordinates = new HashMap<>();
    
    private Map map;
    private final static Logger LOGGER =
            Logger.getLogger(MapSetter.class.getName());
    
    final private int BLOCK_SIZE = 45;

    /**
     * constructor, used map set from options
     */
    public MapSetter(){
        try {
            setChoosenMap();
            this.map = new Map(getMapParameters());
        } catch (IOException ex) {
            Logger.getLogger(MapSetter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * constructor for load game
     * @param coordinates all game coordinates saved from last saved game
     */
    public MapSetter(HashMap<String, ArrayList> coordinates){
        try {
            setChoosenMap();
            this.map = new Map(getMapParameters());
        } catch (IOException ex) {
            Logger.getLogger(MapSetter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * method that choose right map fille after option settings
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void setChoosenMap() throws FileNotFoundException, IOException{             
        FileReader fr = new FileReader(chosenMap);
        BufferedReader br = new BufferedReader(fr);
        String firstLine = br.readLine();
          
        int mapId = Integer.parseInt(firstLine);
        if(mapId == 1){    mapFile = mapFile1;
        } else if(mapId == 2){  mapFile = mapFile2;
        } else if(mapId == 3){  mapFile = mapFile3;
        } else if(mapId == 4){  mapFile = mapFile4;
        } else { mapFile = mapFile5;}
        LOGGER.info(mapFile.toString() + " was picked");
    }
    
    /**
     * method that return parameters about number of bonuses and enemies
     * it is controlled by configuration file
     * @return
     */
    public HashMap getMapParameters() {
        inputMapFileList = scanMap(mapParamsFile, inputMapFileList);
        HashMap<String, Integer> mapParams = new HashMap<>();
        mapParams.put("Bonuses", Integer.parseInt(inputMapFileList.get(0)));
        mapParams.put("Basic Enemy", Integer.parseInt(inputMapFileList.get(1)));
        mapParams.put("Dummy Enemy", Integer.parseInt(inputMapFileList.get(2)));
        mapParams.put("Speedy Enemy", Integer.parseInt(inputMapFileList.get(3)));
        setMapSize(mapParams);
        System.out.println(mapParams);
        return mapParams;
    }
    
    /**
     *  method that find information about map size and set them to HashMap which is used for generating map
     * @param mapParams
     */
    public void setMapSize(HashMap mapParams) {
        inputMapSizeParams = scanMap(mapFile, inputMapSizeParams);
        mapParams.put("Lenght", inputMapSizeParams.get(0).length());
        mapParams.put("Height", inputMapSizeParams.size());
    }
    
    /**
     * Method that set new monsters by their numbers into game map
     * it set them their game coordinates
     * it is possible to set monster on player corner start position
     */
    public void setMonsters() { 
        for (int i = 0; i < map.getNumberOfEnemies(); i++) {
            int randomEnemyStartPosition = new Random().nextInt(freePositionList.size());
            enemyList.add(new Enemy(freePositionList.get(randomEnemyStartPosition)[0], freePositionList.get(randomEnemyStartPosition)[1], "Basic"));
            freePositionList.remove(randomEnemyStartPosition);
            coordinates.put("Basic Enemy", enemyList);
        }
        for (int i = 0; i < map.getNumberOfDummyEnemies(); i++) {
            int randomEnemyStartPosition = new Random().nextInt(freePositionList.size());
            enemyDummyList.add(new Dummy(freePositionList.get(randomEnemyStartPosition)[0], freePositionList.get(randomEnemyStartPosition)[1], "Dummy"));
            freePositionList.remove(randomEnemyStartPosition);
            coordinates.put("Dummy Enemy", enemyDummyList);
        }
        for (int i = 0; i < map.getNumberOfSpeedyEnemies(); i++) {
            int randomEnemyStartPosition = new Random().nextInt(freePositionList.size());
            enemySpeedyList.add(new Speedy(freePositionList.get(randomEnemyStartPosition)[0], freePositionList.get(randomEnemyStartPosition)[1], "Speedy"));
            freePositionList.remove(randomEnemyStartPosition);
            coordinates.put("Speedy Enemy", enemySpeedyList);
        }
        LOGGER.info("All enemies were set");
    }

    /**
     * Method that set new Blocks into game coordinates and into game
     * method used coordinates get from configuration map file
     * call setMonsters, setPlayerOnStartPosition and setGateKeyBonuses
     * @return HashMap of all objects in map
     */
    public HashMap getObjectCoordinates() {
        objectToSortList = scanMap(mapFile, objectToSortList);
        int row = 0;
        for (String line : objectToSortList) {
            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '#') {
                    undestructibleBlocks.add(new Block(i * BLOCK_SIZE, (row) * BLOCK_SIZE));
                }
                else if(chars[i] == '1' || chars[i] == '2'){
                        destructibleBlocks.add(new DestructibleBlock(Character.getNumericValue(chars[i]), i * BLOCK_SIZE, (row) * BLOCK_SIZE));
                }
                else if(chars[i] == 'S'){
                    startPositions.add(new int[]{i * BLOCK_SIZE, (row) * BLOCK_SIZE});
                }
                else {
                    freePositionList.add(new int[]{i * BLOCK_SIZE, (row) * BLOCK_SIZE});
                }
            }
            row++;
        }
        coordinates.put("Destructible", destructibleBlocks);
        coordinates.put("Undestructible", undestructibleBlocks);
        LOGGER.info("All blocks were set");
        setPlayerOnStartPosition();
        setMonsters();
        setGateAndKeyAndBonuses();
        return coordinates;
    }
    
    /**
     * method that set new bonuses, key and gate into game coordinates and into game
     * bonuses van be in one block, it is for more interesting and random combinations
     */
    public void setGateAndKeyAndBonuses (){
        int randomGatePosition = new Random().nextInt(destructibleBlocks.size());
        int randomKeyPosition = new Random().nextInt(destructibleBlocks.size());
        while(randomGatePosition == randomKeyPosition){
            randomKeyPosition = new Random().nextInt(destructibleBlocks.size());
        }
        Block rnd1 = destructibleBlocks.get(randomGatePosition);
        Block rnd2 = destructibleBlocks.get(randomKeyPosition);
        
        ArrayList<Bonus> bonuses = new ArrayList<>();
        System.out.println(map.getNumberOfbonuses());
        for (int i = 0; i < map.getNumberOfbonuses(); i++) {
            int randomBonusPosition = new Random().nextInt(destructibleBlocks.size());
            while(randomBonusPosition == randomGatePosition || randomBonusPosition == randomKeyPosition){
                randomBonusPosition = new Random().nextInt(destructibleBlocks.size());
            }            
            Block rnd3 = destructibleBlocks.get(randomBonusPosition);
            bonuses.add(new Bonus(rnd3.getPositionX(), rnd3.getPositionY()));
        }
        
        ArrayList<Gate> exitGate =  new ArrayList(Arrays.asList(new Gate(rnd1.getPositionX(), rnd1.getPositionY())));
        ArrayList<Key> key = new ArrayList(Arrays.asList(new Key(rnd2.getPositionX(), rnd2.getPositionY())));
        
        coordinates.put("Gate", exitGate);
        coordinates.put("Key", key);
        coordinates.put("Bonus", bonuses);
        coordinates.put("Bombs", new ArrayList<Bomb>());
        
        LOGGER.info("Gate is set to: " + rnd1.getPositionX() + ", " + rnd1.getPositionY());
        LOGGER.info("Key is set to: " + rnd1.getPositionX() + ", " + rnd1.getPositionY());
    }
    
    /**
     * method that set player on some corner position randomly
     */
    public void setPlayerOnStartPosition() { 
        int randomStartPosition = new Random().nextInt(startPositions.size());
        player1.setPositionX(startPositions.get(randomStartPosition)[0]);
        player1.setPositionY(startPositions.get(randomStartPosition)[1]);
        playersList.add(player1);
        coordinates.put("Player", playersList);
        LOGGER.info("Player/s are on start positions");
    }
    
    /**
     *method called from game after some player needs respawn
     * @param player who needs respawn
     * @return
     */
    public Player setPlayerOnStartPosition(Player player) { 
        int randomStartPosition = new Random().nextInt(startPositions.size());
        player.setPositionX(startPositions.get(randomStartPosition)[0]);
        player.setPositionY(startPositions.get(randomStartPosition)[1]);
        return player;
    } 
    
    /**
     * method that scan some file and get values from it
     * @param file to scanning
     * @param list list for adding information
     * @return
     */
    public ArrayList scanMap(File file, ArrayList list) {
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @return
     */
    public ArrayList<int[]> getStartPositions() {
        return startPositions;
    }

    /**
     *
     * @return
     */
    public Map getMap() {
        return map;
    }
    
    /**
     * method that deserialized game from String save data
     * used for loading and server game
     * @param strMain data base of coordinates and parameters
     * @return HashMap with renew objects
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