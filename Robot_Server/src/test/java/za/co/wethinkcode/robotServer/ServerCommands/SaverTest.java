package za.co.wethinkcode.robotServer.ServerCommands;


import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.ServerCommunication.ServerCommands.Saver;


import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.*;


class SaverTest {
    Saver saver = new Saver();

    @Test
    void testWorldNameAlreadyExistsInDatabase() {
        // add name to database if it doesn't exist
        if (saver.worldNameAlreadyExistsInDatabase("ryan") == true){
            assertTrue(Saver.worldNameAlreadyExistsInDatabase("ryan"));

        }else
            assertFalse(Saver.worldNameAlreadyExistsInDatabase("Drizzy"));
    }


}

