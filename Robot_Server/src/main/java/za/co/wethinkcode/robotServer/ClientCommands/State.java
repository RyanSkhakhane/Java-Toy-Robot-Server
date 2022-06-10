package za.co.wethinkcode.robotServer.ClientCommands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import za.co.wethinkcode.robotServer.Position;
import za.co.wethinkcode.robotServer.Robot.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.util.ArrayList;
import java.util.List;

public class State extends ClientCommands{

    public State(String robotName) {
        super("state",robotName);
    }

    @Override
    public String execute(World world, String[] arguments) {
        StateResponseJSon stateResponseJSon;
        Gson gson = new GsonBuilder()
//                .setPrettyPrinting()
                .create();
        for(Robot robot : world.getRobots()){
            if(robot.getRobotName().equals(getArgument())){
                int[] position = {robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()};
                stateResponseJSon = new StateResponseJSon(position, robot.getCurrentDirection().toString(),
                        robot.getShields(),robot.getShots(),robot.getStatus());

                List objectList = new ArrayList();
                StateDataJson dataJson = new StateDataJson(1, position, objectList);
                FullStateResponse fullStateResp = new FullStateResponse("OK", dataJson, stateResponseJSon);

                System.out.println(stateResponseJSon);
                System.out.println(gson.toJson(stateResponseJSon));
                return gson.toJson(fullStateResp);
            }
        }
        return "Robot not found!!";
    }

    public class FullStateResponse{
        String result;
        StateDataJson data;
        StateResponseJSon state;

        public FullStateResponse(String result, StateDataJson data, StateResponseJSon state) {
            this.result = result;
            this.data = data;
            this. state = state;
        }
    }

    public static class StateDataJson {
        int visibility;
        List objects;
        int[] position;

        public StateDataJson(int visibility, int[] position, List objects) {
            this.visibility = visibility;
            this.position = position;
            this.objects = objects;

        }
    }
    public static class StateResponseJSon{
        int[] position;
        String direction;
        int shields;
        int shots;
        String status;

        public StateResponseJSon(int[] position, String direction, int shields, int shots, String status){
            this.position = position;
            this.direction = direction;
            this.shields = shields;
            this.shots = shots;
            this.status = status;
        }
    }
}

