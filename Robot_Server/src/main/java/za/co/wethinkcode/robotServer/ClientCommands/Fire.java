package za.co.wethinkcode.robotServer.ClientCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Direction;
import za.co.wethinkcode.robotServer.Position;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.World;

public class Fire extends ClientCommands{
    public Fire(String name) {
        super(name);
    }

    @Override
    public String execute(ClientHandler clienthandler, World world, String[] arguments) {
        int distance = 5;
        Robot robot = world.getRobots().get(1);
        Direction direction = robot.getCurrentDirection();
        // get all other robots code ....

        Position position = robot.getCurrentPosition();
        Position finalPosition = new Position(0,0);

        if (Direction.NORTH.equals(robot.getCurrentDirection())){
            finalPosition = new Position(position.getX(),position.getY()+distance);
        }else if (Direction.EAST.equals(robot.getCurrentDirection())){
            finalPosition = new Position(position.getX()+distance,position.getY());
        }else if (Direction.SOUTH.equals(robot.getCurrentDirection())){
            finalPosition = new Position(position.getX(),position.getY()-distance);
        }else if (Direction.WEST.equals(robot.getCurrentDirection())){
            finalPosition = new Position(position.getX()-distance,position.getY());

    }
        //want to see if path is blocked by obstacle then return "There is an Obs",if ibethe i robot then returns "hit",
        //if no hit then "MIss"
        return null;
}
}
