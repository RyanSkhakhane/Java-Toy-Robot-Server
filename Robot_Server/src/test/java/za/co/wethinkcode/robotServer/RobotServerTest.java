package za.co.wethinkcode.robotServer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.ServerCommunication.RobotServer;

import static org.junit.jupiter.api.Assertions.*;

class RobotServerTest {

    @Test
    void portCheck(){
        Assertions.assertFalse(RobotServer.portCheck("12345"));
        assertFalse(RobotServer.portCheck("145"));
        assertFalse(RobotServer.portCheck("123s"));
        assertFalse(RobotServer.portCheck("eeee"));
        assertTrue(RobotServer.portCheck("2345"));

    }

}