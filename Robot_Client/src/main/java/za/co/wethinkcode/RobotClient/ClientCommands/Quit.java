package za.co.wethinkcode.RobotClient.ClientCommands;

public class Quit extends ClientCommands{
    public Quit(String name) {
        super("quit",name);
    }

    @Override
    public String execute(String robotName) {
        System.out.println("System shutting down");
        System.exit(0);
        return robotName;
    }
}
