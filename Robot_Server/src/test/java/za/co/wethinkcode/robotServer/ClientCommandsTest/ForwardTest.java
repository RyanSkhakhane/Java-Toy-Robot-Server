package za.co.wethinkcode.robotServer.ClientCommandsTest;

class ForwardTest {

//
//    @Test
//    void executeForwardValid() throws IOException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        robots.add(test);
//        Forward testForward = new Forward("Bob", 5);
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Obstructed\"},\"state\":{\"position\":[0,0],\"direction\":\"NORTH\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testForward.execute(worldTest, args));
//    }
//
//    @Test
//    void executeForwardInvalidEdge() throws IOException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        robots.add(test);
//        Forward testForward = new Forward("Bob", 100);
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Obstructed\"},\"state\":{\"position\":[0,0],\"direction\":\"NORTH\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testForward.execute(worldTest, args));
//    }
//
//    @Test
//    void executeForwardInvalidRobot() throws IOException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        robots.add(test);
//        Robot test2 = new Normal(worldTest, "Bill", "normal");
//        test2.setCurrentPosition(new Position(0 , 2));
//        robots.add(test2);
//        Forward testForward = new Forward("Bob", 5);
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Obstructed\"},\"state\":{\"position\":[0,0],\"direction\":\"NORTH\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testForward.execute(worldTest, args));
//    }
//
//    @Test
//    void executeForwardInvalidObstacle() throws IOException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        robots.add(test);
//        SquareObstacle squareObstacle = new SquareObstacle(0, 2);
//        SquareObstacle[] newList = {squareObstacle};
//        worldTest.setObstacles(newList);
//        Forward testForward = new Forward("Bob", 5);
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Obstructed\"},\"state\":{\"position\":[0,0],\"direction\":\"NORTH\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testForward.execute(worldTest, args));
//    }
//
//    @Test
//    void executeBackInvalidRobotXAxis() throws IOException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        robots.add(test);
//        test.setCurrentDirection(Direction.EAST);
//        Robot test2 = new Normal(worldTest, "Bill", "normal");
//        test2.setCurrentPosition(new Position(2 , 0));
//        robots.add(test2);
//        Forward testForward = new Forward("Bob", 5);
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Obstructed\"},\"state\":{\"position\":[0,0],\"direction\":\"EAST\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testForward.execute(worldTest, args));
//    }
//
//    @Test
//    void executeBackInvalidObstacleXAxis() throws IOException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        test.setCurrentDirection(Direction.EAST);
//        robots.add(test);
//        SquareObstacle squareObstacle = new SquareObstacle(2, 0);
//        SquareObstacle[] newList = {squareObstacle};
//        worldTest.setObstacles(newList);
//        Forward testForward = new Forward("Bob", 5);
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Obstructed\"},\"state\":{\"position\":[0,0],\"direction\":\"EAST\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testForward.execute(worldTest, args));
//    }
//
//    @Test
//    void executeBackInvalidEdgeXAxis() throws IOException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        test.setCurrentDirection(Direction.EAST);
//        robots.add(test);
//        Forward testForward = new Forward("Bob", 100);
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Obstructed\"},\"state\":{\"position\":[0,0],\"direction\":\"EAST\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testForward.execute(worldTest, args));
//    }
//
//    @Test
//    void executeBackValidEdgeXAxis() throws IOException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        test.setCurrentDirection(Direction.EAST);
//        robots.add(test);
//        Forward testForward = new Forward("Bob", 5);
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Obstructed\"},\"state\":{\"position\":[0,0],\"direction\":\"EAST\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testForward.execute(worldTest, args));
//    }
}