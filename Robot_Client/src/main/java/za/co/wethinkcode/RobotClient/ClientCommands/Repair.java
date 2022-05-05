package za.co.wethinkcode.RobotClient.ClientCommands;

import com.google.gson.Gson;

public class Repair extends ClientCommands{

    public Repair() {
        super("repair");
    }

    public String execute(String robotName) {
        Gson gson = new Gson();
        String[] arguments = {};
        RepairCommandJson repairCommandJson = new RepairCommandJson(robotName, "repair", arguments);
        return gson.toJson(repairCommandJson);
    }

    class RepairCommandJson {
        String robot;
        String command;
        String[] arguments;

        private RepairCommandJson(String robotName, String robotCommand, String[] stateArguments) {
            this.robot = robotName;
            this.command = robotCommand;
            this.arguments = stateArguments;
        }
    }
}