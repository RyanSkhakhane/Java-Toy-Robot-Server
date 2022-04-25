import org.testng.annotations.Test;
import za.co.wethinkcode.RobotClient.ClientCommands.ClientCommands;

import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertEquals;

public class ClientCommandsTests {
    @Test
    void createInvalidCommand() {
        try {
            ClientCommands clientCommands = ClientCommands.create("say hello");
            fail("Should have thrown an exception");
        } catch (IllegalArgumentException e) {
            assertEquals("Unsupported command: say hello", e.getMessage());
        }
    }
    
}
