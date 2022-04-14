package za.co.wethinkcode.RobotClient.AdminCommands;

import za.co.wethinkcode.RobotClient.ClientCommands.ClientCommands;
import za.co.wethinkcode.RobotClient.ClientCommands.Launch;
import za.co.wethinkcode.RobotClient.ClientCommands.Look;
import za.co.wethinkcode.RobotClient.ClientCommands.State;

public abstract class AdminCommands implements AdminCommandsInterface{

    private final String name;

    public AdminCommands(String name){
        this.name = name.trim().toLowerCase();
        //this.argument="";
    }

    public static AdminCommands create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]) {
            case "quit":
                System.out.println("quit command passed");
                return new Quit();
            case "dump":
                System.out.println("dump command passed");
                return new Dump();
            case "robots":
                System.out.println("robots command passed");
                return new Robots();
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }

    public abstract String execute();

}
