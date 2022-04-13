package za.co.wethinkcode.robotServer.ClientCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.World.World;

public abstract class ClientCommands implements CommandInterface {

    public abstract String execute(ClientHandler clienthandler, World world);
}
