package za.co.wethinkcode.robotServer.AcceptanceTestsTest;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.RobotWorldClient;
import za.co.wethinkcode.robotServer.RobotWorldJsonClient;

import static org.junit.jupiter.api.Assertions.*;

public class LookRobotTests {



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

    @Test
    void testConnectivity(){
        assertTrue(serverClient.isConnected());

    }

    @Test
    void testLook(){
        String request = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response = serverClient.sendRequest(request);


        assertNotNull(response.get("result"));
        assertEquals("OK", response.get("result").asText());


        String request2 = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"look\","+
                "  \"arguments\": [\"\"]" +
                "}";
        JsonNode response2 = serverClient.sendRequest(request2);


        assertNotNull(response2.get("result"));
        assertEquals("OK",  response2.get("result").asText());
        assertTrue(response2.get("data").get("objects").toString().contains("EDGE"));
        assertTrue(response2.get("data").get("objects").toString().contains("OBSTACLE"));

    }

    @Test
    void shouldSeeOtherRobots() {
        int robots = 0;
        boolean world_ful = false;
        while (!world_ful) {
            if(robots == 4){
                world_ful = true;
            }
            robots++;
            String request = "{" +
                    "  \"robot\": \"HAL " + robots + "\"," +
                    "  \"command\": \"launch\"," +
                    "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                    "}";
            JsonNode response = serverClient.sendRequest(request);
        }

        String request1 = "{" +
                "  \"robot\": \"HAL 2\"," +
                "  \"command\": \"look\","+
                "  \"arguments\": [\"\"]" +
                "}";

        JsonNode response1 = serverClient.sendRequest(request1);
        assertNotNull(response1.get("result"));
//        assertTrue(response1.get("data").get("objects").toString().contains("ROBOT"));
        assertTrue(response1.get("data").get("objects").toString().contains("EDGE"));
    }

    @Test
    void shouldSeeObstacles(){
        int robots = 0;
        boolean world_ful = false;
        while (!world_ful) {
            if(robots == 4){
                world_ful = true;
            }
            robots++;
            String request = "{" +
                    "  \"robot\": \"HAL " + robots + "\"," +
                    "  \"command\": \"launch\"," +
                    "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                    "}";
            JsonNode response = serverClient.sendRequest(request);
        }

        String request1 = "{" +
                "  \"robot\": \"HAL 2\"," +
                "  \"command\": \"look\","+
                "  \"arguments\": [\"\"]" +
                "}";

        JsonNode response1 = serverClient.sendRequest(request1);
        assertNotNull(response1.get("result"));
        assertTrue(response1.get("data").get("objects").toString().contains("OBSTACLE"));
        assertTrue(response1.get("data").get("objects").toString().contains("EDGE"));
    }
}
