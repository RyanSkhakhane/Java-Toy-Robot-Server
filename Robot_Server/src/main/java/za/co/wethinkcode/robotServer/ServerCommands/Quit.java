package za.co.wethinkcode.robotServer.ServerCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.util.ArrayList;

public class Quit extends ServerCommand{


    @Override
    public void execute(ArrayList<ClientHandler> users, ArrayList<Robot> robots, World world){
        System.out.println("System shutting dooown");
        System.exit(0);
    }
}
