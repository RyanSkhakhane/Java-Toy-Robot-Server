package za.co.wethinkcode.RobotClient.ClientCommands;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.RobotClient.RobotWorldClient;
import za.co.wethinkcode.RobotClient.RobotWorldJsonClient;

import static org.junit.jupiter.api.Assertions.*;

/**
 * As a player
 * I want to launch my robot in the online robot world
 * So that I can break the record for the most robot kills
 */
class LaunchRobotTests {
    private final static int DEFAULT_PORT = 5000;
    private final static String DEFAULT_IP = "localhost";
    private final RobotWorldClient serverClient = new RobotWorldJsonClient();

    @BeforeEach
    void connectToServer(){
        serverClient.connect(DEFAULT_IP, DEFAULT_PORT);
    }

    @AfterEach
    void disconnectFromServer(){
        serverClient.disconnect();
    }


    //--------------------  T E S T S -------------------------------------------------
    @Test
    void validLaunchShouldSucceed(){
        // Given that I am connected to a running Robot Worlds server
        // And the world is of size 1x1 (The world is configured or hardcoded to this size)
        assertTrue(serverClient.isConnected());

        // When I send a valid launch request to the server
        String request = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response = serverClient.sendRequest(request);

        // Then I should get a valid response from the server
        assertNotNull(response.get("result"));
        assertEquals("OK", response.get("result").asText());

        // And the position should be (x:0, y:0)
        assertNotNull(response.get("data"));
        assertNotNull(response.get("data").get("position"));
        assertEquals(0, response.get("data").get("position").get(0).asInt());
        assertEquals(0, response.get("data").get("position").get(1).asInt());

        // And I should also get the state of the robot
        assertNotNull(response.get("state"));
    }

    @Test
    void invalidLaunchShouldFail(){
        // Given that I am connected to a running Robot Worlds server
        assertTrue(serverClient.isConnected());

        // When I send a invalid launch request with the command "luanch" instead of "launch"
        String request = "{" +
                "\"robot\": \"HAL\"," +
                "\"command\": \"luanch\"," +
                "\"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response = serverClient.sendRequest(request);

        // Then I should get an error response
        assertNotNull(response.get("result"));
        assertEquals("ERROR", response.get("result").asText());

        // And the message "Unsupported command"
        assertNotNull(response.get("data"));
        assertNotNull(response.get("data").get("message"));
        assertTrue(response.get("data").get("message").asText().contains("Unsupported command"));
    }

    @Test
    void theWorldHasNoSpaceForRobot(){
        //Given that a client is connected and successfully launched to a Robot Worlds server
        //And the world is of size 1x1
        String request1 = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response1 = serverClient.sendRequest(request1);

        //When I send a valid launch request
        String request2 = "{" +
                "  \"robot\": \"HAP\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response2 = serverClient.sendRequest(request2);

        // Then I should get an "ERROR" response
        assertNotNull(response2.get("result"));
        assertEquals("ERROR", response2.get("result").asText());

        //And the message "No more space in this world"
        assertNotNull(response2.get("data"));
        assertNotNull(response2.get("data").get("message"));
        System.out.println(response2.get("data").get("message").asText());
        assertTrue(response2.get("data").get("message").asText().contains("No more space in this world"));
    }

    @Test
    void robotNameAlreadyTaken (){
        // Given that a client is connected and successfully launched to a Robot Worlds server
        // And the world is of size 1x1
        String request1 = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response1 = serverClient.sendRequest(request1);

        // When I launch and name my Robot with an existing Robot name in the World
        String request2 = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response2 = serverClient.sendRequest(request2);


        // Then I should get an "ERROR" response
        assertNotNull(response2.get("result"));
        assertEquals("ERROR", response2.get("result").asText());

        // And the message "Too many of you in this world"
        assertNotNull(response2.get("data"));
        assertNotNull(response2.get("data").get("message"));
//        System.out.println(response2.get("data").get("message").asText());
        assertTrue(response2.get("data").get("message").asText().contains("Too many of you in this world"));

    }
}