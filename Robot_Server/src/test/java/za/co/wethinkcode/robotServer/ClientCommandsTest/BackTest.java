package za.co.wethinkcode.robotServer.ClientCommandsTest;

class BackTest {


//    @Test
//    void executeBackValid() throws IOException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        robots.add(test);
//        Back testBack = new Back("Bob", 5);
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Done\"},\"state\":{\"position\":[0,-5],\"direction\":\"NORTH\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testBack.execute(worldTest, args));
//    }
//
//    @Test
//    void executeBackInvalidEdge() throws IOException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        robots.add(test);
//        Back testBack = new Back("Bob", 100);
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Edge\"},\"state\":{\"position\":[0,0],\"direction\":\"NORTH\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testBack.execute(worldTest, args));
//    }
//
//    @Test
//    void executeBackInvalidRobot() throws IOException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        robots.add(test);
//        Robot test2 = new Normal(worldTest, "Bill", "normal");
//        test2.setCurrentPosition(new Position(0 , -2));
//        robots.add(test2);
//        Back testBack = new Back("Bob", 5);
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Obstructed\"},\"state\":{\"position\":[0,0],\"direction\":\"NORTH\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testBack.execute(worldTest, args));
//    }
//
//    @Test
//    void executeBackInvalidObstacle() throws IOException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        robots.add(test);
//        SquareObstacle squareObstacle = new SquareObstacle(0, -2);
//        SquareObstacle[] newList = {squareObstacle};
//        worldTest.setObstacles(newList);
//        Back testBack = new Back("Bob", 5);
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Obstructed\"},\"state\":{\"position\":[0,0],\"direction\":\"NORTH\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testBack.execute(worldTest, args));
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
//        test2.setCurrentPosition(new Position(-2 , 0));
//        robots.add(test2);
//        Back testBack = new Back("Bob", 5);
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Obstructed\"},\"state\":{\"position\":[0,0],\"direction\":\"EAST\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testBack.execute(worldTest, args));
//    }
//
//    @Test
//    void executeBackInvalidObstacleXAxis() throws IOException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        test.setCurrentDirection(Direction.EAST);
//        robots.add(test);
//        SquareObstacle squareObstacle = new SquareObstacle(-2, 0);
//        SquareObstacle[] newList = {squareObstacle};
//        worldTest.setObstacles(newList);
//        Back testBack = new Back("Bob", 5);
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Obstructed\"},\"state\":{\"position\":[0,0],\"direction\":\"EAST\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testBack.execute(worldTest, args));
//    }
//
//    @Test
//    void executeBackInvalidEdgeXAxis() throws IOException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        test.setCurrentDirection(Direction.EAST);
//        robots.add(test);
//        Back testBack = new Back("Bob", 100);
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Edge\"},\"state\":{\"position\":[0,0],\"direction\":\"EAST\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testBack.execute(worldTest, args));
//    }
//
//    @Test
//    void executeBackValidEdgeXAxis() throws IOException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        World worldTest = new World(robots);
//        Robot test = new Normal(worldTest, "Bob", "normal");
//        test.setCurrentDirection(Direction.EAST);
//        robots.add(test);
//        Back testBack = new Back("Bob", 5);
//        String[] args = {};
//        assertEquals("{\"result\":\"OK\",\"data\":{\"message\":\"Done\"},\"state\":{\"position\":[-5,0],\"direction\":\"EAST\",\"shields\":3,\"shots\":3,\"status\":\"normal\"}}", testBack.execute(worldTest, args));
//    }
}