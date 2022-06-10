package za.co.wethinkcode.RobotClient.ClientCommands;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.RobotClient.RobotWorldClient;
import za.co.wethinkcode.RobotClient.RobotWorldJsonClient;

import static org.junit.jupiter.api.Assertions.*;

public class StateRobotTests {
    private final static int DEFAULT_PORT = 5000;
    private final static String DEFAULT_IP = "localhost";

    //    private final static int DEFAULT_PORT = 8080;
//    private final static String DEFAULT_IP = "20.20.28.20";
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
    void validStateShouldSucceed(){
        // Given that I am connected to a running Robot Worlds server
        // And the world is of size 1x1 (The world is configured or hardcoded to this size)
        assertTrue(serverClient.isConnected());

        // When I send a valid launch request to the server

        String requestrr = "{" +
                "  \"robot\": \"VAL\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode responserr = serverClient.sendRequest(requestrr);

        String request = "{" +
                "  \"robot\": \"VAL\"," +
                "  \"command\": \"state\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response = serverClient.sendRequest(request); // test infinitely runs from here
//        System.out.println("Response: " + response);

        // Then I should get a valid response from the server ({"result":"OK","data":{"position":[0,0],"visibility":1,"reload":3,"repair":3,"shields":3},"state":{"position":[0,0],"direction":"NORTH","shields":2,"shots":6,"status":"normal"}})
        assertNotNull(response.get("result"));
        assertEquals("OK", response.get("result").asText());

        // And the position should be (x:0, y:0)
        assertNotNull(response.get("data"));
        assertNotNull(response.get("data").get("position"));
        assertEquals(0, response.get("data").get("position").get(0).asInt()); // fail
        assertEquals(0, response.get("data").get("position").get(1).asInt());

        // And I should also get the state of the robot
        assertNotNull(response.get("state"));
    }

}


