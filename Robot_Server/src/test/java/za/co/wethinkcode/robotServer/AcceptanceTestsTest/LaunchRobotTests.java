package za.co.wethinkcode.robotServer.AcceptanceTestsTest;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.Position;
import za.co.wethinkcode.robotServer.RobotWorldClient;
import za.co.wethinkcode.robotServer.RobotWorldJsonClient;
import za.co.wethinkcode.robotServer.World.Obstacle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * As a player
 * I want to launch my robot in the online robot world
 * So that I can break the record for the most robot kills
 */
class LaunchRobotTests {
    private final static int DEFAULT_PORT = 5000;
    private final static String DEFAULT_IP = "localhost";

    //    private final static int DEFAULT_PORT = 8080;
//    private final static String DEFAULT_IP = "20.20.28.20";
    private final RobotWorldClient serverClient = new RobotWorldJsonClient();

    @BeforeEach
    void connectToServer() {
        serverClient.connect(DEFAULT_IP, DEFAULT_PORT);
    }

    @AfterEach
    void disconnectFromServer() {
        serverClient.disconnect();
    }


    //--------------------  T E S T S -------------------------------------------------
    @Test
    void validLaunchShouldSucceed() {
        // Given that I am connected to a running Robot Worlds server
        // And the world is of size 1x1 (The world is configured or hardcoded to this size)
        assertTrue(serverClient.isConnected());

        // When I send a valid launch request to the server
        String request = "{" +
                "  \"robot\": \"VAL\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response = serverClient.sendRequest(request); // test infinitely runs from here
//        System.out.println("Response: " + response);

        // Then I should get a valid response from the server ({"result":"OK","data":{"position":[0,0],"visibility":1,"reload":3,"repair":3,"shields":3},"state":{"position":[0,0],"direction":"NORTH","shields":2,"shots":6,"status":"normal"}})
        assertNotNull(response.get("result"));
        assertEquals("OK", response.get("result").asText());

        // And the position should be random
        assertNotNull(response.get("data"));
        assertNotNull(response.get("data").get("position"));
//        assertNotEquals(0, response.get("data").get("position").get(0).asInt()); // fail
//        assertNotEquals(0, response.get("data").get("position").get(1).asInt());

        // And I should also get the state of the robot
        assertNotNull(response.get("state"));
    }

    @Test
    void invalidLaunchShouldFail() {
        // Given that I am connected to a running Robot Worlds server
        assertTrue(serverClient.isConnected());

        // When I send a invalid launch request with the command "luanch" instead of "launch"
        String request = "{" +
                "\"robot\": \"ROTO\"," +
                "\"command\": \"luanch\"," +
                "\"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response = serverClient.sendRequest(request); // {"result":"ERROR","data":{"message":"Unsupported command"}}

        // Then I should get an error response ( {"result":"ERROR","data":{"message":"Unsupported command"}} )
        assertNotNull(response.get("result"));
        assertEquals("ERROR", response.get("result").asText());

        // And the message "Unsupported command"
        assertNotNull(response.get("data"));
        assertNotNull(response.get("data").get("message"));
        assertTrue(response.get("data").get("message").asText().contains("Unsupported command"));
    }

    @Test
    void theWorldHasNoSpaceForRobot() {
        //Given that a client is connected and successfully launched to a Robot Worlds server
        //And the world is of size yxy (y > 0)
        boolean world_full = false;
        int count = 1;
        JsonNode response1 = null;
        while (!world_full) {
            String request1 = "{" +
                    "  \"robot\": \"HAL " + count + "\"," +
                    "  \"command\": \"launch\"," +
                    "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                    "}";
            response1 = serverClient.sendRequest(request1); // {"result":"OK","data":{"visibility":1,"position":[0,0],"objects":[]},"state":{"position":[0,0],"direction":"NORTH","shields":0,"shots":0,"status":"TODO"}}
            String currentResult = response1.get("result").asText();
            if (currentResult.equalsIgnoreCase("error")) {
                System.out.println("[Print statement] : The world is full with " + (count-1) + " robots inside.");
                world_full = true;
            }
            count++;
        }

