package za.co.wethinkcode.RobotClient.ClientCommands;

import com.google.gson.Gson;

public class Turn extends ClientCommands{
    public Turn(String turnDirection){
        super("turn",turnDirection);
    }

    @Override
    public String execute(String robotName) {
        String[] arguments = {getArgument()};
        TurnCommandJson turnCommandJson = new TurnCommandJson(robotName,"turn",arguments);

        Gson gson = new Gson();
        String json = gson.toJson(turnCommandJson);

        return json;

    }
public class TurnCommandJson{
        String robot;
        String command;
        String[] arguments;

        public TurnCommandJson(String robot,String command,String[]arguments){
            this.robot = robot;
            this.command = command;
            this.arguments = arguments;
        }
}
}
