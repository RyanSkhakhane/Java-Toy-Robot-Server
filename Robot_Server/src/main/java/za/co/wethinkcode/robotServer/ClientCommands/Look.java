package za.co.wethinkcode.robotServer.ClientCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.World.World;

public class Look extends ClientCommands{

    public Look() {
        super("look");
    }

    @Override
    public String execute(ClientHandler clienthandler, World world, String[] arguments) {
        return null;
    }
}
