package za.co.wethinkcode.robotServer.ClientCommands;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.Position;
import za.co.wethinkcode.robotServer.Robot;
import za.co.wethinkcode.robotServer.World.SquareObstacle;
import za.co.wethinkcode.robotServer.World.World;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LookTest {
    @Test
    void lookTest() throws FileNotFoundException {
        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        SquareObstacle[] obstacles = {new SquareObstacle(2,2), new SquareObstacle(-3,-4)};
        worldTest.setObstacles(obstacles);
        Robot test = new Robot(worldTest, "Bob", "normal");
        test.setCurrentPosition(new Position(1,1));
        robots.add(test);
        Look look = new Look("Bob");
        String[] args = {};
        look.execute(worldTest, args);
    }
}