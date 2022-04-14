package za.co.wethinkcode.RobotClient.AdminCommands;

public class Quit extends AdminCommands{

    public Quit(){
        super("quit");
    }
    @Override
    public String execute() {
        return "quit";
    }
}
