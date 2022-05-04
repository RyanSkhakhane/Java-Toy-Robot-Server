package za.co.wethinkcode.RobotClient.ClientCommands;


import com.google.gson.Gson;


public class Back extends ClientCommands{


    public Back(String steps) {
        super("back", steps);
    }


    @Override
    public String execute(String robotName) {
        int steps = Integer.parseInt(getArgument());
        Integer[] arguments = {steps};

        BackCommandJson backCommandJson = new BackCommandJson(robotName, "back", arguments);

        Gson gson = new Gson();
        String json = gson.toJson(backCommandJson);

        return json;
    }


    public class BackCommandJson{
        String robot;
        String command;
        Integer[] arguments;

        public BackCommandJson(String robotName, String robotCommand, Integer[] launchArguments){
            this.robot = robotName;
            this.command = robotCommand;
            this.arguments = launchArguments;
        }
    }
}
