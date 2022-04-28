package za.co.wethinkcode.robotServer.ClientCommands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Direction;
import za.co.wethinkcode.robotServer.Position;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.SquareObstacle;
import za.co.wethinkcode.robotServer.World.World;

import java.util.ArrayList;
import java.util.Arrays;

public class Look extends ClientCommands{
    ArrayList<ObjectJson> objects = new ArrayList<>();
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();


    public Look(String name) {
        super("look",name);
    }

    @Override
    public String execute(ClientHandler clienthandler, World world, String[] arguments) {
        StateResponseJSon state;
        System.out.println(getArgument());
        for(Robot robot : clienthandler.getRobots()){
            if(robot.getRobotName().equals(getArgument())){
                int[] position = {robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()};
                state = new StateResponseJSon(position, robot.getCurrentDirection().toString(),
                        robot.getShields(),robot.getShots(), "normal");
                switch (robot.getCurrentDirection()){
                    case NORTH:
                        startNorth(world.VISIBILITY, robot, world);
                    case EAST:
                        startEast(world.VISIBILITY, robot, world);
                    case SOUTH:
                        startSouth(world.VISIBILITY, robot, world);
                    case WEST:
                        startWest(world.VISIBILITY, robot, world);
                }
            ObjectJson[] objectJsons = objects.toArray(new ObjectJson[0]);
            DataJson data = new DataJson(objectJsons);
            LookResponseJson lookResponseJson = new LookResponseJson("ok", data, state);
            objects.clear();
            return gson.toJson(lookResponseJson);
            }
        }
        return null;
    }

    public void lookCommand(int visibility, Robot myRobot, World world , int direction) {
        Gson gson = new Gson();
        int newX = myRobot.getCurrentPosition().getX();
        int newY = myRobot.getCurrentPosition().getY();


        switch (myRobot.getCurrentDirection()) {
            case NORTH:
                newY = newY + visibility;
                break;
            case SOUTH:
                newY = newY - visibility;
                break;
            case EAST:
                newX = newX + visibility;
                break;
            case WEST:
                newX = newX - visibility;
                break;
        }
        Position newPosition = new Position(newX, newY);
        for (int i = 0; i < world.getOBSTACLES().length; i++) {
            if (blocksPath(myRobot.getCurrentPosition(), newPosition, Arrays.asList(world.getOBSTACLES()).get(i))) {
                ObjectJson object = new ObjectJson(directionCheck(direction), "robot", i);
                objects.add(object);
                break;
//                return Robot.UpdateResponse.FAILED_OBSTRUCTED_OBSTACLE;
            }
        }
        for (int i = 0; i < world.getRobots().size(); i++){
            if (!world.getRobots().get(i).getRobotName().equals(myRobot.getRobotName())){
                if(robotBlocksPath(myRobot.getCurrentPosition(), newPosition, world.getRobots().get(i))){
                    ObjectJson object = new ObjectJson(directionCheck(direction), "obstacle", i);
                    objects.add(object);
                    break;
//                    return Robot.UpdateResponse.FAILED_OBSTRUCTED_ROBOT;
                }
            }
        }
        if (isNewPositionAllowed(newPosition, world)) {
//            return Robot.UpdateResponse.SUCCESS;
        }
        else{
            edge(myRobot, world, direction);
        }
//        return Robot.UpdateResponse.FAILED_OUTSIDE_WORLD;
    }

    public boolean isNewPositionAllowed(Position position , World world) {
        if (position.isIn(world.getTOP_LEFT(), world.getBOTTOM_RIGHT())) {
            return true;
        }
        return false;
    }


    private boolean robotBlocksSight(Position position, Robot robot) {
        if(robot.getCurrentPosition().getX() == position.getX() && robot.getCurrentPosition().getY() == position.getY()){
            return true;
        }
        return false;
    }

