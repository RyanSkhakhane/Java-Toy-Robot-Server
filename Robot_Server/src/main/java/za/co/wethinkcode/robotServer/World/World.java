package za.co.wethinkcode.robotServer.World;

import com.google.gson.Gson;
import za.co.wethinkcode.robotServer.ConfigFileJson;
import za.co.wethinkcode.robotServer.Position;
import za.co.wethinkcode.robotServer.Robot;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class World {

    protected final Position TOP_LEFT = new Position(-(getEdge(true)), getEdge(false));
    protected final Position BOTTOM_RIGHT = new Position(getEdge(true), -(getEdge(false)));
    protected final int VISIBILITY = getVISIBILITY();
    public static final Position CENTRE = new Position(0, 0);
    protected SquareObstacle[] OBSTACLES;
    protected ArrayList<Robot> robots;


    public World(ArrayList<Robot> robotArrayList) throws FileNotFoundException {
        this.OBSTACLES = readObstacles();
        this.robots = robotArrayList;
    }

    public SquareObstacle[] readObstacles() throws FileNotFoundException {
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("Config.json");
            ConfigFileJson json = gson.fromJson(fileReader, ConfigFileJson.class);
            return json.getObstacles();
        } catch (FileNotFoundException e) {
            System.out.println("No config file present");
        }
        SquareObstacle[] obstaclesList = {new SquareObstacle(0, 0)};
        return obstaclesList;
    }

    public void showObstacles() {
        System.out.println("There are some obstacles");
        for (int i = 0; i <= OBSTACLES.length - 1; i++) {
            System.out.println("- At position "+ Arrays.asList(OBSTACLES).get(i).getBottomLeftX()+","+Arrays.asList(OBSTACLES).get(i).getBottomLeftY()+"" +
                    " (to "+(Arrays.asList(OBSTACLES).get(i).getBottomLeftX()+ 3)+","+(Arrays.asList(OBSTACLES).get(i).getBottomLeftY()+ 3)+")");

        }
    }

    public int getEdge(boolean xCheck){
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("Config.json");
            ConfigFileJson json = gson.fromJson(fileReader, ConfigFileJson.class);
            ConfigFileJson.GridJson grid = json.getGridSize();
            if (xCheck){
                return grid.getX();
            }else{
                return grid.getY();
            }

        } catch (FileNotFoundException e) {
            System.out.println("No config file present");
        }
        return 0;
    }

    public int getVISIBILITY() {
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("Config.json");
            ConfigFileJson json = gson.fromJson(fileReader, ConfigFileJson.class);
            return json.getVisibility();
        } catch (FileNotFoundException e) {
            System.out.println("No config file present");
        }
        return 5;
    }



    public SquareObstacle[] getOBSTACLES(){
        return OBSTACLES;
    }

    public Position getTOP_LEFT() {
        return TOP_LEFT;
    }

    public Position getBOTTOM_RIGHT() {
        return BOTTOM_RIGHT;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }
}
