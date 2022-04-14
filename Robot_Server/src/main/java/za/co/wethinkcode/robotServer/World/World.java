package za.co.wethinkcode.robotServer.World;

import za.co.wethinkcode.robotServer.Direction;
import za.co.wethinkcode.robotServer.Position;
import za.co.wethinkcode.robotServer.Robot;

import java.util.ArrayList;

public class World {

    protected final Position TOP_LEFT = new Position(-5, 5);
    protected final Position BOTTOM_RIGHT = new Position(5, -5);
    public static final Position CENTRE = new Position(0, 0);
    protected ArrayList<Obstacle> OBSTACLES;
    protected ArrayList<Robot> robots;
//    public static final Direction STARTDIRECTION = Direction.NORTH;

    public World(ArrayList<Robot> robotArrayList){
        this.OBSTACLES = makeObstacles();
        this.robots = robotArrayList;
    }


    public ArrayList<Obstacle> makeObstacles(){
        ArrayList<Obstacle> obstacleList = new ArrayList<>();
        obstacleList.add(new SquareObstacle(-1,2));
        return obstacleList;
    }

    public ArrayList<Obstacle> getOBSTACLES(){
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
