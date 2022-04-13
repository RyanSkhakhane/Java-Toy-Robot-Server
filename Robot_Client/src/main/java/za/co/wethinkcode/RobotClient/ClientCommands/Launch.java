package za.co.wethinkcode.RobotClient.ClientCommands;

public class Launch extends ClientCommands{

    public Launch(String make, String name) {
        super("launch",make,name);
    }

    @Override
    public String execute() {
        String commandName = getName();
        String robotMake = getArgument();
        String robotName = getArgument2();

        return commandName + " " + robotMake + " " + robotName;
    }
}
