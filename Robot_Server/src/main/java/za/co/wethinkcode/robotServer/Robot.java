package za.co.wethinkcode.robotServer;

import za.co.wethinkcode.robotServer.World.SquareObstacle;
import za.co.wethinkcode.robotServer.World.World;

import java.util.Arrays;

public class Robot {

    public static final Direction STARTDIRECTION = Direction.NORTH;
    private Direction currentDirection;
    private String robotName;
    private String robotType;
    private World world;
    private Position currentPosition;
    private int shields;
    private int shots;
    private int shotDistance;
    private String status;

    public Robot(World world, String robotName, String robotType){
        this.currentPosition= new Position(0,0);
        this.currentDirection = STARTDIRECTION;
        this.robotName = robotName;
        this.robotType = robotType;
        this.world = world;
        this.shields = 3;
        this.shots = 3;
        this.shotDistance = 3;
        this.status = "normal";
    }
    public void setRobotPosition(int x , int y){
        currentPosition = new Position(x , y);
    }


    public enum UpdateResponse {
        SUCCESS,
        FAILED_OUTSIDE_WORLD,
        FAILED_OBSTRUCTED;
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

    public int getShotDistance(){
        return shotDistance;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public UpdateResponse updatePosition(int steps) {

        int newX = this.currentPosition.getX();
        int newY = this.currentPosition.getY();


        switch (currentDirection) {
            case NORTH:
                if(blockCheckNorth(steps)){
                    return UpdateResponse.FAILED_OBSTRUCTED;
                }
                newY = newY + steps;
                break;
            case SOUTH:
                if(blockCheckSouth(steps)){
                    return UpdateResponse.FAILED_OBSTRUCTED;
                }
                newY = newY - steps;
                break;
            case EAST:
                if(blockCheckEast(steps)){
                    return UpdateResponse.FAILED_OBSTRUCTED;
                }
                newX = newX + steps;
                break;
            case WEST:
                if(blockCheckWest(steps)){
                    return UpdateResponse.FAILED_OBSTRUCTED;
                }
                newX = newX - steps;
                break;
        }
        Position newPosition = new Position(newX, newY);
//        for (int i = 0; i < world.getOBSTACLES().length; i++) {
//            if (Arrays.asList(world.getOBSTACLES()).get(i).blocksPath(this.currentPosition, newPosition)) {
//                return UpdateResponse.FAILED_OBSTRUCTED_OBSTACLE;
//            }
//        }
//        for (int i = 0; i < world.getRobots().size(); i++){
//            if (!world.getRobots().get(i).getRobotName().equals(this.robotName)){
//                if(world.getRobots().get(i).robotBlocksPath(this.currentPosition, newPosition, world.getRobots().get(i))){
//                    return UpdateResponse.FAILED_OBSTRUCTED_ROBOT;
//                }
//            }
//        }
        if (isNewPositionAllowed(newPosition)) {
            this.currentPosition = newPosition;
            return UpdateResponse.SUCCESS;
        }
        return UpdateResponse.FAILED_OUTSIDE_WORLD;
    }

    public void updateDirection(boolean right){
        //This section is to account for if the robot is turning right.
        //If it is facing NORTH and turns to the right is then facing east and so forth
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
            //This section is to account for if the robot is turning left.
            //If it is facing NORTH and turns to the left is then facing west and so forth
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

    public boolean blockCheckNorth(int steps){
        for(int i = 1 ; i < steps + 1 ; i++){
            Position position = new Position(this.currentPosition.getX(), this.currentPosition.getY()+ i);
            for (SquareObstacle obstacle : world.getOBSTACLES()) {
                if (obstacle.blocksPosition(position)) {
                    return true;
                }
            }
            for(Robot robot : world.getRobots()){
                if(!robot.getRobotName().equals(this.robotName)){
                    if(robot.robotBlocksPosition(position, robot)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean blockCheckWest(int steps){
        for(int i = 1 ; i < steps + 1 ; i++){
            Position position = new Position(this.currentPosition.getX() - i, this.currentPosition.getY());
            for (SquareObstacle obstacle : world.getOBSTACLES()) {
                if (obstacle.blocksPosition(position)) {
                    return true;
                }
            }
            for(Robot robot : world.getRobots()){
                if(!robot.getRobotName().equals(this.robotName)){
                    if(robot.robotBlocksPosition(position, robot)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean blockCheckEast(int steps){
        for(int i = 1 ; i < steps + 1 ; i++){
            Position position = new Position(this.currentPosition.getX() + i, this.currentPosition.getY());
            for (SquareObstacle obstacle : world.getOBSTACLES()) {
                if (obstacle.blocksPosition(position)) {
                    return true;
                }
            }
            for(Robot robot : world.getRobots()){
                if(!robot.getRobotName().equals(this.robotName)){
                    if(robot.robotBlocksPosition(position, robot)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean blockCheckSouth(int steps){
        for(int i = 1 ; i < steps + 1 ; i++){
            Position position = new Position(this.currentPosition.getX(), this.currentPosition.getY() - i);
            for (SquareObstacle obstacle : world.getOBSTACLES()) {
                if (obstacle.blocksPosition(position)) {
                    return true;
                }
            }
            for(Robot robot : world.getRobots()){
                if(!robot.getRobotName().equals(this.robotName)){
                    if(robot.robotBlocksPosition(position, robot)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean robotBlocksPosition(Position position, Robot robot) {
        if(robot.getCurrentPosition().getX() == position.getX() && robot.getCurrentPosition().getY() == position.getY()){
                return true;
        }
        return false;
    }

//    public boolean robotBlocksPath(Position a, Position b , Robot robot){
//        if(a.getX() > b.getX()){
//            int path = a.getX() - b.getX();
//            for(int i = 0; i < path; i++){
//                if(this.robotBlocksPosition(new Position(b.getX()+i, b.getY()), robot)){
//                    return true;
//                }
//            }
//        }
//        else if(a.getX() < b.getX()){
//            int path = b.getX() - a.getX();
//            for(int i = 0; i < path; i++){
//                if(this.robotBlocksPosition(new Position(a.getX()+i, a.getY()), robot)){
//                    return true;
//                }
//            }
//        }
//        else if(a.getY() > b.getY()){
//            int path = a.getY() - b.getY();
//            for(int i = 0; i < path; i++){
//                if(this.robotBlocksPosition(new Position(b.getX(), b.getY()+ i), robot)){
//                    return true;
//                }
//            }
//        }
//        else if(a.getY() < b.getY()){
//            int path = b.getY() - a.getY();
//            for(int i = 0; i < path; i++){
//                if(this.robotBlocksPosition(new Position(a.getX(), a.getY()+ i), robot)){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    public boolean isNewPositionAllowed(Position position) {
        if (position.isIn(world.getTOP_LEFT(), world.getBOTTOM_RIGHT())) {
            return true;
        }
        return false;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }
    public void loseShield(){
        this.shields -= 1;
    }

    public void loseShot(){
        this.shots -= 1;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
