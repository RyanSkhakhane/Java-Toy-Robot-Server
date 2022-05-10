package za.co.wethinkcode.robotServer.ServerCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.io.IOException;
import java.util.ArrayList;

public class Quit extends ServerCommand{


    @Override
    public void execute(ArrayList<ClientHandler> users, ArrayList<Robot> robots, World world){
        for(ClientHandler user : users){
            try {
                user.getSocket().close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("System shutting down");
        System.exit(0);
    }
}
