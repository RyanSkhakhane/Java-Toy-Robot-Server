package za.co.wethinkcode.robotServer.ClientCommands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Position;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.Obstacle;
import za.co.wethinkcode.robotServer.World.World;

import java.util.Random;


public class Launch extends ClientCommands {

    public Launch(String make, String name) {
        super("launch",make,name);
    }


    @Override
    public String execute(ClientHandler clienthandler, World world, String[] arguments) {
        Robot robot = new Robot(world, getArgument2(), getArgument());
        Position freePosition = findFreeSpace(world);
        robot.setRobotPosition(freePosition.getX(),freePosition.getY());
        clienthandler.robots.add(robot);
        return responseFormulator(robot);

    }

    private String responseFormulator(Robot robot){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        int[] position = {robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()};
        StateRespone stateRespone = new StateRespone(position, robot.getCurrentDirection().toString(),
                robot.getShields(), robot.getShots(), robot.getStatus());
        DataResponse dataResponse = new DataResponse(position, 0, 0, 0, 0);
        LaunchResponse launchResponse = new LaunchResponse("OK", dataResponse, stateRespone);
        return gson.toJson(launchResponse);
    }

    private Position findFreeSpace(World world){
        Random random = new Random();
        while(true){
            boolean free = true;
            Position freePosition = new Position((random.nextInt(world.getBOTTOM_RIGHT().getX() -
                    (world.getTOP_LEFT().getX())) + (world.getTOP_LEFT().getX())),
                    (random.nextInt(world.getTOP_LEFT().getY() -(world.getBOTTOM_RIGHT().getY()))) + (world.getBOTTOM_RIGHT().getY()));
            for(Obstacle obstacles: world.getOBSTACLES()){
                if(obstacles.blocksPosition(freePosition)){
                    free = false;
                }
            for(Robot robots: world.getRobots())    {
                if(robots.getCurrentPosition().getX() == freePosition.getX()
                        && robots.getCurrentPosition().getY() == freePosition.getY()){
                    free = false;
                }
            }
            }
            if(free){
                return freePosition;
            }
        }
    }

    public class LaunchResponse{
        String result;
        DataResponse data;
        StateRespone state;
        public LaunchResponse(String result, DataResponse data, StateRespone state){
            this.result = result;
            this.data = data;
            this.state = state;
        }
    }

    public class DataResponse{
        int[] position;
        int visibility;
        int reload;
        int repair;
        int shields;


        public DataResponse(int[] position, int visibility, int reload, int repair, int shields){
            this.position = position;
            this.visibility = visibility;
            this.reload = reload;
            this.repair = repair;
            this.shields = shields;
        }
    }

    public class StateRespone{
        int[] position;
        String direction;
        int shields;
        int shots;
        String status;

        public StateRespone(int[] position, String direction, int shields, int shots, String status){
            this.position = position;
            this.direction = direction;
            this.shields = shields;
            this.shots = shots;
            this.status = status;
        }
    }
}