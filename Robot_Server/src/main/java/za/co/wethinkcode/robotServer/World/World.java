package za.co.wethinkcode.robotServer.World;

import za.co.wethinkcode.robotServer.Direction;
import za.co.wethinkcode.robotServer.Position;
import za.co.wethinkcode.robotServer.Robot;

import java.util.ArrayList;
import java.util.List;

public class World {

    protected final Position TOP_LEFT = new Position(-5, 5);
    protected final Position BOTTOM_RIGHT = new Position(5, -5);
    public static final Position CENTRE = new Position(0, 0);
    protected ArrayList<Obstacle> OBSTACLES;
    protected ArrayList<Robot> robots;
    

    public World (){this.OBSTACLES = makeObstacles();
    }
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
    public void showObstacles() {
        System.out.println("There are some obstacles");
        for (int i = 0; i <= OBSTACLES.size() - 1; i++) {
            System.out.println("- At position "+ OBSTACLES.get(i).getBottomLeftX()+","+OBSTACLES.get(i).getBottomLeftY()+"" +
                    " (to "+(OBSTACLES.get(i).getBottomLeftX()+ 4)+","+(OBSTACLES.get(i).getBottomLeftY()+ 4)+")");

        }
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
