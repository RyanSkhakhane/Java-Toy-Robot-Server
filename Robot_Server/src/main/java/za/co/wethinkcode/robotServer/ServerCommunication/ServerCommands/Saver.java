package za.co.wethinkcode.robotServer.ServerCommunication.ServerCommands;

import za.co.wethinkcode.robotServer.ServerCommunication.ClientHandler;
import za.co.wethinkcode.robotServer.RobotWorld.Robot.Robot;
import za.co.wethinkcode.robotServer.RobotWorld.World.World;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static za.co.wethinkcode.robotServer.Database.DbConnect.IN_MEMORY_DB_URL;
import static za.co.wethinkcode.robotServer.ServerCommunication.RobotServer.*;

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
        worldName = world_name;

        String sql = "INSERT INTO world_roboot(world_name, size, obstacles_x, obstacles_y) VALUES(?,?,?,?)";
        if(!worldNameAlreadyExistsInDatabase(worldName)) {
            try (final Connection conn = DriverManager.getConnection(IN_MEMORY_DB_URL)) {
//                System.out.println("Database connected!");
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, String.valueOf(worldName));
                    pstmt.setInt(2, worldSize);
                    pstmt.setInt(3, obsticleXCoord);
                    pstmt.setInt(4, obstacleYCoord);
                    pstmt.executeUpdate();
                    System.out.println("\nCongratulations! World saved successfully :) \n What would you like to do next?");
//                    System.out.println(worldName + " saved!");
                } catch (NullPointerException e) {
                    System.out.println("Error");
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static boolean worldNameAlreadyExistsInDatabase(String worldNameToSave) {
        String sql = "SELECT * FROM world_roboot WHERE world_name=?";

        try(final Connection conn = DriverManager.getConnection(IN_MEMORY_DB_URL)) {
//            System.out.println("Database connected!");

            try (PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, worldNameToSave);
//            stmt = connection.dbConnection.createStatement();
                ResultSet rs = pstmt.executeQuery();
//            System.out.println(rs);


                while (rs.next()) { // something is wrong here
                    System.out.println("World name already exists. Try saving another world name :)");
                    System.out.println("\nWhat would you like to do next?: \n");
                    return true;
                }

            } catch (NullPointerException e){
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;

    }



//        System.out.println("");
//        System.exit(0);
    }


