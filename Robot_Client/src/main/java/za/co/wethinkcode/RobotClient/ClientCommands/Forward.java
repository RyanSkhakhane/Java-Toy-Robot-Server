package za.co.wethinkcode.RobotClient.ClientCommands;



public class Forward extends ClientCommands{
    int steps;

    public Forward(int steps) {
        super("forward");
        this.steps = steps;
    }

    @Override
    public String execute(String robotName) {

    }


    private class ForwardCommandJson{
        String robot;
        String command;
        String[] arguments;

        private ForwardCommandJson(String robotName, String robotCommand, String[] launchArguments){
            this.robot = robotName;
            this.command = robotCommand;
            this.arguments = launchArguments;
        }
    }
}
