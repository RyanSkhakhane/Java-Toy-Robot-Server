package za.co.wethinkcode.RobotClient.AdminCommands;

public class Dump extends AdminCommands{

    public Dump(){
        super("Dump");
    }
    @Override
    public String execute() {
        return "dump";
    }
}
