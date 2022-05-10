package za.co.wethinkcode.robotServer.ClientCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.World;

public class Quit extends ClientCommands{
    public Quit(String name) {
        super(name);
    }

    @Override
    public String execute(World world, String[] arguments) {
        for (Robot robot : ClientHandler.robots) {
            if (robot.getRobotName().equals(getArgument())) {
                ClientHandler.broadcastMessage(robot.getRobotName()+" has left the game.");
                ClientHandler.robots.remove(robot);
            }

        }
        return " System shutting down";

    }
}