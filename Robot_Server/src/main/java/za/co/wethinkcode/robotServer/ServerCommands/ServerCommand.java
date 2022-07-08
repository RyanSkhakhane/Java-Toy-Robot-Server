package za.co.wethinkcode.robotServer.ServerCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Robot.Robot;
import za.co.wethinkcode.robotServer.World.World;


import java.sql.SQLException;
import java.util.ArrayList;

public abstract class ServerCommand {

    public abstract void execute(ArrayList<ClientHandler> users, ArrayList<Robot> robots, World world) throws SQLException;

    public static ServerCommand create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]) {
            case "quit":
                return new Quit();
            case "robots":
                return new Robots();
            case "dump":
                return new Dump();
            case "restore":
                return new Restorer();
            case "save":
                return new Saver();

            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }
}
