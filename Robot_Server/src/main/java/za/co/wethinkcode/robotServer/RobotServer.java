package za.co.wethinkcode.robotServer;

import com.google.gson.Gson;
import za.co.wethinkcode.robotServer.ServerCommands.Quit;
import za.co.wethinkcode.robotServer.ServerCommands.ServerCommand;
import za.co.wethinkcode.robotServer.World.SquareObstacle;

import java.io.*;
import java.net.*;
import java.util.*;

public class RobotServer {

    private ServerInput serverInput;
    private final ServerSocket serverSocket;


    public RobotServer(ServerSocket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
        this.serverInput = new ServerInput();
    }

    public void startServer(){
        serverInput.start();
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("A new user has connected to the server.");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }

        }catch (IOException e) {
            closeServerSocket();
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int portChoice(){
        Scanner scanner = new Scanner(System.in);
        String portChosen;
        System.out.println("What Port would you like to use(4 digit number)");
        while(true){
            portChosen = scanner.nextLine();
            if(portCheck(portChosen)){
                break;
            }
        }
        return Integer.parseInt(portChosen);
    }

    public static void myIp() {
        String ip;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;
                System.out.println("Here are you ip addresses for users to connect to: ");
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    ip = addr.getHostAddress();
                    System.out.println(iface.getDisplayName() + " " + ip);
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean portCheck(String portChoice){
        try {
            Integer.parseInt(portChoice);
            if(portChoice.length() != 4){
                System.out.println("4 Digits are required please choose a valid port");
                return false;
            }

            return true;

        } catch (NumberFormatException e) {
            System.out.println("you need to input a 4 digit number.");
        }

        return false;
    }

    public static void fileConfig(){
        ConfigFileJson.GridJson gridJson;
        Gson gson = new Gson();
        gridJson = mapSizeChooser();
        int visibility = integerChooser("visibility");
        SquareObstacle[] obstaclesList = obstacleChooser(gridJson);
        int shieldRepairTime = integerChooser("shield repair time");
        int reloadTime = integerChooser("reload time");
        int maxShieldStrength = integerChooser("max shield strength");
        String json = gson.toJson(new ConfigFileJson(gridJson,visibility,obstaclesList,
                shieldRepairTime,reloadTime,maxShieldStrength));
        System.out.println("Thank you server configuration being set up.");
        try (FileWriter file = new FileWriter("Config.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File myObj = new File("Config.json");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static ConfigFileJson.GridJson mapSizeChooser(){
        Scanner scanner = new Scanner(System.in);
        String[] mapSizes = {"small", "medium" , "large"};
        int x;
        int y;
        String mapSize;
        System.out.println("First how large would you like the grid?\n" +
                "small(10 by 10) , medium(30 by 30) or large(50 by 50).");
        do{
            mapSize = scanner.nextLine();
        }while(!Arrays.asList(mapSizes).contains(mapSize));
        switch (mapSize){
            case "small":
                return new ConfigFileJson.GridJson(5,5);
            case "medium":
                return new ConfigFileJson.GridJson(15,15);
            case "large":
                return new ConfigFileJson.GridJson(25,25);
        }
        System.out.println("Default of small selected.");
        return new ConfigFileJson.GridJson(10,10);
    }

    static int integerChooser(String configTypeChoice){
        String answer;
        boolean passes = false;
        int steps = 5;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Next what "+ configTypeChoice + " would you like?");
        do{
            answer = scanner.nextLine();
            try{
                steps = Integer.parseInt(answer);
                passes = true;
            }catch(Exception e){
                System.out.println("Please input a digit.");
            }
        }while(!passes);
        return steps;
    }

    static SquareObstacle[] obstacleChooser(ConfigFileJson.GridJson mapSize){
        Scanner scanner = new Scanner(System.in);
        String obstaclesChoice;
        String[] obstaclesChoices = {"none", "one" , "several", "ten", "twenty"};
        System.out.println("Next how many obstacles would you like in the world? " +
                "(None),(One),(Several),(Ten),(Twenty)");
        do{
            obstaclesChoice = scanner.nextLine();
        }while(!Arrays.asList(obstaclesChoices).contains(obstaclesChoice));

        switch (obstaclesChoice.toLowerCase()){
            case "none":
                return obstaclesMaker(0, mapSize.x,mapSize.y);
            case "one":
                return obstaclesMaker(1, mapSize.x,mapSize.y);
            case "several":
                return obstaclesMaker(3, mapSize.x,mapSize.y);
            case "ten":
                return obstaclesMaker(10, mapSize.x,mapSize.y);
            case "twenty":
                return obstaclesMaker(20, mapSize.x,mapSize.y);

        }
        return new SquareObstacle[0];
    }

    static SquareObstacle[] obstaclesMaker(int obstaclesAmount , int xSize, int ySize){
        Random random = new Random();
        boolean sharesPosition;
        SquareObstacle[] obstacles = new SquareObstacle[obstaclesAmount];
        ArrayList<SquareObstacle> obstaclesArrayList = new ArrayList<>();
        while(obstaclesArrayList.size()!= obstaclesAmount){
            sharesPosition = false;
            SquareObstacle newObstacle = new SquareObstacle((random.nextInt(xSize/2 - (-xSize/2)) + (-xSize/2)),
                    random.nextInt(ySize/2 - (-ySize/2)) + (-ySize/2));
            for(SquareObstacle squareObstacle : obstaclesArrayList){
               sharesPosition = sharesPosition(squareObstacle, newObstacle);
            }
            if(sharesPosition){
                continue;
            }
            obstaclesArrayList.add(newObstacle);
        }
        obstaclesArrayList.toArray(obstacles);
        for(SquareObstacle squareObstacle : obstacles){
            System.out.println("x" + squareObstacle.getBottomLeftX() + "y " + squareObstacle.getBottomLeftY());
        }
        return obstacles;
    }

    static boolean sharesPosition(SquareObstacle existingObstacle , SquareObstacle newObstacle){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(newObstacle.getBottomLeftX() + i == existingObstacle.getBottomLeftX() + j) {
                    return true;
                }
                else if(newObstacle.getBottomLeftY() + i == existingObstacle.getBottomLeftY() + j){
                        return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Robot Worlds Server Cpt18 please take a moment to configure server settings.");
        int port = portChoice();
        myIp();
        fileConfig();
        ServerSocket serverSocket = new ServerSocket(port);
        RobotServer server = new RobotServer(serverSocket);
        System.out.println("Server configuration successful starting server :)");
        server.startServer();
    }



    private static class ServerInput extends Thread {
        Scanner scanner;
        ServerCommand command;
        public ServerInput() {
            this.scanner = new Scanner(System.in);
        }

        @Override
        public void run() {
            super.run();
            while (true) {
                String serverCommand = scanner.nextLine();
                try {
                    command = ServerCommand.create(serverCommand);
                    command.execute(ClientHandler.users, ClientHandler.robots, ClientHandler.world);
                } catch (IllegalArgumentException e) {
                    System.out.println("Command is unrecognised");
                }

            }
        }
    }
}
