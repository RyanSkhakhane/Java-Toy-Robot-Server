package za.co.wethinkcode.RobotClient.ClientCommands;

public class UnsupportedCommand extends ClientCommands {
    public UnsupportedCommand(String name) {
        super(name);
    }

    @Override
    public String execute(String robotName) {
        String request = "{" +
                "\"robot\": \"HAL\"," +
                "\"command\": \"luanch\"," +
                "\"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        return request;
    }
}
