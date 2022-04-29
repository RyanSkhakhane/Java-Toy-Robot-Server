package za.co.wethinkcode.RobotClient.ClientCommands;

import com.google.gson.Gson;


public class State extends ClientCommands {
    public State() {
        super("state");
    }

    public String execute(String robotName) {
        Gson gson = new Gson();
        String[] arguments = {};
        StateCommandJson stateCommandJson = new StateCommandJson(robotName, "state", arguments);
        return gson.toJson(stateCommandJson);
    }

    class StateCommandJson {
        String robot;
        String command;
        String[] arguments;

        private StateCommandJson(String robotName, String robotCommand, String[] stateArguments) {
            this.robot = robotName;
            this.command = robotCommand;
            this.arguments = stateArguments;
        }
    }
}

