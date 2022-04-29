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
        for(Robot robot : clienthandler.getRobots()){
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
            ObjectJson[] objectJsons = objects.toArray(new ObjectJson[0]);
            DataJson data = new DataJson(objectJsons);
            LookResponseJson lookResponseJson = new LookResponseJson("ok", data, state);
            objects.clear();
            return gson.toJson(lookResponseJson);
            }
        }
        return null;
    }

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
                ObjectJson[] objectJsons = objects.toArray(new ObjectJson[0]);
                DataJson data = new DataJson(objectJsons);
                LookResponseJson lookResponseJson = new LookResponseJson("ok", data, state);
                objects.clear();
                return gson.toJson(lookResponseJson);
            }
        }
        return null;
    }

    public boolean lookCommand(int visibility, Robot myRobot, World world , int direction) {
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
                ObjectJson object = new ObjectJson(directionCheck(direction), "OBSTACLE", i+ 1);
                objects.add(object);
                return true;
            }
        }
        for (int i = 0; i < world.getRobots().size(); i++){
            if (!world.getRobots().get(i).getRobotName().equals(myRobot.getRobotName())){
                if(robotBlocksPath(myRobot.getCurrentPosition(), newPosition, world.getRobots().get(i))){
                    ObjectJson object = new ObjectJson(directionCheck(direction), "ROBOT", i + 1);
                    objects.add(object);
                    return true;
                }
            }
        }
        if (isNewPositionAllowed(newPosition, world)) {
        }
        else{
            edge(myRobot, world, direction);

        }
        return false;
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

    private void edge(Robot myRobot, World world, int direction){
        ObjectJson objectJson;
        switch(direction){
            case 1:
                if((world.getTOP_LEFT().getY() - myRobot.getCurrentPosition().getY() <= world.VISIBILITY)){
                    objectJson = new ObjectJson(directionCheck(1), "EDGE",
                            world.getTOP_LEFT().getY() - myRobot.getCurrentPosition().getY());
                    objects.add(objectJson);
                }
                break;
            case 2:
                if((world.getBOTTOM_RIGHT().getX() - myRobot.getCurrentPosition().getX()) <= world.VISIBILITY){
                    objectJson = new ObjectJson(directionCheck(2), "EDGE",
                            world.getBOTTOM_RIGHT().getX() - myRobot.getCurrentPosition().getX());
                    objects.add(objectJson);
                }
                break;
            case 3:
                objectJson = new ObjectJson(directionCheck(3), "EDGE",
                        -(world.getBOTTOM_RIGHT().getY()) - (-myRobot.getCurrentPosition().getY()));
                objects.add(objectJson);
                break;
            case 4:
                if(((world.getBOTTOM_RIGHT().getX()) - (myRobot.getCurrentPosition().getX()))<= world.VISIBILITY){
                    objectJson = new ObjectJson(directionCheck(4), "EDGE",
                            -(world.getBOTTOM_RIGHT().getX()) - (-myRobot.getCurrentPosition().getX()));
                    objects.add(objectJson);
                }
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

