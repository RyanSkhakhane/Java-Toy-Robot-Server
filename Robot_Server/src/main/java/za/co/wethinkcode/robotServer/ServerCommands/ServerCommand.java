package za.co.wethinkcode.robotServer.ServerCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.World;



import java.util.ArrayList;

public abstract class ServerCommand {

    public abstract void execute(ArrayList<ClientHandler> users, ArrayList<Robot> robots, World world);


    public static ServerCommand create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]) {
            case "quit":
                System.out.println("quit command passed");
                return new Quit();
            case "robots":
                System.out.println("robots command passed");
                return new Robots();
            case "dump":
                System.out.println("dump command passed");
                return new Dump();
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }
}
