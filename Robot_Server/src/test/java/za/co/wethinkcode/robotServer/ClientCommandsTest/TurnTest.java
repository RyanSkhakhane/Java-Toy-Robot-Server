package za.co.wethinkcode.robotServer.ClientCommandsTest;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.Robot.Normal;
import za.co.wethinkcode.robotServer.Robot.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TurnTest {

    @Test
    void executeTestTurnRight() throws IOException {

        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        robots.add(test);
        Turn turnTest = new Turn("Bob", "right");
        String[] args = {};
        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Done\"},\"state\":{\"direction\":\"EAST\"}}", turnTest.execute(worldTest, args));
    }

    @Test
    void executeTestTurnRightTwice() throws IOException {

        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        robots.add(test);
        Turn turnTest = new Turn("Bob", "right");
        String[] args = {};
        turnTest.execute(worldTest, args);
        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Done\"},\"state\":{\"direction\":\"SOUTH\"}}", turnTest.execute(worldTest, args));
    }

    @Test
    void executeTestTurnRightFourTimes() throws IOException {

        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        robots.add(test);
        Turn turnTest = new Turn("Bob", "right");
        String[] args = {};
        turnTest.execute(worldTest, args);
        turnTest.execute(worldTest, args);
        turnTest.execute(worldTest, args);
        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Done\"},\"state\":{\"direction\":\"NORTH\"}}", turnTest.execute(worldTest, args));
    }

    @Test
    void executeTestTurnLeft() throws IOException {

        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        robots.add(test);
        Turn turnTest = new Turn("Bob", "left");
        String[] args = {};
        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Done\"},\"state\":{\"direction\":\"WEST\"}}", turnTest.execute(worldTest, args));
    }

    @Test
    void executeTestTurnLeftTwice() throws IOException {

        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        robots.add(test);
        Turn turnTest = new Turn("Bob", "left");
        String[] args = {};
        turnTest.execute(worldTest, args);
        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Done\"},\"state\":{\"direction\":\"SOUTH\"}}", turnTest.execute(worldTest, args));
    }

    @Test
    void executeTestTurnLeftFourTimes() throws IOException {

        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        robots.add(test);
        Turn turnTest = new Turn("Bob", "left");
        String[] args = {};
        turnTest.execute(worldTest,args);
        turnTest.execute(worldTest,args);
        turnTest.execute(worldTest,args);
        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Done\"},\"state\":{\"direction\":\"NORTH\"}}", turnTest.execute(worldTest, args));
    }
}