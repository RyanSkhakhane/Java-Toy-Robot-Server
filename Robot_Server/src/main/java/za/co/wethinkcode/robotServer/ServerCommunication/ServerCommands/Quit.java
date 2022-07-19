package za.co.wethinkcode.robotServer.ServerCommunication.ServerCommands;

import za.co.wethinkcode.robotServer.ServerCommunication.ClientHandler;
import za.co.wethinkcode.robotServer.RobotWorld.Robot.Robot;
import za.co.wethinkcode.robotServer.RobotWorld.World.World;
import java.util.ArrayList;

public class Quit extends ServerCommand {

    @Override
    public void execute(ArrayList<ClientHandler> users, ArrayList<Robot> robots, World world){
        System.out.println("System shutting down");
        System.exit(0);
    }
}
