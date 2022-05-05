package za.co.wethinkcode.robotServer.ClientCommands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.awt.*;

public class Turn extends ClientCommands{
    String turnDirection;
    public Turn(String robotName, String turnDirection){
        super("turn",robotName);
        this.turnDirection = turnDirection;
    }
    @Override
    public String execute(ClientHandler clienthandler, World world, String[] arguments) {
        StateResponseJson stateResponseJson = null;
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        for (Robot robot : clienthandler.getRobots()) {
            if (robot.getRobotName().equals(getArgument())){
                switch(turnDirection){
                    case "left":
                        robot.updateDirection(false);
                        break;
                    case "right":
                        robot.updateDirection(true);
                        break;

                }
                stateResponseJson = new StateResponseJson(robot.getCurrentDirection().toString());
            }
        }
        DataResponseJson dataResponseJson = new DataResponseJson("Done");
        TurnResponseJson turnResponseJson = new TurnResponseJson("OK",dataResponseJson,stateResponseJson);
        String json = gson.toJson(turnResponseJson);
        return json;
    }
    public class TurnResponseJson{
        String result;
        DataResponseJson data;
        StateResponseJson state;
        public TurnResponseJson(String result, DataResponseJson data, StateResponseJson state){
            this.result = result;
            this.data = data;
            this.state = state;

        }
    }

    public class DataResponseJson{
        String message;
        public DataResponseJson(String message){
            this.message = message;
        }

    }
    public class StateResponseJson{
        String direction;
        public StateResponseJson(String direction){
            this.direction = direction;
        }
    }

}
