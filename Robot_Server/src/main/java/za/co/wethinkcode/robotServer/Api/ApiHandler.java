package za.co.wethinkcode.robotServer.Api;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import io.javalin.http.NotFoundResponse;
import za.co.wethinkcode.robotServer.Database.DbConnect;
import za.co.wethinkcode.robotServer.RobotWorld.Robot.Robot;
import za.co.wethinkcode.robotServer.RobotWorld.World.World;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static za.co.wethinkcode.robotServer.ServerCommunication.ClientHandler.world;

public class ApiHandler {

//    public static DbConnect connection;


    public void defaultWorld(Context context) throws FileNotFoundException {
        ArrayList<Robot> robots = new ArrayList<>();
        World currentWorld = new World(robots);
        context.json(currentWorld.allWorldObjects());
    }

//    public void restoreWorld(Context context) throws FileNotFoundException {
//        // connect to database
//        String databaseFile = "-f world.db";
//        String[] db=databaseFile.split(" ");
//        DbConnect database = new DbConnect(db);
//
//
//        // restore specified world
//        String worldName = context.pathParamMap().get("worldName"); // world name as string
//        ArrayList<Robot> robots = new ArrayList<>();
//        World currentWorld = new World(robots);
//        world.restoreWorld(worldName);
//
//
//    }


}
