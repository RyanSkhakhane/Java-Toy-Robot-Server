package za.co.wethinkcode.robotServer.ServerCommunication.ServerCommands;

import za.co.wethinkcode.robotServer.ServerCommunication.ClientHandler;
import za.co.wethinkcode.robotServer.ServerCommunication.RobotServer;
import za.co.wethinkcode.robotServer.RobotWorld.Robot.Robot;
import za.co.wethinkcode.robotServer.RobotWorld.World.World;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static za.co.wethinkcode.robotServer.Database.DbConnect.IN_MEMORY_DB_URL;

public class Restorer extends ServerCommand {
    @Override
    public void execute(ArrayList<ClientHandler> users, ArrayList<Robot> robots, World world) throws SQLException {
        Statement stmt = null;
        System.out.println("What world name would like to RESTORE? ");
        Scanner in = new Scanner(System.in);
        String world_name=in.next();

//        world.restoreWorld(world_name);
    }
}
