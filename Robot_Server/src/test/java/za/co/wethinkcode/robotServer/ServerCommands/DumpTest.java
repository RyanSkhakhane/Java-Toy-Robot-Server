package za.co.wethinkcode.robotServer.ServerCommands;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.ServerCommunication.ClientHandler;
import za.co.wethinkcode.robotServer.ServerCommunication.ServerCommands.Dump;
import za.co.wethinkcode.robotServer.RobotWorld.Robot.Normal;
import za.co.wethinkcode.robotServer.RobotWorld.Robot.Robot;
import za.co.wethinkcode.robotServer.RobotWorld.World.SquareObstacle;
import za.co.wethinkcode.robotServer.RobotWorld.World.World;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DumpTest {


    private void simulateGame(String simulatedUserInput, String expectedLastLine) throws FileNotFoundException {                     //<1>
        InputStream simulatedInputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());     //<2>
        System.setIn(simulatedInputStream);                                                             //<3>

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();                         //<4>
        System.setOut(new PrintStream(outputStreamCaptor));                                             //<5>

        ArrayList<Robot> robots = new ArrayList<>();
        World worldTest = new World(robots);
        Robot test = new Normal(worldTest, "Bob", "normal");
        robots.add(test);
        ArrayList<ClientHandler> testUserList = new ArrayList<>();
        SquareObstacle[] obstacles = {new SquareObstacle(0,3), new SquareObstacle(2,1)};
        worldTest.setObstacles(obstacles);
        Dump dump = new Dump();
        dump.execute(testUserList, robots, worldTest);


        String linesOutput = outputStreamCaptor.toString();
        assertEquals(expectedLastLine, linesOutput);                                                       //<12>
    }

    @Test
    void areRobots() throws FileNotFoundException {
            String simulatedUserInput = "";
            String expectedOutput =
                    "There are some obstacles\n" +
                            "- At position 0,3 (to 3,6)\n" +
                            "- At position 2,1 (to 5,4)\n" +
                            "ROBOTS:\n" +
                            "Robot : Bob\n" +
                            "Position [0,0] \n" +
                            "Direction [NORTH]\n \n";
            simulateGame(simulatedUserInput, expectedOutput);                                               //<3>
        }
    }