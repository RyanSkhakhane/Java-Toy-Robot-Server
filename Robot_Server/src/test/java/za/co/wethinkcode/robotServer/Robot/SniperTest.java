//package za.co.wethinkcode.Tests.Robot;
//
//import org.junit.jupiter.api.Test;
//import za.co.wethinkcode.robotServer.Direction;
//import za.co.wethinkcode.robotServer.Robot.Robot;
//import za.co.wethinkcode.robotServer.Robot.Sniper;
//import za.co.wethinkcode.robotServer.World.World;
//
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//public class SniperTest {
//    @Test
//    void executeSniperRobot() throws FileNotFoundException {
//        ArrayList<Robot> robots = new ArrayList<>();
//        Sniper sniper = new Sniper(new World(robots),"hal","sniper");
//        assertEquals(1,sniper.shields);
//        assertEquals(2,sniper.shots);
//        assertEquals(1,sniper.maxShields);
//        assertEquals(2,sniper.maxShots);
//        assertEquals(Direction.NORTH,sniper.getCurrentDirection());
//        assertEquals(5,sniper.shotDistance);
//
//
//    }
//
//}
