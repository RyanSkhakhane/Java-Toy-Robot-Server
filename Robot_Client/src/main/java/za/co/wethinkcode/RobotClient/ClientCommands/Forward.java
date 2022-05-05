package za.co.wethinkcode.RobotClient.ClientCommands;


import com.google.gson.Gson;


public class Forward extends ClientCommands{


    public Forward(String steps) {
        super("forward", steps);
    }


    @Override
    public String execute(String robotName) {
        int steps = Integer.parseInt(getArgument());
        Integer[] arguments = {steps};
        ForwardCommandJson forwardCommandJson = new ForwardCommandJson(robotName, "forward", arguments);

        Gson gson = new Gson();
        String json = gson.toJson(forwardCommandJson);

        return json;
    }


    public class ForwardCommandJson{
        String robot;
        String command;
        Integer[] arguments;

        public ForwardCommandJson(String robotName, String robotCommand, Integer[] launchArguments){
            this.robot = robotName;
            this.command = robotCommand;
            this.arguments = launchArguments;
        }
    }
}
