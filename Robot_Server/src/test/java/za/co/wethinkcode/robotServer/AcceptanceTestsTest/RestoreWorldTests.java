package za.co.wethinkcode.robotServer.AcceptanceTestsTest;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.RobotWorldClient;
import za.co.wethinkcode.robotServer.RobotWorldJsonClient;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestoreWorldTests {
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

    @Test
    void existingName() {
        assertTrue(serverClient.isConnected());

        // When I send a valid launch request to the server
        String request = "{" +
                "  \"robot\": \"VAL\"," +
                "  \"command\": \"restore\"," +
                "  \"arguments\": []" +
                "}";
        JsonNode response = serverClient.sendRequest(request);
    }
}
