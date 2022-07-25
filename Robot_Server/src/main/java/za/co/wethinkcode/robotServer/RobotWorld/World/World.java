package za.co.wethinkcode.robotServer.RobotWorld.World;

import com.google.gson.Gson;
import za.co.wethinkcode.robotServer.ServerCommunication.ConfigFileJson;
import za.co.wethinkcode.robotServer.RobotWorld.Position;
import za.co.wethinkcode.robotServer.RobotWorld.Robot.Robot;
import za.co.wethinkcode.robotServer.ServerCommunication.RobotServer;
import za.co.wethinkcode.robotServer.ServerCommunication.ServerCommands.Saver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static za.co.wethinkcode.robotServer.Database.DbConnect.IN_MEMORY_DB_URL;

public class World {

    public Position TOP_LEFT = new Position(-(getEdge(true)), getEdge(false));
    public Position BOTTOM_RIGHT = new Position(getEdge(true), -(getEdge(false)));
    public int VISIBILITY;
    protected SquareObstacle[] OBSTACLES;
    protected ArrayList<Robot> robots;


    public World(ArrayList<Robot> robotArrayList) throws FileNotFoundException {
        this.OBSTACLES = readObstacles();
        this.robots = robotArrayList;
        this.VISIBILITY = getVISIBILITY();
    }

    public SquareObstacle[] readObstacles() {
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("Config.json");
            ConfigFileJson json = gson.fromJson(fileReader, ConfigFileJson.class);
            return json.getObstacles();
        } catch (FileNotFoundException e) {
            System.out.println("No config file present");
        }
        return new SquareObstacle[]{new SquareObstacle(5, 5)};
    }

    public void showObstacles() {
        System.out.println("There are some obstacles");
        for (int i = 0; i <= OBSTACLES.length - 1; i++) {
            System.out.println("- At position " + Arrays.asList(OBSTACLES).get(i).getBottomLeftX() + "," + Arrays.asList(OBSTACLES).get(i).getBottomLeftY() + "" +
                    " (to " + (Arrays.asList(OBSTACLES).get(i).getBottomLeftX() + 3) + "," + (Arrays.asList(OBSTACLES).get(i).getBottomLeftY() + 3) + ")");
        }
    }

    public int getEdge(boolean xCheck) {
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("Config.json");
            ConfigFileJson json = gson.fromJson(fileReader, ConfigFileJson.class);
            ConfigFileJson.GridJson grid = json.getGridSize();
            if (xCheck) {
                return grid.getX();
            } else {
                return grid.getY();
            }

        } catch (FileNotFoundException e) {
            System.out.println("No config file present");
        }
        return 0;
    }

    public int getVISIBILITY() {
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("Config.json");
            ConfigFileJson json = gson.fromJson(fileReader, ConfigFileJson.class);
            return json.getVisibility();
        } catch (FileNotFoundException e) {
            System.out.println("No config file present");
        }
        return 5;
    }

    public SquareObstacle[] getOBSTACLES() {
        return OBSTACLES;
    }

    public Position getTOP_LEFT() {
        return TOP_LEFT;
    }

    public Position getBOTTOM_RIGHT() {
        return BOTTOM_RIGHT;
    }

    public void setTOP_LEFT(Position TOP_LEFT) {
        this.TOP_LEFT = TOP_LEFT;
    }

    public void setBOTTOM_RIGHT(Position BOTTOM_RIGHT) {
        this.BOTTOM_RIGHT = BOTTOM_RIGHT;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public void setObstacles(SquareObstacle[] listNow) {
        this.OBSTACLES = listNow;
    }

    public void setVISIBILITY(int VISIBILITY) {
        this.VISIBILITY = VISIBILITY;
    }

    public String allWorldObjects() {
        Map<String, Object> allObjects = new HashMap<>();

        allObjects.put("robots", getRobots());
        allObjects.put("obstacles", getOBSTACLES());
        allObjects.put("obstacles", "hghggh");
        Gson gson = new Gson();
        String json = gson.toJson(allObjects);
        return json;

    }
}

//    public void restoreWorld(String world_name){
//        String sql = "SELECT * FROM world_roboot WHERE world_name=?";
//
//        try(final Connection conn = DriverManager.getConnection(IN_MEMORY_DB_URL)) {
////            System.out.println("Database connected!");
//
//            try (PreparedStatement pstmt = conn.prepareStatement(sql)){
//                System.out.println("World name: " + world_name);
//                pstmt.setString(1, world_name);
////            stmt = connection.dbConnection.createStatement();
//                ResultSet rs = pstmt.executeQuery();
////            System.out.println(rs);
//
////                System.out.println("OLDS: \nWord Size: " + worldSize + "\n Obstacle at (" + obsticleXCoord + "," + obstacleYCoord + ")");
//                if(Saver.worldNameAlreadyExistsInDatabase(world_name)) {
//                    while (rs.next()) { // something is wrong here
//                        //                world_name = pstmt.get("world_name");
//                        int robotWorldSize = rs.getInt("size");
//                        int obstacle_x = rs.getInt("obstacles_x");
//                        int obstacle_y = rs.getInt("obstacles_y");
//                        System.out.println("Inside while loop");
//                        RobotServer.worldSize = robotWorldSize + 1;
//                        RobotServer.obstacleYCoord = obstacle_y + 1;
//                        RobotServer.obsticleXCoord = obstacle_x + 1;
//
//                        System.out.println("\nCongratulations! WORLD of: \n- size " + RobotServer.worldSize + "x" + RobotServer.worldSize +
//                                " and \n- obstacles at (" + RobotServer.obsticleXCoord + "," + RobotServer.obstacleYCoord + ") " +
//                                "\n was RESTORED successfully :) \n\nWhat would you like to do next?\n");
//                    }
//                } else {
//                    System.out.println("World name by the name of " + world_name + "does not exits in the database :)" +
//                            "\nWhat would you like to do next?: \n");
//                }
//
//
////                System.out.println("Previous WORLD RESTORED successfully :)");
//
//            } catch (NullPointerException e){
//                System.out.println(e.getMessage());
//            }
//
//        } catch (SQLException e) {
//            System.out.println("HERE");
//            System.out.println(e.getMessage());
//        }
//    }
//}

