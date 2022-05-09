package za.co.wethinkcode.robotServer.ClientCommands;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.Direction;
import za.co.wethinkcode.robotServer.Position;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.SquareObstacle;
import za.co.wethinkcode.robotServer.World.World;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BackTest {


    @Test
    void executeBackValid() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Robot(worldTest, "Bob", "normal");
        robots.add(test);
        Back testBack = new Back("Bob", 5);
        String[] args = {};
        assertEquals("{\n" +
                "  \"result\": \"OK\",\n" +
                "  \"data\": {\n" +
                "    \"message\": \"Done\"\n" +
                "  },\n" +
                "  \"state\": {\n" +
                "    \"position\": [\n" +
                "      0,\n" +
                "      -5\n" +
                "    ],\n" +
                "    \"direction\": \"NORTH\",\n" +
                "    \"shields\": 3,\n" +
                "    \"shots\": 3,\n" +
                "    \"status\": \"normal\"\n" +
                "  }\n" +
                "}", testBack.execute(worldTest, args));
    }

    @Test
    void executeBackInvalidEdge() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Robot(worldTest, "Bob", "normal");
        robots.add(test);
        Back testBack = new Back("Bob", 100);
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
                "}", testBack.execute(worldTest, args));
    }

    @Test
    void executeBackInvalidRobot() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Robot(worldTest, "Bob", "normal");
        robots.add(test);
        Robot test2 = new Robot(worldTest, "Bill", "normal");
        test2.setCurrentPosition(new Position(0 , -2));
        robots.add(test2);
        Back testBack = new Back("Bob", 5);
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
                "}", testBack.execute(worldTest, args));
    }

    @Test
    void executeBackInvalidObstacle() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Robot(worldTest, "Bob", "normal");
        robots.add(test);
        SquareObstacle squareObstacle = new SquareObstacle(0, -2);
        SquareObstacle[] newList = {squareObstacle};
        worldTest.setObstacles(newList);
        Back testBack = new Back("Bob", 5);
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
                "}", testBack.execute(worldTest, args));
    }

    @Test
    void executeBackInvalidRobotXAxis() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Robot(worldTest, "Bob", "normal");
        robots.add(test);
        test.setCurrentDirection(Direction.EAST);
        Robot test2 = new Robot(worldTest, "Bill", "normal");
        test2.setCurrentPosition(new Position(-2 , 0));
        robots.add(test2);
        Back testBack = new Back("Bob", 5);
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
                "}", testBack.execute(worldTest, args));
    }

    @Test
    void executeBackInvalidObstacleXAxis() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Robot(worldTest, "Bob", "normal");
        test.setCurrentDirection(Direction.EAST);
        robots.add(test);
        SquareObstacle squareObstacle = new SquareObstacle(-2, 0);
        SquareObstacle[] newList = {squareObstacle};
        worldTest.setObstacles(newList);
        Back testBack = new Back("Bob", 5);
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
                "}", testBack.execute(worldTest, args));
    }

    @Test
    void executeBackInvalidEdgeXAxis() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Robot(worldTest, "Bob", "normal");
        test.setCurrentDirection(Direction.EAST);
        robots.add(test);
        Back testBack = new Back("Bob", 100);
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
                "}", testBack.execute(worldTest, args));
    }

    @Test
    void executeBackValidEdgeXAxis() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Robot(worldTest, "Bob", "normal");
        test.setCurrentDirection(Direction.EAST);
        robots.add(test);
        Back testBack = new Back("Bob", 5);
        String[] args = {};
        assertEquals("{\n" +
                "  \"result\": \"OK\",\n" +
                "  \"data\": {\n" +
                "    \"message\": \"Done\"\n" +
                "  },\n" +
                "  \"state\": {\n" +
                "    \"position\": [\n" +
                "      -5,\n" +
                "      0\n" +
                "    ],\n" +
                "    \"direction\": \"EAST\",\n" +
                "    \"shields\": 3,\n" +
                "    \"shots\": 3,\n" +
                "    \"status\": \"normal\"\n" +
                "  }\n" +
                "}", testBack.execute(worldTest, args));
    }
}