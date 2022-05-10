package za.co.wethinkcode.robotServer.ClientCommands;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.Position;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.SquareObstacle;
import za.co.wethinkcode.robotServer.World.World;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuitTest {


    @Test
    void QuitTest() throws FileNotFoundException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        worldTest.setVISIBILITY(7);
        SquareObstacle[] obstacles = {new SquareObstacle(1,3), new SquareObstacle(2,1)};
        worldTest.setObstacles(obstacles);
        Robot test = new Robot(worldTest, "Bob", "normal");
        test.setCurrentPosition(new Position(1,1));
        robots.add(test);
        Robot test2 = new Robot(worldTest, "Bill", "normal");
        robots.add(test2);
        test2.setCurrentPosition(new Position(1,2));
        Robot test3 = new Robot(worldTest,"Tim", "normal");
        test3.setCurrentPosition(new Position(1, -2));
        robots.add(test3);
        Quit quit = new Quit("Bill");
        String[] args = {};
        quit.execute(worldTest,args);
        Look look = new Look("Bob");
        assertEquals("{\n" +
                "  \"result\": \"ok\",\n" +
                "  \"data\": {\n" +
                "    \"objects\": [\n" +
                "      {\n" +
                "        \"direction\": \"NORTH\",\n" +
                "        \"objectType\": \"ROBOT\",\n" +
                "        \"steps\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"direction\": \"EAST\",\n" +
                "        \"objectType\": \"OBSTACLE\",\n" +
                "        \"steps\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"direction\": \"SOUTH\",\n" +
                "        \"objectType\": \"ROBOT\",\n" +
                "        \"steps\": 3\n" +
                "      },\n" +
                "      {\n" +
                "        \"direction\": \"WEST\",\n" +
                "        \"objectType\": \"EDGE\",\n" +
                "        \"steps\": 6\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"state\": {\n" +
                "    \"position\": [\n" +
                "      1,\n" +
                "      1\n" +
                "    ],\n" +
                "    \"direction\": \"NORTH\",\n" +
                "    \"shields\": 3,\n" +
                "    \"shots\": 3,\n" +
                "    \"status\": \"normal\"\n" +
                "  }\n" +
                "}",look.execute(worldTest, args));
    }

}