    private boolean robotBlocksPath(Position a, Position b , Robot robot){
        if(a.getX() > b.getX()){
            int path = a.getX() - b.getX();
            for(int i = 0; i < path; i++){
                if(robotBlocksSight(new Position(b.getX()+i, b.getY()), robot)){
                    return true;
                }
            }
        }
        else if(a.getX() < b.getX()){
            int path = b.getX() - a.getX();
            for(int i = 0; i < path; i++){
                if(robotBlocksSight(new Position(a.getX()+i, a.getY()), robot)){
                    return true;
                }
            }
        }
        else if(a.getY() > b.getY()){
            int path = a.getY() - b.getY();
            for(int i = 0; i < path; i++){
                if(robotBlocksSight(new Position(b.getX(), b.getY()+ i), robot)){
                    return true;
                }
            }
        }
        else if(a.getY() < b.getY()){
            int path = b.getY() - a.getY();
            for(int i = 0; i < path; i++){
                if(robotBlocksSight(new Position(a.getX(), a.getY()+ i), robot)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean blocksPosition(Position position , SquareObstacle squareObstacle) {
        if(squareObstacle.getBottomLeftX() <= position.getX() && position.getX() <= (squareObstacle.getBottomLeftX() + 3)){
            if(squareObstacle.getBottomLeftY() <= position.getY() && position.getY()<= (squareObstacle.getBottomLeftY() + 3))
                return true;
        }
        return false;
    }

    public boolean blocksPath(Position a, Position b , SquareObstacle squareObstacle){
        if(a.getX() > b.getX()){
            int path = a.getX() - b.getX();
            for(int i = 0; i < path; i++){
                if(blocksPosition(new Position(b.getX()+i, b.getY()), squareObstacle)){
                    return true;
                }
            }
        }
        else if(a.getX() < b.getX()){
            int path = b.getX() - a.getX();
            for(int i = 0; i < path; i++){
                if(blocksPosition(new Position(a.getX()+i, a.getY()), squareObstacle)){
                    return true;
                }
            }
        }
        else if(a.getY() > b.getY()){
            int path = a.getY() - b.getY();
            for(int i = 0; i < path; i++){
                if(blocksPosition(new Position(b.getX(), b.getY()+ i), squareObstacle)){
                    return true;
                }
            }
        }
        else if(a.getY() < b.getY()){
            int path = b.getY() - a.getY();
            for(int i = 0; i < path; i++){
                if(blocksPosition(new Position(a.getX(), a.getY()+ i) , squareObstacle)){
                    return true;
                }
            }
        }
        return false;
    }

    private String directionCheck(int direction){
        switch (direction){
            case 1 :
                return "north";
            case 2 :
                return "east";
            case 3 :
                return "south";
            case 4 :
                return "west";
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

    private void edge(Robot myRobot, World world, int direction){
        ObjectJson objectJson;
        switch(direction){
            case 1:
                objectJson = new ObjectJson(directionCheck(1), "edge",
                        world.getTOP_LEFT().getY() - myRobot.getCurrentPosition().getY());
                objects.add(objectJson);
                break;
            case 2:
                objectJson = new ObjectJson(directionCheck(1), "edge",
                        world.getBOTTOM_RIGHT().getX() - myRobot.getCurrentPosition().getX());
                objects.add(objectJson);
                break;
            case 3:
                objectJson = new ObjectJson(directionCheck(1), "edge",
                        -(world.getBOTTOM_RIGHT().getY()) - (-myRobot.getCurrentPosition().getY()));
                objects.add(objectJson);
                break;
            case 4:
                objectJson = new ObjectJson(directionCheck(1), "edge",
                        -(world.getBOTTOM_RIGHT().getX()) - (-myRobot.getCurrentPosition().getX()));
                objects.add(objectJson);
                break;
        }
    }


    public class ObjectJson {
        String direction;
        String objectType;
        int steps;

        public ObjectJson(String direction, String objectType, int steps) {
            this.direction = direction;
            this.objectType = objectType;
            this.steps = steps;
        }
    }
    public class DataJson {
        ObjectJson[] objects;
        public DataJson(ObjectJson[] objects) {
            this.objects = objects;
        }
    }

    public class StateResponseJSon{
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
    public class LookResponseJson{
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

