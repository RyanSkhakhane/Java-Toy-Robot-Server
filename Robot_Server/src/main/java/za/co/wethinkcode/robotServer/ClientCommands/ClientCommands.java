package za.co.wethinkcode.robotServer.ClientCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.ServerCommands.Dump;
import za.co.wethinkcode.robotServer.ServerCommands.Quit;
import za.co.wethinkcode.robotServer.ServerCommands.Robots;
import za.co.wethinkcode.robotServer.ServerCommands.ServerCommand;
import za.co.wethinkcode.robotServer.World.World;

public abstract class ClientCommands implements CommandInterface {

    private final String name;
    private String argument;
    private String argument2;

    public ClientCommands(String name){
        this.name = name.trim().toLowerCase();
        //this.argument="";
    }

    public ClientCommands(String name, String argument){
        this(name);
        this.argument=argument.trim();
    }

    public ClientCommands(String name, String argument, String argument2){
        this(name);
        this.argument=argument.trim();
        this.argument2 = argument2.trim();
    }

    public String getName(){
        return name;
    }

    public String getArgument() {
        return argument;
    }


    public String getArgument2() {
        return argument2;
    }

    public abstract String execute(ClientHandler clienthandler, World world);

    public static ClientCommands create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]) {
            case "launch":
                System.out.println("Launch command passed");
                return new Launch(args[1], args[2]);
            case "look":
                System.out.println("Look command passed");
                return new Look();
            case "state":
                System.out.println("State command passed");
                return new State();
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }
}
