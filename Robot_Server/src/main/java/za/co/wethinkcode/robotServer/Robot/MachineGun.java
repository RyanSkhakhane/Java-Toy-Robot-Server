package za.co.wethinkcode.robotServer.Robot;

import za.co.wethinkcode.robotServer.World.World;

public class MachineGun extends Robot{

    public MachineGun(World world, String robotName, String robotType) {

        super(world, robotName, robotType);
        this.shields = 2;
        this.shots = 6;
        this.shotDistance = 2;
        this.maxShields = shields;
        this.maxShots = 6;
    }
}
