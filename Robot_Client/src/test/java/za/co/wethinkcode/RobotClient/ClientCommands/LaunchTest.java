package za.co.wethinkcode.RobotClient.ClientCommands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaunchTest {
    @Test
    void executeTest(){
        Launch test = new Launch("normal", "bob");
        assertEquals("{\"robot\":\"bob\",\"command\":\"launch\",\"arguments\":[\"normal\",\"0\",\"0\"]}", test.execute("bob"));
    }
}