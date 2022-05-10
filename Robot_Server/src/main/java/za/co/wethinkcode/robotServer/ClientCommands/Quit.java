package za.co.wethinkcode.robotServer.ClientCommands;

import za.co.wethinkcode.robotServer.World.World;

public class Quit extends ClientCommands{
    public Quit(String name) {
        super(name);
    }

    @Override
    public String execute(World world, String[] arguments) {
        return null;
    }
}
