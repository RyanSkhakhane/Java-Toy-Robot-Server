package za.co.wethinkcode.robotServer.World.command;

import za.co.wethinkcode.robotServer.Robot;

public class LeftCommand {
    //initial boolean is set to right, therefore this should be false

    public void execute(Robot robot){
        robot.updateDirection(false);
    }
}
