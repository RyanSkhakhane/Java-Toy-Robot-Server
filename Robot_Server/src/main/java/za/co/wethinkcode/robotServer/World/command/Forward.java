package za.co.wethinkcode.robotServer.World.command;

import za.co.wethinkcode.robotServer.Robot;

public class Forward {

    int steps;

    public Forward(int steps) {
        this.steps = steps;
    }

    public void execute(Robot robot) {
        robot.updatePosition(steps);
    }

}
