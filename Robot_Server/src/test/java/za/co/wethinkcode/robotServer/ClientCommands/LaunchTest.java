package za.co.wethinkcode.robotServer.ClientCommands;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class LaunchTest {

        @Test
        void executeTest() throws FileNotFoundException {
            ArrayList<Robot> robots = new ArrayList<>();
            World worldTest = new World(robots);
            Launch launchTest = new Launch("normal", "bob");
            String[] args = {};
            assertEquals("{\n" +
                    "  \"result\": \"OK\",\n" +
                    "  \"data\": {\n" +
                    "    \"position\": [\n" +
                    "      0,\n" +
                    "      0\n" +
                    "    ],\n" +
                    "    \"visibility\": 0,\n" +
                    "    \"reload\": 0,\n" +
                    "    \"repair\": 0,\n" +
                    "    \"shields\": 0\n" +
                    "  },\n" +
                    "  \"state\": {\n" +
                    "    \"position\": [\n" +
                    "      0,\n" +
                    "      0\n" +
                    "    ],\n" +
                    "    \"direction\": \"NORTH\",\n" +
                    "    \"shields\": 0,\n" +
                    "    \"shots\": 3,\n" +
                    "    \"status\": \"normal\"\n" +
                    "  }\n" +
                    "}", launchTest.execute(worldTest,args ));
        }
    }