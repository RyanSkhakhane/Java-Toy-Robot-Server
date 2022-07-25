package za.co.wethinkcode.robotServer.ServerCommunication.ServerCommands;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestDescriptor;
import za.co.wethinkcode.robotServer.RobotWorld.Robot.Normal;
import za.co.wethinkcode.robotServer.RobotWorld.Robot.Robot;
import za.co.wethinkcode.robotServer.RobotWorld.RobotWorldClient;
import za.co.wethinkcode.robotServer.RobotWorld.RobotWorldJsonClient;
import za.co.wethinkcode.robotServer.RobotWorld.World.World;
import za.co.wethinkcode.robotServer.ServerCommunication.ClientHandler;
import za.co.wethinkcode.robotServer.ServerCommunication.RobotServer;

import static org.junit.jupiter.api.Assertions.*;

class RestorerTest  {
    Socket socket;
    private final static int DEFAULT_PORT = 5000;
    private final static String DEFAULT_IP = "localhost";
    private final RobotWorldClient serverClient = new RobotWorldJsonClient();
    Robot robot = new Normal(world,"VAL","normal");

    ServerSocket serverSocket;

    {
        try {
            serverSocket = new ServerSocket(5000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Robot> robots = new ArrayList<>();
    public static World world;
    public Gson gsonPretty = new GsonBuilder()
            .setPrettyPrinting().create();
    static {

    }

    RobotServer rs = new RobotServer(serverSocket);

    @BeforeEach
    void startAndConnectToServer()throws IOException {
        try {
            world = new World(robots);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        serverClient.connect(DEFAULT_IP, DEFAULT_PORT);
    }
//
    @AfterEach
    void disconnectAndCloseServer(){
//        serverClient.disconnect();
//        rs.closeServerSocket();
//
    }


    @Test
    void testConnection(){

        assertTrue(serverClient.isConnected());

        // When I send a valid launch request to the server
        String request = "{" +
                "  \"robot\": \"VAL\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response = serverClient.sendRequest(request);
        assertNotNull(response.get("result"));
    }

    @Test
    void testExecute() throws SQLException, IOException {

        Socket socket = serverSocket.accept();
        ClientHandler clientHandler = new ClientHandler(socket);
        Restorer rst = new Restorer();
        ArrayList<ClientHandler> usrs = new ArrayList<>();
        usrs.add(clientHandler);
        robots.add(robot);


        String expectedOut = "What world name would like to RESTORE? \n" +
                "World name: ryan\n" +
                "World name by the name of ryandoes not exits in the database :)\n" +
                "What would you like to do next?: \n\n";


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("ryan\nquit\n".getBytes());
        System.setIn(byteArrayInputStream);
        System.setOut(new PrintStream(byteArrayOutputStream));
        rst.execute(usrs,robots,world);
        int numberOfRobots = RobotServer.getNumberOfRobots();
        int Xcoord = RobotServer.getObsticleXCoord();
        int Ycoord = RobotServer.getObstacleYCoord();
        String worldName = RobotServer.getWorldName();
        int wolrdSize = RobotServer.getWorldSize();

        assertNotNull(byteArrayInputStream);
        assertEquals(expectedOut, byteArrayOutputStream.toString());

        assertNotNull(wolrdSize);
        assertEquals(2, wolrdSize);


        assertNotNull(worldName);
        assertEquals("currentWorld", worldName);

        assertNotNull(Xcoord);
        assertNotNull(Ycoord);
        assertEquals(0,Ycoord);
        assertEquals(0,Xcoord);

        assertEquals(0, numberOfRobots );


    }

}

