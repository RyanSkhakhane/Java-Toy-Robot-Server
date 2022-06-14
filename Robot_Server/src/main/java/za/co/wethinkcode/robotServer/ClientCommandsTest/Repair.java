package za.co.wethinkcode.robotServer.ClientCommandsTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import za.co.wethinkcode.robotServer.Robot.Robot;
import za.co.wethinkcode.robotServer.World.World;

public class Repair extends ClientCommands{

    public Repair(String robotName){
        super("reload", robotName);
    }

    @Override
    public String execute(World world, String[] arguments) {

        Repair.StateJson stateJson;
        Gson gson = new GsonBuilder()
//                .setPrettyPrinting()
                .create();
        for (Robot robot : world.getRobots()) {
            if (robot.getRobotName().equals(getArgument())) {
                robot.repairShields();
                int[] position = {robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()};
                stateJson = new Repair.StateJson(position, robot.getCurrentDirection().toString(), robot.getShields(), robot.getShots(), "REPAIR");
                Repair.DataJson dataJson = new Repair.DataJson("Done");
                Repair.ReloadJson reloadJson = new Repair.ReloadJson("OK", dataJson, stateJson);
                return gson.toJson(reloadJson);
            }
        }
        return "Error";
    }

    public static class ReloadJson{
        String result;
        Repair.DataJson data;
        Repair.StateJson state;

        public ReloadJson(String result, Repair.DataJson data, Repair.StateJson state){
            this.result = result;
            this.data = data;
            this.state = state;

        }

    }
    public static class DataJson{
        String message;

        public DataJson(String message){
            this.message = message;
        }
    }

    public static class StateJson{
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
