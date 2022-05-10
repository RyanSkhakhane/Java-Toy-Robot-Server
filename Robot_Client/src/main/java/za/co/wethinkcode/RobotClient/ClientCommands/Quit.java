package za.co.wethinkcode.RobotClient.ClientCommands;

import com.google.gson.Gson;

public class Quit extends ClientCommands{


    public Quit() {
        super("quit");
    }

    @Override
    public String execute(String robotName) {
        Gson gson = new Gson();
        String[] arguments = {};
        QuitCommandJson quitCommandJson = new QuitCommandJson(robotName,"quit",arguments);
        return gson.toJson(quitCommandJson);
    }
    private class QuitCommandJson{
        String robot;
        String command;
        String[] arguments;

        private QuitCommandJson(String robotName, String robotCommand, String[] launchArguments){
            this.robot = robotName;
            this.command = robotCommand;
            this.arguments = launchArguments;
        }
    }
}

