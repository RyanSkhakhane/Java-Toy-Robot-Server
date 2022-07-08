package za.co.wethinkcode.robotServer.ServerCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Robot.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.function.Predicate;

import static za.co.wethinkcode.robotServer.RobotServer.*;

public class Saver extends ServerCommand {
    @Override
    public void execute(ArrayList<ClientHandler> users, ArrayList<Robot> robots, World world) {
       // System.out.println(connection);
       // private void createData( final Connection connection )
        //throws SQLException
        //{


        String sql = "INSERT INTO world_roboot(size,obstacles_x,obstacles_y) VALUES(?,?,?)";

        try (Connection conn = connection.dbConnection;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, worldSize);
            pstmt.setInt(2, obsticleXCoord);
            pstmt.setInt(3, obstacleYCoord);
            pstmt.executeUpdate();
            System.out.println("Data INSERTED successfully :)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



//        System.out.println("");
//        System.exit(0);
    }


