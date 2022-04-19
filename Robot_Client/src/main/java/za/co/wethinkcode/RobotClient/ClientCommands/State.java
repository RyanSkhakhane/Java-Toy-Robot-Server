package za.co.wethinkcode.RobotClient.ClientCommands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class State extends ClientCommands{
    public State() {
        super("state");
    }

    @Override
    public String execute(String robotName) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String[] arguments = {};
        StateCommandJson stateCommandJson = new StateCommandJson(robotName, "state", arguments);
        String status = gson.toJson(stateCommandJson);
        System.out.println(status);
        return gson.toJson(stateCommandJson);
    }


    private class StateCommandJson{
        String robot;
        String command;
        String[] arguments;

        private StateCommandJson(String robotName, String robotCommand, String[] launchArguments){
            this.robot = robotName;
            this.command = robotCommand;
            this.arguments = launchArguments;
        }
    }
}
