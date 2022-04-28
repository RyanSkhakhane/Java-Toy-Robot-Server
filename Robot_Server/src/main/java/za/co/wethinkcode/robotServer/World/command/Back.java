package za.co.wethinkcode.robotServer.World.command;
import za.co.wethinkcode.robotServer.Robot;

import java.util.HashMap;
import java.util.Map;

public class BackCommand {

    int steps;
//    Map<String, Object> state;

    public BackCommand(int steps) {
        this.steps = steps;
//        state = new HashMap<>();
    }

    public void execute(Robot robot) {
//        state.put("position", robot.getCurrentPosition());
//        state.put("direction", robot.getCurrentDirection());

        robot.updatePosition(steps);
    }
}
