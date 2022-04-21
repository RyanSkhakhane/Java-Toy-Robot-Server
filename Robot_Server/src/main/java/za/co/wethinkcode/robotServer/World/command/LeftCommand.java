package za.co.wethinkcode.robotServer.World.command;

import za.co.wethinkcode.robotServer.Robot;

public class LeftCommand {

    public void execute(Robot robot){
        robot.updateDirection(false);
    }
}
