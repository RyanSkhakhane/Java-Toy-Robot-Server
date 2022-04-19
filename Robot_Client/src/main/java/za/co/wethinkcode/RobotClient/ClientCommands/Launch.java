package za.co.wethinkcode.RobotClient.ClientCommands;

//import com.google.gson.GsonBuilder;

import com.google.gson.Gson;

import java.lang.reflect.Array;

public class Launch extends ClientCommands{

    public Launch(String make, String name) {
        super("launch",make,name);
    }

    @Override
    public String execute(String Name) {
        String commandName = getName();
        String robotMake = getArgument();
        String robotName = getArgument2();
        String[] arguments = {robotMake, "0" , "0"};
        LaunchCommandJson launchCommandJson = new LaunchCommandJson(robotName, commandName, arguments);
        Gson gson = new Gson();
        String json = gson.toJson(launchCommandJson);
        return json;
    }

    public class LaunchCommandJson{
        String robot;
        String command;
        String[] arguments;

        public LaunchCommandJson(String robotName, String robotCommand, String[] launchArguments){
            this.robot = robotName;
            this.command = robotCommand;
            this.arguments = launchArguments;
        }
    }


    public class Arguments{
        String robotKind;
        Integer maxShieldStrength;
        Integer maxShots;

        public Arguments(String robotKind, Integer maxShieldStrength, Integer maxShots){
            this.robotKind = robotKind;
            this.maxShieldStrength = maxShieldStrength;
            this.maxShots = maxShots;
        }
    }

}
