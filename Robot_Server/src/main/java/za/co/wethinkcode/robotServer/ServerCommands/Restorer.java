package za.co.wethinkcode.robotServer.ServerCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Robot.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static za.co.wethinkcode.robotServer.RobotServer.*;
import static za.co.wethinkcode.robotServer.RobotServer.obstacleYCoord;

public class Restorer extends ServerCommand {
    @Override
    public void execute(ArrayList<ClientHandler> users, ArrayList<Robot> robots, World world) throws SQLException {
        Statement stmt = null;
        System.out.println("What world name would like to RESTORE? ");
        Scanner in = new Scanner(System.in);
        String world_n=in.next();


        try{
            stmt = connection.dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM world_roboot WHERE world_name = world_name;" );
            System.out.println(rs);
            System.out.println("OLDS: \nWord Size: " + worldSize + "\n Obstacle at (" + obsticleXCoord + "," + obstacleYCoord + ")");

            while ( rs.next() ) {
                String world_name = rs.getString("world_name");
                int robotWorldSize = rs.getInt("size");
                int obstacle_x = rs.getInt("obstacles_x");
                int obstacle_y = rs.getInt("obstacles_y");
                worldSize = robotWorldSize+1;
                obstacleYCoord = obstacle_y+1;
                obsticleXCoord = obstacle_x+1;
            }

            System.out.println("NEWS: \nWord Size: " + worldSize + "\n Obstacle at (" + obsticleXCoord + "," + obstacleYCoord + ")");
            System.out.println("Previous WORLD RESTORED successfully :)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
