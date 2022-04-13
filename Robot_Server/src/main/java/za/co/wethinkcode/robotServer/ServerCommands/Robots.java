package za.co.wethinkcode.robotServer.ServerCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.util.ArrayList;

public class Robots extends ServerCommand{
    @Override
    public void execute(ArrayList<ClientHandler> users, ArrayList<Robot> robots, World world) {
        robots.add(new Robot(world, "Jack", "normal"));
        robots.add(new Robot(world, "Matt", "normal"));
        robots.add(new Robot(world, "Jill", "normal"));
        robots.add(new Robot(world, "Cindy", "normal"));
        System.out.println(robots.get(0).getRobotName());
        System.out.println(robots.get(0).getRobotState());
    }
}
