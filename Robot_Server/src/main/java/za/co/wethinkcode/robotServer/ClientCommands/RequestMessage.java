package za.co.wethinkcode.robotServer.ClientCommands;

public class RequestMessage {
    String robot;
    String command;
    public String[] arguments;

    public RequestMessage(String robotName, String robotCommand, String[] arguments){
        this.robot = robotName;
        this.command = robotCommand;
        this.arguments = arguments;
    }

    public String getRobot() {
        return robot;
    }

    public String getCommand() {
        return command;
    }

    public String[] getArguments() {
        return arguments;
    }
}
