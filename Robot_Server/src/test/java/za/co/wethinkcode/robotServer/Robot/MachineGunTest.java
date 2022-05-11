package za.co.wethinkcode.robotServer.Robot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotServer.Direction;
import za.co.wethinkcode.robotServer.World.World;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MachineGunTest {
    @Test
    void executeMachineGunRobot() throws FileNotFoundException {
        ArrayList<Robot> robots = new ArrayList<>();
        MachineGun machineGun = new MachineGun(new World(robots),"hal","machinegun");
        assertEquals(2,machineGun.shields);
        assertEquals(6,machineGun.shots);
        assertEquals(2,machineGun.maxShields);
        assertEquals(6,machineGun.maxShots);
        assertEquals(Direction.NORTH,machineGun.getCurrentDirection());
        assertEquals(2,machineGun.shotDistance);


    }

}
