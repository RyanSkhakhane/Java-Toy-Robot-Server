package za.co.wethinkcode.robotServer.ClientCommands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.World;

public class State extends ClientCommands{

    public State(String robotName) {
        super("state",robotName);
    }


    @Override
    public String execute(ClientHandler clienthandler, World world, String[] arguments) {
        StateResponseJSon stateResponseJSon;
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        for(Robot robot : clienthandler.getRobots()){
            if(robot.getRobotName().equals(getArgument())){
                int[] position = {robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()};
                stateResponseJSon = new StateResponseJSon(position, robot.getCurrentDirection().toString(),
                        robot.getShields(),robot.getShots(),robot.getStatus());
                return gson.toJson(stateResponseJSon);
            }
        }
        return "Robot not found!!";
    }




    public class StateResponseJSon{
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
