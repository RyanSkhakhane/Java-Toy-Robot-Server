package za.co.wethinkcode.robotServer;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.World.SquareObstacle;
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
    @Test
    void updateResponse() throws FileNotFoundException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Robot(worldTest, "Bob", "normal");
        SquareObstacle[] obstacles = {new SquareObstacle(0,2)};
        worldTest.setOBSTACLES(obstacles);
        worldTest.setTOP_LEFT(new Position(-10, 10));
        worldTest.setBOTTOM_RIGHT(new Position(10, -10));
        assertEquals(Robot.UpdateResponse.FAILED_OBSTRUCTED_OBSTACLE, test.updatePosition(5));
        assertEquals(Robot.UpdateResponse.SUCCESS, test.updatePosition(1));
        test.setCurrentDirection(Direction.SOUTH);
        assertEquals(Robot.UpdateResponse.SUCCESS, test.updatePosition(5));
        assertEquals(Robot.UpdateResponse.FAILED_OUTSIDE_WORLD, test.updatePosition(600));
    }
    @Test
    void updateDirection() throws FileNotFoundException{
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Robot(worldTest, "Bob", "normal");
        assertEquals(Direction.NORTH, test.getCurrentDirection());
        test.updateDirection(true);
        assertEquals(Direction.EAST, test.getCurrentDirection());
        test.updateDirection(false);
        assertEquals(Direction.NORTH, test.getCurrentDirection());

    }
}