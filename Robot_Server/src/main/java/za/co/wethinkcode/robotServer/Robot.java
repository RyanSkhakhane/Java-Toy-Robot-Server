package za.co.wethinkcode.robotServer;

import za.co.wethinkcode.robotServer.World.World;

public class Robot {

    protected final Position TOP_LEFT = new Position(-5, 5);
    protected final Position BOTTOM_RIGHT = new Position(5, -5);
    public static final Direction STARTDIRECTION = Direction.NORTH;
    private Direction currentDirection;
    private String robotName;
    private String robotType;
    private World world;
    private Position startPosition;

    public Robot(World world, String robotName, String robotType){
        this.startPosition = new Position(0,0);
        this.currentDirection = STARTDIRECTION;
        this.robotName = robotName;
        this.robotType = robotType;
        this.world = world;
    }

    public String getRobotName(){
        return robotName;
    }

    public String getRobotType(){
        return robotType;
    }

    public Direction getCurrentDirection(){
        return currentDirection;
    }
}
