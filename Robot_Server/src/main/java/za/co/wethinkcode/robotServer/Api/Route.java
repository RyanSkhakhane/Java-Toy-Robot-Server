package za.co.wethinkcode.robotServer.Api;

import io.javalin.http.Context;
import za.co.wethinkcode.robotServer.ClientCommandsTest.ClientCommands;
import za.co.wethinkcode.robotServer.Robot.Robot;
import za.co.wethinkcode.robotServer.Robot.Sniper;
import za.co.wethinkcode.robotServer.World.World;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public final class Route {
    public static void forward(Context ctx) throws ClientCommands.CommandNotFoundException, FileNotFoundException {
        ClientCommands command = ClientCommands.create(ctx.body());
        ArrayList<Robot> robots = new ArrayList<>();
        World world = new World(robots);
        robots.add(new Sniper(world, "OFHANI", "sniper"));
        String respose = command.execute(world, new String[]{"forward", "10"});
        ctx.json(respose);
    }
}
