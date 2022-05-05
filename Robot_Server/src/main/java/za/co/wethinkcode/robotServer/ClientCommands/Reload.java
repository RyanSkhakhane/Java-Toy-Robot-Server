package za.co.wethinkcode.robotServer.ClientCommands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.World;

public class Reload extends ClientCommands{

    public Reload(String robotName){
        super("reload", robotName);
    }


    @Override
    public String execute(ClientHandler clienthandler, World world, String[] arguments) {

        StateJson stateJson;
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        for (Robot robot : clienthandler.getRobots()) {
            if (robot.getRobotName().equals(getArgument())) {
                robot.reloadShots();
                int[] position = {robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()};
                stateJson = new StateJson(position, robot.getCurrentDirection().toString(), robot.getShields(), robot.getShots(), "RELOAD");
                DataJson dataJson = new DataJson("Done");
                ReloadJson reloadJson = new ReloadJson("OK", dataJson, stateJson);
                return gson.toJson(reloadJson);
            }
        }
        return "Error";
    }

    public class ReloadJson{
        String result;
        DataJson data;
        StateJson state;

        public ReloadJson(String result, DataJson data, StateJson state){
            this.result = result;
            this.data = data;
            this.state = state;

        }

    }
    public class DataJson{
        String message;

        public DataJson(String message){
            this.message = message;
        }
    }

    public class StateJson{
        int[] position;
        String direction;
        int shields;
        int shots;
        String status;

        public StateJson(int[] position, String direction, int shields, int shots, String status){
            this.position = position;
            this.direction = direction;
            this.shields = shields;
            this.shots = shots;
            this.status = status;
        }
    }
}
