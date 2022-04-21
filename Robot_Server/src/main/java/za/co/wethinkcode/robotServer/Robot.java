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
    private Position currentPosition;
    private int shields;
    private int shots;
    private String status;

    public Robot(World world, String robotName, String robotType){
        this.currentPosition= new Position(0,0);
        this.currentDirection = STARTDIRECTION;
        this.robotName = robotName;
        this.robotType = robotType;
        this.world = world;
        this.shields = 0;
        this.shots = 0;
        this.status = "normal";
    }
    public void setRobotPosition(int x , int y){
        currentPosition = new Position(x , y);
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

    public String getRobotState(){
        return "Position [" + currentPosition.getX() + "," + currentPosition.getY() + "] \n" +
                "Direction [" + currentDirection + "]";
    }

    public int getShields() {
        return shields;
    }

    public int getShots() {
        return shots;
    }

    public String getStatus() {
        return status;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void updatePosition(int steps) {
        switch (currentDirection) {
            case NORTH:
                currentPosition = new Position(currentPosition.getX(), currentPosition.getY() + steps);
                break;
            case SOUTH:
                currentPosition = new Position(currentPosition.getX(), currentPosition.getY() - steps);
                break;
            case EAST:
                currentPosition = new Position(currentPosition.getX() + steps, currentPosition.getY());
                break;
            case WEST:
                currentPosition = new Position(currentPosition.getX() - steps, currentPosition.getY());
                break;
        }

    }

    public void updateDirection(boolean right){
        if(right){
            switch (currentDirection){
                case NORTH:
                    currentDirection = Direction.EAST;
                    break;
                case EAST:
                    currentDirection = Direction.SOUTH;
                    break;
                case SOUTH:
                    currentDirection = Direction.WEST;
                    break;
                case WEST:
                    currentDirection = Direction.NORTH;
                    break;

            }
        }else {
            switch (currentDirection){
                case NORTH:
                    currentDirection = Direction.WEST;
                    break;
                case WEST:
                    currentDirection = Direction.SOUTH;
                    break;
                case SOUTH:
                    currentDirection = Direction.EAST;
                    break;
                case EAST:
                    currentDirection = Direction.NORTH;
                    break;
            }
        }
    }


}
