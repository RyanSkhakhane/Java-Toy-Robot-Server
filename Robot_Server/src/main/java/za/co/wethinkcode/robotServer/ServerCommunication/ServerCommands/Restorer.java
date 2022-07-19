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

        String sql = "SELECT * FROM world_roboot WHERE world_name=?";

        try(final Connection conn = DriverManager.getConnection(IN_MEMORY_DB_URL)) {
//            System.out.println("Database connected!");

            try (PreparedStatement pstmt = conn.prepareStatement(sql)){
                System.out.println("World name: " + world_name);
                pstmt.setString(1, world_name);
//            stmt = connection.dbConnection.createStatement();
                ResultSet rs = pstmt.executeQuery();
//            System.out.println(rs);

//                System.out.println("OLDS: \nWord Size: " + worldSize + "\n Obstacle at (" + obsticleXCoord + "," + obstacleYCoord + ")");
                if(Saver.worldNameAlreadyExistsInDatabase(world_name)) {
                    while (rs.next()) { // something is wrong here
                        //                world_name = pstmt.get("world_name");
                        int robotWorldSize = rs.getInt("size");
                        int obstacle_x = rs.getInt("obstacles_x");
                        int obstacle_y = rs.getInt("obstacles_y");
                        System.out.println("Inside while loop");
                        RobotServer.worldSize = robotWorldSize + 1;
                        RobotServer.obstacleYCoord = obstacle_y + 1;
                        RobotServer.obsticleXCoord = obstacle_x + 1;

                        System.out.println("\nCongratulations! WORLD of: \n- size " + RobotServer.worldSize + "x" + RobotServer.worldSize +
                                " and \n- obstacles at (" + RobotServer.obsticleXCoord + "," + RobotServer.obstacleYCoord + ") " +
                                "\n was RESTORED successfully :) \n\nWhat would you like to do next?\n");
                    }
                } else {
                    System.out.println("World name by the name of " + world_name + "does not exits in the database :)" +
                            "\nWhat would you like to do next?: \n");
                }


//                System.out.println("Previous WORLD RESTORED successfully :)");

            } catch (NullPointerException e){
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("HERE");
            System.out.println(e.getMessage());
        }
    }
}
