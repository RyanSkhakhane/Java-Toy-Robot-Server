package za.co.wethinkcode.robotServer.ClientCommandsTest;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.Direction;
import za.co.wethinkcode.robotServer.Position;
import za.co.wethinkcode.robotServer.Robot.Normal;
import za.co.wethinkcode.robotServer.Robot.Robot;
import za.co.wethinkcode.robotServer.World.SquareObstacle;
import za.co.wethinkcode.robotServer.World.World;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LookTest {
//    @Test
//    void lookTest() throws FileNotFoundException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        SquareObstacle[] obstacles = {new SquareObstacle(-1,2), new SquareObstacle(-3,-4)};
//        worldTest.setObstacles(obstacles);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        test.setCurrentPosition(new Position(1,1));
//        robots.add(test);
//        Look look = new Look("Bob");
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"objects\":[{\"direction\":\"NORTH\",\"objectType\":\"OBSTACLE\",\"steps\":1}]},\"state\":{\"position\":[1,1],\"direction\":\"NORTH\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}",look.execute(worldTest, args));
//    }
//
//    @Test
//    void lookTestThrough() throws FileNotFoundException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        worldTest.setVISIBILITY(3);
//        SquareObstacle[] obstacles = {new SquareObstacle(1,3), new SquareObstacle(2,1)};
//        worldTest.setObstacles(obstacles);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        test.setCurrentPosition(new Position(1,1));
//        robots.add(test);
//        Robot test2 = new Normal(worldTest, "Bill", "normal");
//        robots.add(test2);
//        test2.setCurrentPosition(new Position(1,2));
//        Look look = new Look("Bob");
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"objects\":[{\"direction\":\"NORTH\",\"objectType\":\"ROBOT\",\"steps\":1},{\"direction\":\"EAST\",\"objectType\":\"OBSTACLE\",\"steps\":1}]},\"state\":{\"position\":[1,1],\"direction\":\"NORTH\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}",look.execute(worldTest, args));
//    }
//
//    @Test
//    void lookTestAllAround() throws FileNotFoundException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        worldTest.setVISIBILITY(7);
//        SquareObstacle[] obstacles = {new SquareObstacle(1,3), new SquareObstacle(2,1)};
//        worldTest.setObstacles(obstacles);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        test.setCurrentPosition(new Position(1,1));
//        robots.add(test);
//        Robot test2 = new Normal(worldTest, "Bill", "normal");
//        robots.add(test2);
//        test2.setCurrentPosition(new Position(1,2));
//        Robot test3 = new Normal(worldTest,"Tim", "normal");
//        test3.setCurrentPosition(new Position(1, -2));
//        robots.add(test3);
//        Look look = new Look("Bob");
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"objects\":[{\"direction\":\"NORTH\",\"objectType\":\"ROBOT\",\"steps\":1},{\"direction\":\"EAST\",\"objectType\":\"OBSTACLE\",\"steps\":1},{\"direction\":\"SOUTH\",\"objectType\":\"ROBOT\",\"steps\":3},{\"direction\":\"WEST\",\"objectType\":\"EDGE\",\"steps\":6}]},\"state\":{\"position\":[1,1],\"direction\":\"NORTH\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}",look.execute(worldTest, args));
//    }
//
//    @Test
//    void lookTestForEdges() throws FileNotFoundException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        worldTest.setVISIBILITY(7);
//        SquareObstacle[] obstacles = {new SquareObstacle(1,3), new SquareObstacle(2,1)};
//        worldTest.setObstacles(obstacles);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        test.setCurrentPosition(new Position(0,0));
//        robots.add(test);
//        Look look = new Look("Bob");
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"objects\":[{\"direction\":\"NORTH\",\"objectType\":\"EDGE\",\"steps\":5},{\"direction\":\"EAST\",\"objectType\":\"EDGE\",\"steps\":5},{\"direction\":\"SOUTH\",\"objectType\":\"EDGE\",\"steps\":5},{\"direction\":\"WEST\",\"objectType\":\"EDGE\",\"steps\":5}]},\"state\":{\"position\":[0,0],\"direction\":\"NORTH\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}",look.execute(worldTest, args));
//    }
//
//    @Test
//    void lookTestAllAroundFacingEast() throws FileNotFoundException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        worldTest.setVISIBILITY(7);
//        SquareObstacle[] obstacles = {new SquareObstacle(1,3), new SquareObstacle(2,1)};
//        worldTest.setObstacles(obstacles);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        test.setCurrentPosition(new Position(1,1));
//        test.setCurrentDirection(Direction.EAST);
//        robots.add(test);
//        Robot test2 = new Normal(worldTest, "Bill", "normal");
//        robots.add(test2);
//        test2.setCurrentPosition(new Position(1,2));
//        Robot test3 = new Normal(worldTest,"Tim", "normal");
//        test3.setCurrentPosition(new Position(1, -2));
//        robots.add(test3);
//        Look look = new Look("Bob");
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"objects\":[{\"direction\":\"NORTH\",\"objectType\":\"OBSTACLE\",\"steps\":1},{\"direction\":\"EAST\",\"objectType\":\"ROBOT\",\"steps\":3},{\"direction\":\"SOUTH\",\"objectType\":\"EDGE\",\"steps\":6},{\"direction\":\"WEST\",\"objectType\":\"ROBOT\",\"steps\":1}]},\"state\":{\"position\":[1,1],\"direction\":\"EAST\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}",look.execute(worldTest, args));
//    }
}