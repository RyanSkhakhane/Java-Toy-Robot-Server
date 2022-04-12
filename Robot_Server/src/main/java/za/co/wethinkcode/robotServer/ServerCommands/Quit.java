package za.co.wethinkcode.robotServer.ServerCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.util.ArrayList;

public class Quit extends ServerCommand{


    @Override
    public void execute(ArrayList<ClientHandler> users, ArrayList<Robot> robots, World world){
        for(ClientHandler clientHandler : users) {
            if(!clientHandler.getClientUsername().equals("admin")){
                clientHandler.closeEverything(clientHandler.getSocket(),clientHandler.getBufferedReader(),clientHandler.getBufferedWriter());
                users.remove(clientHandler);
            }
        }
        for(ClientHandler clientHandler : users){
            if(clientHandler.getClientUsername().equals("admin")){
                clientHandler.closeEverything(clientHandler.getSocket(),clientHandler.getBufferedReader(),clientHandler.getBufferedWriter());
                users.remove(clientHandler);
            }
        }
        System.exit(0);
    }
}
