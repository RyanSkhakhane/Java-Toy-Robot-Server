package za.co.wethinkcode.RobotClient.ClientCommands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {
    @Test
    void executeTest(){
        State test = new State();
        assertEquals("{\"robot\":\"bob\",\"command\":\"state\",\"arguments\":[]}", test.execute("bob"));
    }

}