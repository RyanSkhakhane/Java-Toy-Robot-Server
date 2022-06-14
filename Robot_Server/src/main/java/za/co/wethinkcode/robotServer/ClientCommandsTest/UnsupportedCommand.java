package za.co.wethinkcode.robotServer.ClientCommandsTest;

import za.co.wethinkcode.robotServer.World.World;

public class UnsupportedCommand extends ClientCommands {
    public UnsupportedCommand(String robotName) {
        super("unsupported", robotName);
    }

    @Override
    public String execute(World world, String[] arguments) {
        return "{\"result\":\"ERROR\",\"data\":{\"message\":\"Unsupported command\"}}";
    }
}
