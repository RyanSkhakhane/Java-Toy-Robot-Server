package za.co.wethinkcode.robotServer.AcceptanceTestsTest;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.RobotWorldClient;
import za.co.wethinkcode.robotServer.RobotWorldJsonClient;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaveWorldTests {
    private final static int DEFAULT_PORT = 5000;
    private final static String DEFAULT_IP = "localhost";

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
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response = serverClient.sendRequest(request);

        String request2 = "{" +
                "  \"robot\": \"VAL\"," +
                "  \"command\": \"save\"," +
                "  \"arguments\": []" +
                "}";
        JsonNode response2 = serverClient.sendRequest(request);
    }

    @Test
    void saveUniqueNameWorld() {
        assertTrue(serverClient.isConnected());

        // When I send a valid launch request to the server
        String request = "{" +
                "  \"robot\": \"VAL\"," +
                "  \"command\": \"save\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response = serverClient.sendRequest(request);

        String request2 = "{" +
                "  \"robot\": \"VAL\"," +
                "  \"command\": \"save\"," +
                "  \"arguments\": []" +
                "}";
        JsonNode response2 = serverClient.sendRequest(request);
    }
}
