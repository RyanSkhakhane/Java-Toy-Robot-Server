package za.co.wethinkcode.RobotClient.ClientCommands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReloadTest {
    @Test
    void executeTest(){
        Reload test = new Reload();
        assertEquals("{\"robot\":\"bob\",\"command\":\"reload\",\"arguments\":[]}", test.execute("bob"));
    }
}