package za.co.wethinkcode.robotServer.ServerCommands;

import za.co.wethinkcode.robotServer.ClientHandler;
import za.co.wethinkcode.robotServer.Robot.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

import static za.co.wethinkcode.robotServer.DbConnect.IN_MEMORY_DB_URL;
import static za.co.wethinkcode.robotServer.RobotServer.*;

public class Saver extends ServerCommand {
    @Override
    public void execute(ArrayList<ClientHandler> users, ArrayList<Robot> robots, World world) {
       // System.out.println(connection);
       // private void createData( final Connection connection )
        //throws SQLE   xception
        //{
        System.out.println("What would you like to call your world? ");
        Scanner scanner = new Scanner(System.in);
        String world_name = scanner.nextLine();

        String sql = "INSERT INTO world_roboot(world_name, size, obstacles_x, obstacles_y) VALUES(?,?,?,?)";

        try (final Connection conn = DriverManager.getConnection(IN_MEMORY_DB_URL)) {
            System.out.println("Database connected!");
            try (PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, String.valueOf(world_name));
                pstmt.setInt(2, worldSize);
                pstmt.setInt(3, obsticleXCoord);
                pstmt.setInt(4, obstacleYCoord);
                pstmt.executeUpdate();
                System.out.println("Data INSERTED successfully :)");
            } catch (NullPointerException e){
                System.out.println("Error");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



//        System.out.println("");
//        System.exit(0);
    }


