package za.co.wethinkcode.robotServer.ServerCommunication.ServerCommands;

import za.co.wethinkcode.robotServer.ServerCommunication.ClientHandler;
import za.co.wethinkcode.robotServer.RobotWorld.Robot.Robot;
import za.co.wethinkcode.robotServer.RobotWorld.World.World;

import java.util.ArrayList;

public class Robots extends ServerCommand {

    @Override
    public void execute(ArrayList<ClientHandler> users, ArrayList<Robot> robots, World world) {
        if (robots.size() == 0) {
            System.out.println("ROBOTS:");
            System.out.println("There are no robots in the world");
        } else {
            for (int i = 0; i <= robots.size() - 1; i++) {
                System.out.println("Robot : "+ robots.get(i).getRobotName().substring(0, 1).toUpperCase()
                        + robots.get(i).getRobotName().substring(1));
                System.out.println(robots.get(i).getRobotState());
                System.out.println("Shots : "+robots.get(i).getShots());
                System.out.println("Shields : "+robots.get(i).getShields());
                System.out.println();
            }
        }
    }
}