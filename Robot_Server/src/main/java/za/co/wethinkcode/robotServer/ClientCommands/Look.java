package za.co.wethinkcode.robotServer.ClientCommands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import za.co.wethinkcode.robotServer.Direction;
import za.co.wethinkcode.robotServer.Position;
import za.co.wethinkcode.robotServer.Robot.Robot;
import za.co.wethinkcode.robotServer.World.SquareObstacle;
import za.co.wethinkcode.robotServer.World.World;

import java.util.ArrayList;

public class Look extends ClientCommands{
    ArrayList<ObjectJson> objects = new ArrayList<>();
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public Look(String name) {
        super("look",name);
    }

    @Override
    public String execute(World world, String[] arguments) {
        StateResponseJSon state;
        for(Robot robot : world.getRobots()){
            if(robot.getRobotName().equals(getArgument())){
                int[] position = {robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()};
                state = new StateResponseJSon(position, robot.getCurrentDirection().toString(),
                        robot.getShields(),robot.getShots(), "normal");
                switch (robot.getCurrentDirection()){
                    case NORTH:
                        startNorth(world.VISIBILITY, robot, world);
                        break;
                    case EAST:
                        startEast(world.VISIBILITY, robot, world);
                        break;
                    case SOUTH:
                        startSouth(world.VISIBILITY, robot, world);
                        break;
                    case WEST:
                        startWest(world.VISIBILITY, robot, world);
                        break;
                }
            ObjectJson[] objectJson = objects.toArray(new ObjectJson[0]);
            DataJson data = new DataJson(objectJson);
            LookResponseJson lookResponseJson = new LookResponseJson("ok", data, state);
            objects.clear();
            return gson.toJson(lookResponseJson);
            }
        }
        return null;
    }

    public void lookCommand(int visibility, Robot myRobot, World world , int direction) {
        int newX = myRobot.getCurrentPosition().getX();
        int newY = myRobot.getCurrentPosition().getY();

        switch (myRobot.getCurrentDirection()) {
            case NORTH:
                if(SightCheckNorth(visibility,myRobot,world,direction)){
                    return;
                }
                newY = newY + visibility;
                break;
            case SOUTH:
                if(SightCheckSouth(visibility,myRobot,world,direction)){
                    return;
                }
                newY = newY - visibility;
                break;
            case EAST:
                if(SightCheckEast(visibility,myRobot,world,direction)){
                    return;
                }
                newX = newX + visibility;
                break;
            case WEST:
                if(SightCheckWest(visibility,myRobot,world,direction)){
                    return;
                }
                newX = newX - visibility;
                break;
        }
        Position newPosition = new Position(newX, newY);
//
        if (isNewPositionAllowed(newPosition, world)) {
        }
        else{
            edge(myRobot, world, direction);

        }
    }

    public boolean isNewPositionAllowed(Position position , World world) {
        if (position.isIn(world.getTOP_LEFT(), world.getBOTTOM_RIGHT())) {
            return true;
        }
        return false;
    }



    private String directionCheck(int direction){
        switch (direction){
            case 1 :
                return "NORTH";
            case 2 :
                return "EAST";
            case 3 :
                return "SOUTH";
            case 4 :
                return "WEST";
        }
        return "none";
    }

    private void startNorth(int visibility, Robot myRobot, World world){
        int direction = 1;
        lookCommand(visibility, myRobot, world, direction);
        direction += 1;
        myRobot.setCurrentDirection(Direction.EAST);
        lookCommand(visibility, myRobot, world, direction);
        direction += 1;
        myRobot.setCurrentDirection(Direction.SOUTH);
        lookCommand(visibility, myRobot, world, direction);
        direction += 1;
        myRobot.setCurrentDirection(Direction.WEST);
        lookCommand(visibility, myRobot, world, direction);
        myRobot.setCurrentDirection(Direction.NORTH);
    }

    private void startEast(int visibility, Robot myRobot, World world){
        int direction = 1;
        lookCommand(visibility, myRobot, world, direction);
        direction += 1;
        myRobot.setCurrentDirection(Direction.SOUTH);
        lookCommand(visibility, myRobot, world, direction);
        direction += 1;
        myRobot.setCurrentDirection(Direction.WEST);
        lookCommand(visibility, myRobot, world, direction);
        direction += 1;
        myRobot.setCurrentDirection(Direction.NORTH);
        lookCommand(visibility, myRobot, world, direction);
        myRobot.setCurrentDirection(Direction.EAST);
    }

