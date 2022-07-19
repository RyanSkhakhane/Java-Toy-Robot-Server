package za.co.wethinkcode.robotServer.RobotWorld.ClientCommands;

import za.co.wethinkcode.robotServer.RobotWorld.World.World;

public interface CommandInterface {

    String execute(World world, String[] arguments);
}
