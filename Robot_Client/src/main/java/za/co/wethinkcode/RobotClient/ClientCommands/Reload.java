package za.co.wethinkcode.RobotClient.ClientCommands;

import com.google.gson.Gson;

public class Reload extends ClientCommands{

    public Reload() {
        super("reload");
    }

    public String execute(String robotName) {
        Gson gson = new Gson();
        String[] arguments = {};
        ReloadCommandJson reloadCommandJson = new ReloadCommandJson(robotName, "reload", arguments);
        return gson.toJson(reloadCommandJson);
    }

    class ReloadCommandJson {
        String robot;
        String command;
        String[] arguments;

        private ReloadCommandJson(String robotName, String robotCommand, String[] stateArguments) {
            this.robot = robotName;
            this.command = robotCommand;
            this.arguments = stateArguments;
        }
    }
}
