package za.co.wethinkcode.robotServer.ClientCommandsTest;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.RobotWorld.ClientCommands.Repair;
import za.co.wethinkcode.robotServer.RobotWorld.Robot.Normal;
import za.co.wethinkcode.robotServer.RobotWorld.Robot.Robot;
import za.co.wethinkcode.robotServer.RobotWorld.World.World;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RepairTest {

    @Test
    void executeTestRepair() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        robots.add(test);
        test.loseShield();
        test.loseShield();
        Repair repairTest = new Repair("Bob");
        String[] args = {};
        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Done\"},\"state\":{\"position\":[0,0],\"direction\":\"NORTH\",\"shields\":3,\"shots\":3,\"status\":\"REPAIR\"}}", repairTest.execute(worldTest, args));
    }
}