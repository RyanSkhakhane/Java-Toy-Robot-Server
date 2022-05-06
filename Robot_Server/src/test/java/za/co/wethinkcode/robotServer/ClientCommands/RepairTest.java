package za.co.wethinkcode.robotServer.ClientCommands;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RepairTest {

    @Test
    void executeTestRepair() throws IOException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Robot(worldTest, "Bob", "normal");
        robots.add(test);
        test.loseShield();
        test.loseShield();
        Repair repairTest = new Repair("Bob");
        String[] args = {};
        assertEquals("{\n" +
                "  \"result\": \"OK\",\n" +
                "  \"data\": {\n" +
                "    \"message\": \"Done\"\n" +
                "  },\n" +
                "  \"state\": {\n" +
                "    \"position\": [\n" +
                "      0,\n" +
                "      0\n" +
                "    ],\n" +
                "    \"direction\": \"NORTH\",\n" +
                "    \"shields\": 3,\n" +
                "    \"shots\": 3,\n" +
                "    \"status\": \"REPAIR\"\n" +
                "  }\n" +
                "}", repairTest.execute(worldTest, args));
    }
}