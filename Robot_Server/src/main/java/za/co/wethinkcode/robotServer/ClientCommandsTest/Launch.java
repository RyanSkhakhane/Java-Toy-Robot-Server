package za.co.wethinkcode.robotServer.ClientCommandsTest;

import com.google.gson.Gson;
import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Position;
import za.co.wethinkcode.robotServer.Robot.*;
import za.co.wethinkcode.robotServer.World.Obstacle;
import za.co.wethinkcode.robotServer.World.SquareObstacle;
import za.co.wethinkcode.robotServer.World.World;

import java.util.ArrayList;
import java.util.Random;

import static za.co.wethinkcode.robotServer.ClientHandler.world;
//import static za.co.wethinkcode.robotServer.RobotServer.worldObstacles;

public class Launch extends ClientCommands {
    static public boolean world_full = false;
    static public boolean name_already_taken = false;
    public Launch(String make, String name) {
        super("launch",make,name);
    }

    @Override
    public String execute(World world, String[] arguments) {

        switch (getArgument()){
            case "normal":
                Normal robot = new Normal(world, getArgument2(), getArgument());
                Position freePosition = findFreeSpace(world);
                robot.setRobotPosition(0,0);
                ClientHandler.robots.add(robot);
                return responseFormulator(robot);
            case "shooter": // 'machinegun' replaced with 'shooter'
                MachineGun machineGun = new MachineGun(world, getArgument2(), getArgument());

                name_already_taken = check_name_already_taken(machineGun);
                if (name_already_taken) {
                    name_already_taken = false;
                    return "{\"result\":\"ERROR\",\"data\":{\"message\":\"Too many of you in this world\"}}";
                }
                freePosition = findFreeSpace(world);


//                machineGun.setRobotPosition(0,0);
                if(!world_full && !name_already_taken){
                    machineGun.setRobotPosition(freePosition.getX(), freePosition.getY()); // setting the robot into a free position
                    ClientHandler.robots.add(machineGun);
                    System.out.println("\n>>>>  Free Space: (" + freePosition.getX() + "," + freePosition.getY() + ")\n  <<<<<");
                }


                if(world_full){
                    return "{\"result\":\"ERROR\",\"data\":{\"message\":\"No more space in this world\"}}";
                }
//                if(ClientHandler.robots.size() > 1){
//                    ArrayList<Robot> robots = ClientHandler.robots;
//
//                    for (int i = 0; i < ClientHandler.robots.size(); i++) {
//                        String name1 = this.getArgument2();
//                        String name2 = ClientHandler.robots.get(0).getRobotName();
//                        if (name1.equalsIgnoreCase(name2)) {
//                            ClientHandler.robots.clear();
//                            return "{\"result\":\"ERROR\",\"data\":{\"message\":\"Too many of you in this world\"}}";
//                        }
//                    }
//                    ClientHandler.robots.clear();
//                    return "{\"result\":\"ERROR\",\"data\":{\"message\":\"No more space in this world\"}}";
//                }
                return responseFormulator(machineGun);
            case "sniper":
                Sniper sniper = new Sniper(world, getArgument2(), getArgument());
                freePosition = findFreeSpace(world);
                sniper.setRobotPosition(0,0);
                ClientHandler.robots.add(sniper);
                return responseFormulator(sniper);
            case "tank":
                Tank tank = new Tank(world, getArgument2(), getArgument());
                freePosition = findFreeSpace(world);
                tank.setRobotPosition(0,0);
                ClientHandler.robots.add(tank);
                return responseFormulator(tank);
        }
        return "Invalid tank type selected";
    }

    private boolean check_name_already_taken(MachineGun machineGun) {
        for (Robot robot : world.getRobots()) {
            if (robot.getRobotName().equalsIgnoreCase(machineGun.getRobotName())) {
                name_already_taken = true;

            }
        }
        return name_already_taken;
    }

    private String responseFormulator(Robot robot){
//        Gson gson = new GsonBuilder()
//                .setPrettyPrinting()
//                .create();

        Gson gson = new Gson();
        int[] position = {robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()};
        StateResponse stateResponse = new StateResponse(position, robot.getCurrentDirection().toString(),
                robot.getShields(), robot.getShots(), robot.getStatus());
        DataResponse dataResponse = new DataResponse(position, 1, 3, 3, 3);
        LaunchResponse launchResponse = new LaunchResponse("OK", dataResponse, stateResponse);
        String toReturn = gson.toJson(launchResponse);

        return toReturn;
    }

    private Position findFreeSpace(World world){

        Random random = new Random();
        world_full = false;

//        System.out.println("World Obsticle List: " + worldObstacles.toString());

        ArrayList<Position> positions = new ArrayList<>();
        int x1 = world.getBOTTOM_RIGHT().getX();
        int x2 = world.getTOP_LEFT().getX();

        int y1 = world.getBOTTOM_RIGHT().getY();
        int y2 = world.getTOP_LEFT().getY();

        int count = x1-1;
        while(x2<count){

            for(int i=(y1+1); i<y2; i++) {
//                System.out.println("(" + (count) + "," + i + ")");
                Position freePosition = new Position(count, i);
                positions.add(freePosition);
            }
            count--;
        }
        if(world.getRobots().size()>0) {



            for (Robot robots : world.getRobots()) {
                Position robotPosition = new Position(robots.getCurrentPosition().getX(), robots.getCurrentPosition().getY());
                if (positions.contains(robotPosition)) {
                    positions.remove(robotPosition);
//                    System.out.println("(" + robotPosition.getX() + "," + robotPosition.getY() + ") is occupied");
                }
            }
        }else{
            return new Position(0, 0);
        }
        if(positions.size()>0){
            return positions.get(0);
        }

        world_full = true;
        return new Position(0, 0);

//        while(true){
//            boolean free = true;
////            In a 2x2 world:
//            int lowX =world.getTOP_LEFT().getX()+1; // -1
//            int highX = world.getBOTTOM_RIGHT().getX(); // 2
//
//            int highY =world.getTOP_LEFT().getY(); // 2
//            int lowY = world.getBOTTOM_RIGHT().getY()+1; // -1
//
//            int randomX = random.nextInt(highX-lowX)+lowX;
//            int randomY = random.nextInt(highY-lowY)+lowY;
//
//            Position freePosition = new Position(randomX, randomY);
//            for(Obstacle obstacles: world.getOBSTACLES()) {
//                if (obstacles.blocksPosition(freePosition)) {
//                    free = false;
//                }
//            }
//            for(Robot robots: world.getRobots())    {
//                if(robots.getCurrentPosition().getX() == freePosition.getX()
//                        && robots.getCurrentPosition().getY() == freePosition.getY()){
//                    free = false;
//                }
//            }
//            if(free){
//                world_full = false;
//                return freePosition;
//            } else {
//                world_full = true;
//                return new Position(0,0);
//            }
//        }
    }


    public class LaunchResponse{
        String result;
        DataResponse data;
        StateResponse state;
        public LaunchResponse(String result, DataResponse data, StateResponse state){
            this.result = result;
            this.data = data;
            this.state = state;
        }
    }

    public static class DataResponse{
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

    public static class StateResponse{
        int[] position;
        String direction;
        int shields;
        int shots;
        String status;

        public StateResponse(int[] position, String direction, int shields, int shots, String status){
            this.position = position;
            this.direction = direction;
            this.shields = shields;
            this.shots = shots;
            this.status = status;
        }
    }
}
