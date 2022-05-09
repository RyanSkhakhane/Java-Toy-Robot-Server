package za.co.wethinkcode.RobotClient.ClientCommands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Look extends ClientCommands{
    public Look() {
        super("look");
    }

    @Override
    public String execute(String robotName) {
        Gson gson = new Gson();
        String[] arguments = {};
        LookCommandJson lookCommandJson = new LookCommandJson(robotName, "look", arguments);
        String status = gson.toJson(lookCommandJson);
        return gson.toJson(lookCommandJson);
    }


    private class LookCommandJson{
        String robot;
        String command;
        String[] arguments;

        private LookCommandJson(String robotName, String robotCommand, String[] launchArguments){
            this.robot = robotName;
            this.command = robotCommand;
            this.arguments = launchArguments;
        }
    }
}
