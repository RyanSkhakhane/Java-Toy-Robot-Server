package za.co.wethinkcode.robotServer.World.command;

import za.co.wethinkcode.robotServer.Robot;

public class RightCommand {
    public void execute(Robot robot){
        robot.updateDirection(true);
    }
}
