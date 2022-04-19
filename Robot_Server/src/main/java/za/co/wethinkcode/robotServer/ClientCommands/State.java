package za.co.wethinkcode.robotServer.ClientCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.World.World;

public class State extends ClientCommands{

    public State() {
        super("state");
    }


    @Override
    public String execute(ClientHandler clienthandler, World world, String[] arguments) {
        return null;
    }

}