        // Then I should get an "ERROR" response
        assertNotNull(response1.get("result"));
        assertEquals("ERROR", response1.get("result").asText());

        //And the message "No more space in this world"
        assertNotNull(response1.get("data"));
        assertNotNull(response1.get("data").get("message"));
//        System.out.println(response1.get("data").get("message").asText());
        assertTrue(response1.get("data").get("message").asText().contains("No more space in this world"));
    }

    @Test
    void robotNameAlreadyTaken() {
        // Given that a client is connected and successfully launched to a Robot Worlds server
        // And the world is of size 1x1
        String request1 = "{" +
                "  \"robot\": \"ROTO1\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response1 = serverClient.sendRequest(request1); // {"result":"OK","data":{"visibility":1,"position":[0,0],"objects":[]},"state":{"position":[0,0],"direction":"NORTH","shields":0,"shots":0,"status":"TODO"}}

        // When I launch and name my Robot with an existing Robot name in the World
        String request2 = "{" +
                "  \"robot\": \"ROTO1\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response2 = serverClient.sendRequest(request2); // {"result":"ERROR","data":{"message":"Too many of you in this world"}}


        // Then I should get an "ERROR" response
        assertNotNull(response2.get("result"));
        assertEquals("ERROR", response2.get("result").asText());

        // And the message "Too many of you in this world"
        assertNotNull(response2.get("data"));
        assertNotNull(response2.get("data").get("message"));
        System.out.println(response2.get("data").get("message").asText());
        assertTrue(response2.get("data").get("message").asText().contains("Too many of you in this world"));

    }

//    @Test
//    void canLaunchAnotherRobot() {
//        // Given that I am connected to a running Robot Worlds server
//        // And the world is of size (-s entered parameter) (2x2)
//        assertTrue(serverClient.isConnected());
//
//        // and robot "HAL" has already been launched into the world
//        String request1 = "{" +
//                "  \"robot\": \"HAL\"," +
//                "  \"command\": \"launch\"," +
//                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
//                "}";
//        JsonNode firstRobotResponse = serverClient.sendRequest(request1);
//
//        JsonNode response1 = null;
//        boolean world_full = false;
//        int count = 1;
//
//        // When I launch another robot into the world
//        while (!world_full) {
//            count++;
//            String request2 = "{" +
//                    "  \"robot\": \"HAL " + count + "\"," +
//                    "  \"command\": \"launch\"," +
//                    "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
//                    "}";
//            response1 = serverClient.sendRequest(request1); // {"result":"OK","data":{"visibility":1,"position":[0,0],"objects":[]},"state":{"position":[0,0],"direction":"NORTH","shields":0,"shots":0,"status":"TODO"}}
//            String currentResult = response1.get("result").asText();
//            if (currentResult.equalsIgnoreCase("error")) {
//                System.out.println("[Print statement] : The world is full with " + (count - 1) + " robots inside.");
//                world_full = true;
//                break;
//            }
//            count++;
//        }
//
//            if (count <= 2) {
//                assertTrue(response1.get("result").toString().contains("ERROR"));
//            } else {
//
//                // Then the launch should be successful and I should get an "OK" response (launch should be successful)
//                assertNotNull(response1.get("result"));
//                assertEquals("OK", response1.get("result"));
//
//                // and a randomly allocated position of R2D2 should be returned.
//                //        System.out.println("Random position");
//                assertNotEquals("[0,0]", response1.get("data").get("position").toString());
//            }
//        }


