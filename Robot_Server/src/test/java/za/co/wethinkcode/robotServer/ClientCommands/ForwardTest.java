package za.co.wethinkcode.robotServer.ClientCommands;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.Direction;
import za.co.wethinkcode.robotServer.Position;
import za.co.wethinkcode.robotServer.Robot.Normal;
import za.co.wethinkcode.robotServer.Robot.Robot;
import za.co.wethinkcode.robotServer.World.SquareObstacle;
import za.co.wethinkcode.robotServer.World.World;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ForwardTest {


    @Test
    void executeForwardValid() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        robots.add(test);
        Forward testForward = new Forward("Bob", 5);
        String[] args = {};
        assertEquals("{\n" +
                "  \"result\": \"OK\",\n" +
                "  \"data\": {\n" +
                "    \"message\": \"Done\"\n" +
                "  },\n" +
                "  \"state\": {\n" +
                "    \"position\": [\n" +
                "      0,\n" +
                "      5\n" +
                "    ],\n" +
                "    \"direction\": \"NORTH\",\n" +
                "    \"shields\": 3,\n" +
                "    \"shots\": 3,\n" +
                "    \"status\": \"normal\"\n" +
                "  }\n" +
                "}", testForward.execute(worldTest, args));
    }

    @Test
    void executeForwardInvalidEdge() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        robots.add(test);
        Forward testForward = new Forward("Bob", 100);
        String[] args = {};
        assertEquals("{\n" +
                "  \"result\": \"OK\",\n" +
                "  \"data\": {\n" +
                "    \"message\": \"Edge\"\n" +
                "  },\n" +
                "  \"state\": {\n" +
                "    \"position\": [\n" +
                "      0,\n" +
                "      0\n" +
                "    ],\n" +
                "    \"direction\": \"NORTH\",\n" +
                "    \"shields\": 3,\n" +
                "    \"shots\": 3,\n" +
                "    \"status\": \"normal\"\n" +
                "  }\n" +
                "}", testForward.execute(worldTest, args));
    }

    @Test
    void executeForwardInvalidRobot() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        robots.add(test);
        Robot test2 = new Normal(worldTest, "Bill", "normal");
        test2.setCurrentPosition(new Position(0 , 2));
        robots.add(test2);
        Forward testForward = new Forward("Bob", 5);
        String[] args = {};
        assertEquals("{\n" +
                "  \"result\": \"OK\",\n" +
                "  \"data\": {\n" +
                "    \"message\": \"Obstructed\"\n" +
                "  },\n" +
                "  \"state\": {\n" +
                "    \"position\": [\n" +
                "      0,\n" +
                "      0\n" +
                "    ],\n" +
                "    \"direction\": \"NORTH\",\n" +
                "    \"shields\": 3,\n" +
                "    \"shots\": 3,\n" +
                "    \"status\": \"normal\"\n" +
                "  }\n" +
                "}", testForward.execute(worldTest, args));
    }

    @Test
    void executeForwardInvalidObstacle() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        robots.add(test);
        SquareObstacle squareObstacle = new SquareObstacle(0, 2);
        SquareObstacle[] newList = {squareObstacle};
        worldTest.setObstacles(newList);
        Forward testForward = new Forward("Bob", 5);
        String[] args = {};
        assertEquals("{\n" +
                "  \"result\": \"OK\",\n" +
                "  \"data\": {\n" +
                "    \"message\": \"Obstructed\"\n" +
                "  },\n" +
                "  \"state\": {\n" +
                "    \"position\": [\n" +
                "      0,\n" +
                "      0\n" +
                "    ],\n" +
                "    \"direction\": \"NORTH\",\n" +
                "    \"shields\": 3,\n" +
                "    \"shots\": 3,\n" +
                "    \"status\": \"normal\"\n" +
                "  }\n" +
                "}", testForward.execute(worldTest, args));
    }

    @Test
    void executeBackInvalidRobotXAxis() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        robots.add(test);
        test.setCurrentDirection(Direction.EAST);
        Robot test2 = new Normal(worldTest, "Bill", "normal");
        test2.setCurrentPosition(new Position(2 , 0));
        robots.add(test2);
        Forward testForward = new Forward("Bob", 5);
        String[] args = {};
        assertEquals("{\n" +
                "  \"result\": \"OK\",\n" +
                "  \"data\": {\n" +
                "    \"message\": \"Obstructed\"\n" +
                "  },\n" +
                "  \"state\": {\n" +
                "    \"position\": [\n" +
                "      0,\n" +
                "      0\n" +
                "    ],\n" +
                "    \"direction\": \"EAST\",\n" +
                "    \"shields\": 3,\n" +
                "    \"shots\": 3,\n" +
                "    \"status\": \"normal\"\n" +
                "  }\n" +
                "}", testForward.execute(worldTest, args));
    }

    @Test
    void executeBackInvalidObstacleXAxis() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        test.setCurrentDirection(Direction.EAST);
        robots.add(test);
        SquareObstacle squareObstacle = new SquareObstacle(2, 0);
        SquareObstacle[] newList = {squareObstacle};
        worldTest.setObstacles(newList);
        Forward testForward = new Forward("Bob", 5);
        String[] args = {};
        assertEquals("{\n" +
                "  \"result\": \"OK\",\n" +
                "  \"data\": {\n" +
                "    \"message\": \"Obstructed\"\n" +
                "  },\n" +
                "  \"state\": {\n" +
                "    \"position\": [\n" +
                "      0,\n" +
                "      0\n" +
                "    ],\n" +
                "    \"direction\": \"EAST\",\n" +
                "    \"shields\": 3,\n" +
                "    \"shots\": 3,\n" +
                "    \"status\": \"normal\"\n" +
                "  }\n" +
                "}", testForward.execute(worldTest, args));
    }

    @Test
    void executeBackInvalidEdgeXAxis() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        test.setCurrentDirection(Direction.EAST);
        robots.add(test);
        Forward testForward = new Forward("Bob", 100);
        String[] args = {};
        assertEquals("{\n" +
                "  \"result\": \"OK\",\n" +
                "  \"data\": {\n" +
                "    \"message\": \"Edge\"\n" +
                "  },\n" +
                "  \"state\": {\n" +
                "    \"position\": [\n" +
                "      0,\n" +
                "      0\n" +
                "    ],\n" +
                "    \"direction\": \"EAST\",\n" +
                "    \"shields\": 3,\n" +
                "    \"shots\": 3,\n" +
                "    \"status\": \"normal\"\n" +
                "  }\n" +
                "}", testForward.execute(worldTest, args));
    }

    @Test
    void executeBackValidEdgeXAxis() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        test.setCurrentDirection(Direction.EAST);
        robots.add(test);
        Forward testForward = new Forward("Bob", 5);
        String[] args = {};
        assertEquals("{\n" +
                "  \"result\": \"OK\",\n" +
                "  \"data\": {\n" +
                "    \"message\": \"Done\"\n" +
                "  },\n" +
                "  \"state\": {\n" +
                "    \"position\": [\n" +
                "      5,\n" +
                "      0\n" +
                "    ],\n" +
                "    \"direction\": \"EAST\",\n" +
                "    \"shields\": 3,\n" +
                "    \"shots\": 3,\n" +
                "    \"status\": \"normal\"\n" +
                "  }\n" +
                "}", testForward.execute(worldTest, args));
    }
}