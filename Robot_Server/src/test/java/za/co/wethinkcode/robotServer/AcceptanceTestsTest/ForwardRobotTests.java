package za.co.wethinkcode.robotServer.AcceptanceTestsTest;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.RobotWorldClient;
import za.co.wethinkcode.robotServer.RobotWorldJsonClient;

import static org.junit.jupiter.api.Assertions.*;

public class ForwardRobotTests {
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

    @Test
    void ForwardEdge(){
        assertTrue(serverClient.isConnected());

        // When I send a valid launch request to the server
        String request = "{" +
                "  \"robot\": \"VAL\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response = serverClient.sendRequest(request); // test infinitely runs from here
//        System.out.println("Response: " + response);

        String requestrr = "{" +
                "  \"robot\": \"VAL\"," +
                "  \"command\": \"forward\"," +
                "  \"arguments\": [\"5\"]" +
                "}";
        JsonNode responserr = serverClient.sendRequest(requestrr);
        // {"result":"OK","data":{"position":[0,0],"message":"At the NORTH edge","visibility":1,"objects":[]},"state":{"position":[0,0],"direction":"NORTH","shields":0,"shots":0,"status":"TODO"}}
//         Then I should get a valid response from the server ({"result":"OK","data":{"position":[0,0],"visibility":1,"reload":3,"repair":3,"shields":3},"state":{"position":[0,0],"direction":"NORTH","shields":2,"shots":6,"status":"normal"}})
        assertNotNull(responserr.get("result"));
        assertEquals("OK", responserr.get("result").asText());
        assertEquals("At the NORTH edge", responserr.get("data").get("message").asText());
//        assertEquals("[0,0]", responserr.get("data").get("position").toString());
        assertNotNull(response.get("data").get("position"));





    }
}
