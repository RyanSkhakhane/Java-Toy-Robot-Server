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

    public abstract String execute(World world, String[] arguments);

    public static ClientCommands create(String instruction) throws CommandNotFoundException {
        Gson gson = new Gson();
        RequestMessage requestMessage = gson.fromJson(instruction, RequestMessage.class);

        switch (requestMessage.command) {
            case "launch":
                return new Launch(requestMessage.arguments[0], requestMessage.robot);
            case "look":
                return new Look(requestMessage.robot);
            case "state":
                return new State(requestMessage.robot);
            case "fire":
                return new Fire(requestMessage.robot);
            case "forward":
                return new Forward(requestMessage.robot, Integer.parseInt(requestMessage.arguments[0]));
            case "back":
                return new Back(requestMessage.robot, Integer.parseInt(requestMessage.arguments[0]));
            case "turn":
                return new Turn(requestMessage.robot, requestMessage.arguments[0]);
            case "reload":
                return new Reload(requestMessage.robot);
            case "repair":
                return new Repair(requestMessage.robot);
            case "quit":
                return new Quit(requestMessage.robot);
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
