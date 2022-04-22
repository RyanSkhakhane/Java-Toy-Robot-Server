package za.co.wethinkcode.robotServer.World;

import com.google.gson.Gson;
import za.co.wethinkcode.robotServer.ConfigFileJson;
import za.co.wethinkcode.robotServer.Direction;
import za.co.wethinkcode.robotServer.Position;
import za.co.wethinkcode.robotServer.Robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class World {

    protected final Position TOP_LEFT = new Position(-5, 5);
    protected final Position BOTTOM_RIGHT = new Position(5, -5);
    public static final Position CENTRE = new Position(0, 0);
    protected SquareObstacle[] OBSTACLES;
    protected ArrayList<Robot> robots;


    public World(ArrayList<Robot> robotArrayList) throws FileNotFoundException {
        this.OBSTACLES = readObstacles();
        this.robots = robotArrayList;
    }


//    public SquareObstacle[] makeObstacles(){
//        SquareObstacle[] obstaclesList = {new SquareObstacle(0,0)};
//        return obstaclesList;
//    }
    public SquareObstacle[] readObstacles() throws FileNotFoundException {
        Gson gson = new Gson();
        try {
            File file = new File("Config.json");
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
