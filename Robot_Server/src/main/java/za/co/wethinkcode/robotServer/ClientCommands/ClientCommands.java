package za.co.wethinkcode.robotServer.ClientCommands;
import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.World.World;
import com.google.gson.Gson;

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

    public abstract String execute(ClientHandler clienthandler, World world, String[] arguments);

    public static ClientCommands create(String instruction) throws CommandNotFoundException {
        Gson gson = new Gson();
        System.out.println(instruction);
        RequestMessage requestMessage = gson.fromJson(instruction, RequestMessage.class);

        switch (requestMessage.command) {
            case "launch":
                System.out.println("Launch command passed");
                return new Launch(requestMessage.arguments[0], requestMessage.robot);
            case "look":
                System.out.println("Look command passed");
                return new Look(requestMessage.robot);
            case "state":
                System.out.println("State command passed");
                return new State(requestMessage.robot);
            case "fire":
                System.out.println("Fire command passed");
                return new Fire(requestMessage.robot);
            case "forward":
                System.out.println("Forward command passed");
                return new Forward(requestMessage.robot, Integer.parseInt(requestMessage.arguments[0]));
            case "back":
                System.out.println("Back command passed");
                return new Back(requestMessage.robot, Integer.parseInt(requestMessage.arguments[0]));
            default:
                throw new CommandNotFoundException("Unsupported command: " + instruction);
        }
    }

    public static class CommandNotFoundException extends Exception {

        public CommandNotFoundException(String message) {
            super(message);
        }
    }


}
