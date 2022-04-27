//package za.co.wethinkcode.robotServer.ClientCommands;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import za.co.wethinkcode.robotServer.ClientHandler;
//import za.co.wethinkcode.robotServer.Direction;
//import za.co.wethinkcode.robotServer.Position;
//import za.co.wethinkcode.robotServer.Robot;
//import za.co.wethinkcode.robotServer.World.SquareObstacle;
//import za.co.wethinkcode.robotServer.World.World;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//public class Look extends ClientCommands{
//    ArrayList<String> objects = new ArrayList<>();
//    Gson gson = new GsonBuilder()
//            .setPrettyPrinting()
//            .create();
//
//
//    public Look(String name) {
//        super("look",name);
//    }
//
//    @Override
//    public String execute(ClientHandler clienthandler, World world, String[] arguments) {
//        System.out.println(getArgument());
//        for(Robot robot : clienthandler.getRobots()){
//            if(robot.getRobotName().equals(getArgument())){
//                switch (robot.getCurrentDirection()){
//                    case
//                }
//            }
//        }
//        return null;
//    }
//
//    public Robot.UpdateResponse lookCommand(int visibility, Robot myRobot, World world , int direction) {
//
//        int newX = myRobot.getCurrentPosition().getX();
//        int newY = myRobot.getCurrentPosition().getY();
//
//
//        switch (myRobot.getCurrentDirection()) {
//            case NORTH:
//                newY = newY + visibility;
//                break;
//            case SOUTH:
//                newY = newY - visibility;
//                break;
//            case EAST:
//                newX = newX + visibility;
//                break;
//            case WEST:
//                newX = newX - visibility;
//                break;
//        }
//        Position newPosition = new Position(newX, newY);
//        for (int i = 0; i < world.getOBSTACLES().length; i++) {
//            if (blocksPath(myRobot.getCurrentPosition(), newPosition, Arrays.asList(world.getOBSTACLES()).get(i))) {
//                return Robot.UpdateResponse.FAILED_OBSTRUCTED_OBSTACLE;
//            }
//        }
//        for (int i = 0; i < world.getRobots().size(); i++){
//            if (!world.getRobots().get(i).getRobotName().equals(myRobot.getRobotName())){
//                if(robotBlocksPath(myRobot.getCurrentPosition(), newPosition, world.getRobots().get(i))){
//                    return Robot.UpdateResponse.FAILED_OBSTRUCTED_ROBOT;
//                }
//            }
//        }
//        if (isNewPositionAllowed(newPosition, world)) {
//            return Robot.UpdateResponse.SUCCESS;
//        }
//        return Robot.UpdateResponse.FAILED_OUTSIDE_WORLD;
//    }
//
//    public boolean isNewPositionAllowed(Position position , World world) {
//        if (position.isIn(world.getTOP_LEFT(), world.getBOTTOM_RIGHT())) {
//            return true;
//        }
//        return false;
//    }
//
//
//    private boolean robotBlocksSight(Position position, Robot robot) {
//        if(robot.getCurrentPosition().getX() == position.getX() && robot.getCurrentPosition().getY() == position.getY()){
//            return true;
//        }
//        return false;
//    }
//
//    private boolean robotBlocksPath(Position a, Position b , Robot robot){
//        if(a.getX() > b.getX()){
//            int path = a.getX() - b.getX();
//            for(int i = 0; i < path; i++){
//                if(robotBlocksSight(new Position(b.getX()+i, b.getY()), robot)){
//                    String object = gson.toJson(new ObjectsJson())
//                    return true;
//                }
//            }
//        }
//        else if(a.getX() < b.getX()){
//            int path = b.getX() - a.getX();
//            for(int i = 0; i < path; i++){
//                if(robotBlocksSight(new Position(a.getX()+i, a.getY()), robot)){
//                    return true;
//                }
//            }
//        }
//        else if(a.getY() > b.getY()){
//            int path = a.getY() - b.getY();
//            for(int i = 0; i < path; i++){
//                if(robotBlocksSight(new Position(b.getX(), b.getY()+ i), robot)){
//                    return true;
//                }
//            }
//        }
//        else if(a.getY() < b.getY()){
//            int path = b.getY() - a.getY();
//            for(int i = 0; i < path; i++){
//                if(robotBlocksSight(new Position(a.getX(), a.getY()+ i), robot)){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//
//    public boolean blocksPosition(Position position , SquareObstacle squareObstacle) {
//        if(squareObstacle.getBottomLeftX() <= position.getX() && position.getX() <= (squareObstacle.getBottomLeftX() + 3)){
//            if(squareObstacle.getBottomLeftY() <= position.getY() && position.getY()<= (squareObstacle.getBottomLeftY() + 3))
//                return true;
//        }
//        return false;
//    }
//
//    public boolean blocksPath(Position a, Position b , SquareObstacle squareObstacle){
//        if(a.getX() > b.getX()){
//            int path = a.getX() - b.getX();
//            for(int i = 0; i < path; i++){
//                if(blocksPosition(new Position(b.getX()+i, b.getY()), squareObstacle)){
//                    return true;
//                }
//            }
//        }
//        else if(a.getX() < b.getX()){
//            int path = b.getX() - a.getX();
//            for(int i = 0; i < path; i++){
//                if(blocksPosition(new Position(a.getX()+i, a.getY()), squareObstacle)){
//                    return true;
//                }
//            }
//        }
//        else if(a.getY() > b.getY()){
//            int path = a.getY() - b.getY();
//            for(int i = 0; i < path; i++){
//                if(blocksPosition(new Position(b.getX(), b.getY()+ i), squareObstacle)){
//                    return true;
//                }
//            }
//        }
//        else if(a.getY() < b.getY()){
//            int path = b.getY() - a.getY();
//            for(int i = 0; i < path; i++){
//                if(blocksPosition(new Position(a.getX(), a.getY()+ i) , squareObstacle)){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    private String direction(int direction){
//        switch (direction){
//            case 1 :
//                return "north";
//            case 2 :
//                return "east";
//            case 3 :
//                return "south";
//            case 4 :
//                return "west";
//        }
//        return "none";
//    }
//
//    private void startNorth(int visibility, Robot myRobot, World world){
//        int direction = 1;
//        lookCommand(visibility, myRobot, world, direction);
//        direction += 1;
//        myRobot.setCurrentDirection(Direction.EAST);
//        lookCommand(visibility, myRobot, world, direction);
//        direction += 1;
//        myRobot.setCurrentDirection(Direction.SOUTH);
//        lookCommand(visibility, myRobot, world, direction);
//        direction += 1;
//        myRobot.setCurrentDirection(Direction.WEST);
//        lookCommand(visibility, myRobot, world, direction);
//        myRobot.setCurrentDirection(Direction.NORTH);
//    }
//
//    private void startEast(int visibility, Robot myRobot, World world){
//        int direction = 1;
//        lookCommand(visibility, myRobot, world, direction);
//        direction += 1;
//        myRobot.setCurrentDirection(Direction.SOUTH);
//        lookCommand(visibility, myRobot, world, direction);
//        direction += 1;
//        myRobot.setCurrentDirection(Direction.WEST);
//        lookCommand(visibility, myRobot, world, direction);
//        direction += 1;
//        myRobot.setCurrentDirection(Direction.NORTH);
//        lookCommand(visibility, myRobot, world, direction);
//        myRobot.setCurrentDirection(Direction.EAST);
//    }
//
//    private void startSouth(int visibility, Robot myRobot, World world){
//        int direction = 1;
//        lookCommand(visibility, myRobot, world, direction);
//        direction += 1;
//        myRobot.setCurrentDirection(Direction.WEST);
//        lookCommand(visibility, myRobot, world, direction);
//        direction += 1;
//        myRobot.setCurrentDirection(Direction.NORTH);
//        lookCommand(visibility, myRobot, world, direction);
//        direction += 1;
//        myRobot.setCurrentDirection(Direction.EAST);
//        lookCommand(visibility, myRobot, world, direction);
//        myRobot.setCurrentDirection(Direction.SOUTH);
//    }
//
//    private void startWest(int visibility, Robot myRobot, World world){
//        int direction = 1;
//        lookCommand(visibility, myRobot, world, direction);
//        direction += 1;
//        myRobot.setCurrentDirection(Direction.NORTH);
//        lookCommand(visibility, myRobot, world, direction);
//        direction += 1;
//        myRobot.setCurrentDirection(Direction.EAST);
//        lookCommand(visibility, myRobot, world, direction);
//        direction += 1;
//        myRobot.setCurrentDirection(Direction.SOUTH);
//        lookCommand(visibility, myRobot, world, direction);
//        myRobot.setCurrentDirection(Direction.WEST);
//    }
//
//
//    static class ObjectsJson{
//        String direction;
//        String objectType;
//        int steps;
//
//        public ObjectsJson(String direction, String objectType, int steps){
//            this.direction = direction;
//            this.objectType = objectType;
//            this.steps = steps;
//        }
//    }
//}