    private void startSouth(int visibility, Robot myRobot, World world){
        int direction = 1;
        lookCommand(visibility, myRobot, world, direction);
        direction += 1;
        myRobot.setCurrentDirection(Direction.WEST);
        lookCommand(visibility, myRobot, world, direction);
        direction += 1;
        myRobot.setCurrentDirection(Direction.NORTH);
        lookCommand(visibility, myRobot, world, direction);
        direction += 1;
        myRobot.setCurrentDirection(Direction.EAST);
        lookCommand(visibility, myRobot, world, direction);
        myRobot.setCurrentDirection(Direction.SOUTH);
    }

    private void startWest(int visibility, Robot myRobot, World world){
        int direction = 1;
        lookCommand(visibility, myRobot, world, direction);
        direction += 1;
        myRobot.setCurrentDirection(Direction.NORTH);
        lookCommand(visibility, myRobot, world, direction);
        direction += 1;
        myRobot.setCurrentDirection(Direction.EAST);
        lookCommand(visibility, myRobot, world, direction);
        direction += 1;
        myRobot.setCurrentDirection(Direction.SOUTH);
        lookCommand(visibility, myRobot, world, direction);
        myRobot.setCurrentDirection(Direction.WEST);
    }

    public boolean SightCheckNorth(int visibility, Robot myRobot, World world, int direction){
        Position position;
        for(int i = 1 ; i < Math.abs(visibility) + 1 ; i++){
            position = new Position(myRobot.getCurrentPosition().getX(), myRobot.getCurrentPosition().getY()+ i);
            for (SquareObstacle obstacle : world.getOBSTACLES()) {
                if (obstacle.blocksPosition(position)) {
                    ObjectJson object = new ObjectJson(directionCheck(direction), "OBSTACLE", i);
                    objects.add(object);
                    return true;
                }
            }
            for(Robot robot : world.getRobots()){
                if(!robot.getRobotName().equals(myRobot.getRobotName())){
                    if(robot.robotBlocksPosition(position, robot)){
                        ObjectJson object = new ObjectJson(directionCheck(direction), "ROBOT", i);
                        objects.add(object);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean SightCheckWest(int visibility, Robot myRobot, World world, int direction){
        Position position;
        for(int i = 1 ; i < Math.abs(visibility) + 1 ; i++){
            position = new Position(myRobot.getCurrentPosition().getX()- i, myRobot.getCurrentPosition().getY());
            for (SquareObstacle obstacle : world.getOBSTACLES()) {
                if (obstacle.blocksPosition(position)) {
                    ObjectJson object = new ObjectJson(directionCheck(direction), "OBSTACLE", i);
                    objects.add(object);
                    return true;
                }
            }
            for(Robot robot : world.getRobots()){
                if(!robot.getRobotName().equals(myRobot.getRobotName())){
                    if(robot.robotBlocksPosition(position, robot)){
                        ObjectJson object = new ObjectJson(directionCheck(direction), "ROBOT", i);
                        objects.add(object);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean SightCheckEast(int visibility, Robot myRobot, World world, int direction){
        Position position;
        for(int i = 1 ; i < Math.abs(visibility) + 1 ; i++){
            position = new Position(myRobot.getCurrentPosition().getX()+ i,myRobot.getCurrentPosition().getY());
            for (SquareObstacle obstacle : world.getOBSTACLES()) {
                if (obstacle.blocksPosition(position)) {
                    ObjectJson object = new ObjectJson(directionCheck(direction), "OBSTACLE", i);
                    objects.add(object);
                    return true;
                }
            }
            for(Robot robot : world.getRobots()){
                if(!robot.getRobotName().equals(myRobot.getRobotName())){
                    if(robot.robotBlocksPosition(position, robot)){
                        ObjectJson object = new ObjectJson(directionCheck(direction), "ROBOT", i);
                        objects.add(object);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean SightCheckSouth(int visibility, Robot myRobot, World world, int direction){
        Position position;
        for(int i = 1 ; i < Math.abs(visibility) + 1 ; i++){
            position = new Position(myRobot.getCurrentPosition().getX(), myRobot.getCurrentPosition().getY()- i);
            for (SquareObstacle obstacle : world.getOBSTACLES()) {
                if (obstacle.blocksPosition(position)) {
                    ObjectJson object = new ObjectJson(directionCheck(direction), "OBSTACLE", i);
                    objects.add(object);
                    return true;
                }
            }
            for(Robot robot : world.getRobots()){
                if(!robot.getRobotName().equals(myRobot.getRobotName())){
                    if(robot.robotBlocksPosition(position, robot)){
                        ObjectJson object = new ObjectJson(directionCheck(direction), "ROBOT", i);
                        objects.add(object);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void edge(Robot myRobot, World world, int direction){
        ObjectJson objectJson;
        switch(direction){
            case 1:
                if(myRobot.getCurrentPosition().getY() >= 0) {
                    if ((world.getTOP_LEFT().getY() - myRobot.getCurrentPosition().getY() <= world.VISIBILITY)) {
                        objectJson = new ObjectJson(directionCheck(1), "EDGE",
                                world.getTOP_LEFT().getY() - myRobot.getCurrentPosition().getY());
                        objects.add(objectJson);
                    }
                }else if(myRobot.getCurrentPosition().getY() < 0){
                    if ((world.getTOP_LEFT().getY() + (-myRobot.getCurrentPosition().getY()) <= world.VISIBILITY)){
                        objectJson = new ObjectJson(directionCheck(1), "EDGE",
                                world.getTOP_LEFT().getY() + (-myRobot.getCurrentPosition().getY()));
                        objects.add(objectJson);
                    }
                }
                break;
            case 2:
                if(myRobot.getCurrentPosition().getX() >= 0) {
                    if ((world.getBOTTOM_RIGHT().getX() - myRobot.getCurrentPosition().getX()) <= world.VISIBILITY) {
                        objectJson = new ObjectJson(directionCheck(2), "EDGE",
                                (world.getBOTTOM_RIGHT().getX() - (myRobot.getCurrentPosition().getX())));
                        objects.add(objectJson);
                    }
                }else if(myRobot.getCurrentPosition().getX() < 0) {
                    if ((world.getBOTTOM_RIGHT().getX() + (-myRobot.getCurrentPosition().getX())) <= world.VISIBILITY){
                        objectJson = new ObjectJson(directionCheck(2), "EDGE",
                                (world.getBOTTOM_RIGHT().getX()) + (-myRobot.getCurrentPosition().getX()));
                        objects.add(objectJson);
                    }
                }
                break;
            case 3:
                if(myRobot.getCurrentPosition().getY() >= 0){
                    if((world.getTOP_LEFT().getY() + myRobot.getCurrentPosition().getY() <= world.VISIBILITY)){
                        objectJson = new ObjectJson(directionCheck(3), "EDGE",
                                world.getTOP_LEFT().getY() + myRobot.getCurrentPosition().getY());
                        objects.add(objectJson);
                    }
                }else if(myRobot.getCurrentPosition().getY() < 0){
                    if((world.getTOP_LEFT().getY() - (-myRobot.getCurrentPosition().getY()) <= world.VISIBILITY)){
                        objectJson = new ObjectJson(directionCheck(3), "EDGE",
                                world.getTOP_LEFT().getY() - (-myRobot.getCurrentPosition().getY()));
                        objects.add(objectJson);
                    }
                }
                break;
            case 4:
                if(myRobot.getCurrentPosition().getX() >= 0){
                    if(((world.getBOTTOM_RIGHT().getX()) + (myRobot.getCurrentPosition().getX()))<= world.VISIBILITY){
                        objectJson = new ObjectJson(directionCheck(4), "EDGE",
                                (world.getBOTTOM_RIGHT().getX()) + myRobot.getCurrentPosition().getX());
                        objects.add(objectJson);
                    }
                }else if (myRobot.getCurrentPosition().getX() < 0){
                    if(((world.getBOTTOM_RIGHT().getX()) - (-myRobot.getCurrentPosition().getX()))<= world.VISIBILITY){
                        objectJson = new ObjectJson(directionCheck(4), "EDGE",
                                (world.getBOTTOM_RIGHT().getX()  - (-myRobot.getCurrentPosition().getX())));
                        objects.add(objectJson);
                    }
                }
                break;
        }
    }

    public static class ObjectJson {
        String direction;
        String objectType;
        int steps;

        public ObjectJson(String direction, String objectType, int steps) {
            this.direction = direction;
            this.objectType = objectType;
            this.steps = steps;
        }
    }
    public static class DataJson {
        ObjectJson[] objects;
        public DataJson(ObjectJson[] objects) {
            this.objects = objects;
        }
    }

    public static class StateResponseJSon{
            int[] position;
            String direction;
            int shields;
            int shots;
            String status;

            public StateResponseJSon(int[] position, String direction, int shields, int shots, String status){
                this.position = position;
                this.direction = direction;
                this.shields = shields;
                this.shots = shots;
                this.status = status;
            }
        }

    public static class LookResponseJson{
            String result;
            DataJson data;
            StateResponseJSon state;

            public LookResponseJson(String result, DataJson data, StateResponseJSon state){
                this.result = result;
                this.data = data;
                this.state = state;
            }
    }
}

