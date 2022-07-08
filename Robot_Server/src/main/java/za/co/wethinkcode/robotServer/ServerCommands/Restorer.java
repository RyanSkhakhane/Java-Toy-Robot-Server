package za.co.wethinkcode.robotServer.ServerCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Robot.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.sql.*;
import java.util.ArrayList;

import static za.co.wethinkcode.robotServer.RobotServer.*;
import static za.co.wethinkcode.robotServer.RobotServer.obstacleYCoord;

public class Restorer extends ServerCommand {
    @Override
    public void execute(ArrayList<ClientHandler> users, ArrayList<Robot> robots, World world) throws SQLException {
        String sql = "INSERT INTO world_roboot(size,obstacles_x,obstacles_y) VALUES(?,?,?)";
        Statement stmt = null;
        try{
            stmt = connection.dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM world_roboot;" );
            System.out.println(rs);
            System.out.println("OLDS: \nWord Size: " + worldSize + "\n Obstacle at (" + obsticleXCoord + "," + obstacleYCoord + ")");

            while ( rs.next() ) {
                int robotWorldSize = rs.getInt("size");
                int obstacle_x = rs.getInt("obstacles_x");
                int obstacle_y = rs.getInt("obstacles_y");
                worldSize = robotWorldSize;
                obstacleYCoord = obstacle_y;
                obsticleXCoord = obstacle_x;
            }

//            System.out.println("NEWS: \nWord Size: " + worldSize + "\n Obstacle at (" + obsticleXCoord + "," + obstacleYCoord + ")");
            System.out.println("Previous WORLD RESTORED successfully :)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
