package za.co.wethinkcode.robotServer.ClientCommandsTest;

import za.co.wethinkcode.robotServer.World.World;

public interface CommandInterface {

    String execute(World world, String[] arguments);
}
