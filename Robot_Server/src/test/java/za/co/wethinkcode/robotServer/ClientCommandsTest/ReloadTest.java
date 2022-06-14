package za.co.wethinkcode.robotServer.ClientCommandsTest;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.Robot.Normal;
import za.co.wethinkcode.robotServer.Robot.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReloadTest {


    @Test
    void executeTestReload() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        robots.add(test);
        Fire fireTest = new Fire("Bob");
        String[] args = {};
        fireTest.execute(worldTest, args);
        Reload reloadTest = new Reload("Bob");
        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Done\"},\"state\":{\"position\":[0,0],\"direction\":\"NORTH\",\"shields\":3,\"shots\":3,\"status\":\"RELOAD\"}}", reloadTest.execute(worldTest, args));
    }
}