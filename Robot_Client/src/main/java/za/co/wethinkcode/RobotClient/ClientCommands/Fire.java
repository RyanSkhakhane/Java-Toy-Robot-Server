package za.co.wethinkcode.RobotClient.ClientCommands;

import com.google.gson.Gson;

public class Fire extends ClientCommands{
    public Fire(String name) {
        super(name);
    }

    @Override
    public String execute(String robotName){
        Gson gson = new Gson();
        String[] arguments = {};
        FireCommandJson fireCommandJson = new FireCommandJson(robotName, "fire", arguments);
        String status = gson.toJson(fireCommandJson);
        System.out.println(status);
        return gson.toJson(fireCommandJson);
    }
    private class FireCommandJson{
        String robot;
        String command;
        String[] arguments;

        private FireCommandJson(String robotName, String robotCommand, String[] launchArguments){
            this.robot = robotName;
            this.command = robotCommand;
            this.arguments = launchArguments;
        }
    }
}
