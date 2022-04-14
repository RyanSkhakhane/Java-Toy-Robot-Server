package za.co.wethinkcode.robotServer.ServerCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.util.ArrayList;

public class Robots extends ServerCommand{
    @Override
    public void execute(ArrayList<ClientHandler> users, ArrayList<Robot> robots, World world) {
        for (int i = 0; i <= robots.size() - 1; i++) {
            System.out.println(robots.get(i).getRobotName());
            System.out.println(robots.get(i).getRobotState());
        }
    }
}
