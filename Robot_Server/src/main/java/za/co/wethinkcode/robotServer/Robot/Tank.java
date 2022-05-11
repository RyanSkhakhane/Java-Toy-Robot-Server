package za.co.wethinkcode.robotServer.Robot;

import za.co.wethinkcode.robotServer.World.World;

public class Tank extends Robot{

    public Tank(World world, String robotName, String robotType) {

        super(world, robotName, robotType);
        this.shields = 5;
        this.shots = 3;
        this.shotDistance = 2;
        this.maxShields = shields;
        this.maxShots = 3;
    }
}
