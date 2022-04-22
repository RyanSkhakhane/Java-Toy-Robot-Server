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


//    public UpdateResponse updatePosition(int nrSteps) {
//
//        int newX = this.position.getX();
//        int newY = this.position.getY();
//
//        if (za.co.wethinkcode.toyrobot.world.IWorld.Direction.UP.equals(this.currentDirection)) {
//            newY = newY + nrSteps;
//        } else if (za.co.wethinkcode.toyrobot.world.IWorld.Direction.RIGHT.equals(this.currentDirection)) {
//            newX = newX + nrSteps;
//        } else if (za.co.wethinkcode.toyrobot.world.IWorld.Direction.DOWN.equals(this.currentDirection)) {
//            newY = newY - nrSteps;
//        } else if (za.co.wethinkcode.toyrobot.world.IWorld.Direction.LEFT.equals(this.currentDirection)) {
//            newX = newX - nrSteps;
//        }
//
//        Position newPosition = new Position(newX, newY);
//        for(int i=0; i< obstacles.size(); i++){
//            if(obstacles.get(i).blocksPath(this.position, newPosition)){
//                return UpdateResponse.FAILED_OBSTRUCTED;
//            }
//        }
//        if (isNewPositionAllowed(newPosition)) {
//            this.position = newPosition;
//            return UpdateResponse.SUCCESS;
//        }
//        return UpdateResponse.FAILED_OUTSIDE_WORLD;
//    }

    enum UpdateResponse {
        SUCCESS, //position was updated successfully
        FAILED_OUTSIDE_WORLD, //robot will go outside world limits if allowed, so it failed to update the position
        FAILED_OBSTRUCTED, //robot obstructed by at least one obstacle, thus cannot proceed.
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
}
