package za.co.wethinkcode.robotServer.ClientCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.World.World;

public interface CommandInterface {

    String execute(World world, String[] arguments);
}