//    @Test
//    void worldWithoutObstaclesIsFull(){
//        //Given that a client is connected and successfully launched to a Robot Worlds server
//        //And the world is of size AxA (A > 0) (2x2)
//        boolean world_full = false;
//        int count = 1;
//        JsonNode response1 = null;
//
//        // And I have successfully launched 9 robots into the world
//        // When I launch one more robot
//        while (!world_full) {
//            String request1 = "{" +
//                    "  \"robot\": \"HAL " + count + "\"," +
//                    "  \"command\": \"launch\"," +
//                    "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
//                    "}";
//            response1 = serverClient.sendRequest(request1); // {"result":"OK","data":{"visibility":1,"position":[0,0],"objects":[]},"state":{"position":[0,0],"direction":"NORTH","shields":0,"shots":0,"status":"TODO"}}
//            String currentResult = response1.get("result").asText();
//            if (currentResult.equalsIgnoreCase("error")) {
//                System.out.println("[Print statement] : The world is full with " + (count-1) + " robots inside.");
//                world_full = true;
//            }
//            count++;
//        }
//
//        // Then I should get an "ERROR" response
//        assertNotNull(response1.get("result"));
//        assertEquals("ERROR", response1.get("result").asText());
//
//        //And the message "No more space in this world"
//        assertNotNull(response1.get("data"));
//        assertNotNull(response1.get("data").get("message"));
////        System.out.println(response1.get("data").get("message").asText());
//        assertTrue(response1.get("data").get("message").asText().contains("No more space in this world"));
//    }
//

    @Test
    void launchRobotsIntoAWorldWithAnObstacle(){
        // Given a world of size 2x2
        // And the world has an obstacle at coordinate [1,1]

        // When I launch 8 robots into the world

        boolean world_full = false;
        int count = 1;
        JsonNode response1 = null;

        while (!world_full) {
            String request1 = "{" +
                    "  \"robot\": \"HAL " + count + "\"," +
                    "  \"command\": \"launch\"," +
                    "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                    "}";
            response1 = serverClient.sendRequest(request1); // {"result":"OK","data":{"visibility":1,"position":[0,0],"objects":[]},"state":{"position":[0,0],"direction":"NORTH","shields":0,"shots":0,"status":"TODO"}}
            String currentResult = response1.get("result").asText();
            if (currentResult.equalsIgnoreCase("error")) {
                System.out.println("[Print statement] : The world is full with " + (count-1) + " robots inside.");
                world_full = true;
            } else {
                // Then each robot cannot be in position [1,1]
                assertNotEquals("[1,1]", response1.get("data").get("position").toString());
            }
            count++;
        }


    }


    @Test
    void worldWithAnObstacleIsFull(){
        // Given a world of size 2x2
        // And the world has an obstacle at coordinate [1,1]

        // And I have successfully launche 8 robots into the world

        boolean world_full = false;
        int count = 1;
        JsonNode response1 = null;

        while (!world_full) {
            String request1 = "{" +
                    "  \"robot\": \"HAL " + count + "\"," +
                    "  \"command\": \"launch\"," +
                    "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                    "}";
            response1 = serverClient.sendRequest(request1); // {"result":"OK","data":{"visibility":1,"position":[0,0],"objects":[]},"state":{"position":[0,0],"direction":"NORTH","shields":0,"shots":0,"status":"TODO"}}
            String currentResult = response1.get("result").asText();
            if (currentResult.equalsIgnoreCase("error")) {
                System.out.println("[Print statement] : The world is full with " + (count-1) + " robots inside.");
                world_full = true;
            }
            count++;
        }

        // When I launch one more robot
        String request1 = "{" +
                "  \"robot\": \"HAL " + count + "\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        response1 = serverClient.sendRequest(request1);

        // Then I should get an error response bac with the message "No more space in this world"
        assertNotNull(response1.get("data"));
        assertNotNull(response1.get("data").get("message"));
//        System.out.println(response1.get("data").get("message").asText());
        assertTrue(response1.get("data").get("message").asText().contains("No more space in this world"));



    }
}