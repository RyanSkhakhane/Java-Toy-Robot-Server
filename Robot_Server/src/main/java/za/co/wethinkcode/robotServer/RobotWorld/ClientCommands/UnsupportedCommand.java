package za.co.wethinkcode.robotServer.RobotWorld.ClientCommands;

import za.co.wethinkcode.robotServer.RobotWorld.World.World;

public class UnsupportedCommand extends ClientCommands {
    public UnsupportedCommand(String robotName) {
        super("unsupported", robotName);
    }

    @Override
    public String execute(World world, String[] arguments) {
        return "{\"result\":\"ERROR\",\"data\":{\"message\":\"Unsupported command\"}}";
    }
}
