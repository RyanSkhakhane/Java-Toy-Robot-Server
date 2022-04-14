package za.co.wethinkcode.robotServer.ClientCommands;

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
    public String execute(ClientHandler clienthandler, World world) {
        Robot robot = new Robot(world, getArgument2(), getArgument());
        Position freePosition = findFreeSpace(world);
        robot.setRobotPosition(freePosition.getX(),freePosition.getY());
//        clienthandler.addRobot(robot);
        clienthandler.robots.add(robot);
        System.out.println(robot.getCurrentPosition().getX()+ robot.getCurrentPosition().getY());
        return "Your robot (" + robot.getRobotName() + ") has been launched into the world at " +
                "position[" + robot.getCurrentPosition().getX() + "," + robot.getCurrentPosition().getY() + "]";
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
}
