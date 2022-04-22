package za.co.wethinkcode.robotServer;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.World.World;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {


    @Test
    void getRobotState() throws FileNotFoundException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Robot(worldTest, "Bob", "normal");
        assertEquals(test.getRobotState(), "Position [0,0] \n" +
                "Direction [NORTH]");
        Robot test2 = new Robot(worldTest, "Ray", "normal");
        test2.setRobotPosition(10, 10);
        assertEquals(test2.getRobotState(), "Position [10,10] \n" +
                "Direction [NORTH]");
    }
}