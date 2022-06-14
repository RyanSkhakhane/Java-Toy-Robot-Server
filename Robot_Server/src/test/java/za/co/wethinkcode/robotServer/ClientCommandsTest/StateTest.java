package za.co.wethinkcode.robotServer.ClientCommandsTest;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.Robot.Normal;
import za.co.wethinkcode.robotServer.Robot.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    @Test
    void executeTest() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        robots.add(test);
        State testState = new State("Bob");
        String[] args = {};
        assertEquals("{\"result\":\"OK\",\"data\":{\"visibility\":1,\"objects\":[],\"position\":[0,0]},\"state\":{\"position\":[0,0],\"direction\":\"NORTH\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testState.execute(worldTest, args));
    }

}