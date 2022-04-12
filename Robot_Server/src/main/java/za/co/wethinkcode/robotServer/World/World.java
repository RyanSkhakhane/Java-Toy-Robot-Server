package za.co.wethinkcode.robotServer.World;

import za.co.wethinkcode.robotServer.Direction;
import za.co.wethinkcode.robotServer.Position;

import java.util.List;

public class World {

    protected final Position TOP_LEFT = new Position(-5, 5);
    protected final Position BOTTOM_RIGHT = new Position(5, -5);
    public static final Position CENTRE = new Position(0, 0);
    protected List<Obstacle> OBSTACLES;
//    public static final Direction STARTDIRECTION = Direction.UP;

}
