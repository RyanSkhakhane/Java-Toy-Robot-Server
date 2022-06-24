package za.co.wethinkcode.robotServer;

import com.google.gson.Gson;
import za.co.wethinkcode.robotServer.ServerCommands.ServerCommand;
import za.co.wethinkcode.robotServer.World.SquareObstacle;

import java.io.*;
import java.net.*;
import java.util.*;

public class RobotServer {
    public static int size = 3;
    public  static String obsPos = "1,1";
    public static  int xcoord;
    public static int ycoord;

    public static String[] Args;

    private final ServerInput serverInput;
    private final ServerSocket serverSocket;
    public static int numberOfRobots = 0;
//    public static SquareObstacle[] worldObstacles;

    public RobotServer(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
        this.serverInput = new ServerInput();
    }

    public void startServer(){
        serverInput.start();
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();

                System.out.println("\n----------------------------------------------");
                System.out.println("      A New User Has Connected To The Server       ");
                System.out.println("----------------------------------------------\n");


                numberOfRobots+=1;
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
//                System.out.println("Server Socket Has Been Closed");
//                System.out.println("\n----------------------------------");
//                System.out.println("\nServer Socket Has Been Closed\n");
//                System.out.println("----------------------------------\n");
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
        do {
            portChosen = scanner.nextLine();
        } while (!portCheck(portChosen));
        return Integer.parseInt(portChosen);
    }

    public static void myIp() {
        String ip;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iFace = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iFace.isLoopback() || !iFace.isUp())
                    continue;
                System.out.println("Here are you ip addresses for users to connect to: ");
                Enumeration<InetAddress> addresses = iFace.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    ip = address.getHostAddress();
                    System.out.println(iFace.getDisplayName() + " " + ip);
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
//        System.out.println("\n----------------------------------");
//        System.out.println("\nFile Config function ran\n");
//        System.out.println("----------------------------------\n");
        ConfigFileJson.GridJson gridJson;
        Gson gson = new Gson();
        gridJson = mapSizeChooser();
        int visibility = 3; //integerChooser("visibility");
        SquareObstacle[] obstaclesList = obstacleChooser(gridJson);
        int shieldRepairTime = 3; //integerChooser("shield repair time");
        int reloadTime = 3; //integerChooser("reload time");
        int maxShieldStrength = 5; // integerChooser("max shield strength");
        String json = gson.toJson(new ConfigFileJson(gridJson,visibility,obstaclesList,
                shieldRepairTime,reloadTime,maxShieldStrength));
//        System.out.println("Thank you server configuration being set up.");
        try (FileWriter file = new FileWriter("Config.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ConfigFileJson.GridJson mapSizeChooser(){
//        Scanner scanner = new Scanner(System.in);
        String[] mapSizes = {"small", "medium" , "large"};
        String mapSize;
//        System.out.println("First how large would you like the grid?\n" +
//                "small(1 by 1) , medium(30 by 30) or large(50 by 50).");
        do{
            mapSize = "medium"; // scanner.nextLine();
        }while(!Arrays.asList(mapSizes).contains(mapSize));
        switch (mapSize){
            case "small":
                return new ConfigFileJson.GridJson(1,1);
            case "medium":
                return new ConfigFileJson.GridJson(2,2);
            case "large":
                return new ConfigFileJson.GridJson(25,25);
        }
        System.out.println("Default of medium selected.");
        return new ConfigFileJson.GridJson(2,2);
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
//        Scanner scanner = new Scanner(System.in);
        String obstaclesChoice = "none";
        String[] obstaclesChoices = {"none", "one" , "several", "ten", "twenty"};
//        System.out.println("Next how many obstacles would you like in the world? " +
//                "(None),(One),(Several),(Ten),(Twenty)");
        do{
            if(Args.length > 0){
                obstaclesChoice = "one"; //scanner.nextLine();
            }
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
//            SquareObstacle newObstacle = new SquareObstacle((random.nextInt(xSize - (-xSize)) + (-xSize)),
//                    random.nextInt(ySize - (-ySize)) + (-ySize));
            SquareObstacle newObstacle = new SquareObstacle(xcoord,ycoord);
            for(SquareObstacle squareObstacle : obstaclesArrayList){
                sharesPosition = sharesPosition(squareObstacle, newObstacle);
            }
            if(sharesPosition){
                continue;
            }
            obstaclesArrayList.add(newObstacle);
        }
        obstaclesArrayList.toArray(obstacles);
        return obstacles;
    }

    static boolean sharesPosition(SquareObstacle existingObstacle , SquareObstacle newObstacle){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
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
        System.out.println("\n=====================================================================================================");
        System.out.println("\n      W E L C O M E - - - T O - - - O U R - - - R O B O T - W O R L D  - - - S E R V E R    :)\n");
        System.out.println("======================================================================================================\n");
//        System.out.println("Enter config: \n Port | World Size | Obsticle Position\n e.g 5000 2 10,5");
//        Scanner scanner = new Scanner(System.in);

        int port = 5000;//portChoice();
        Args = args;

        // Reading program arguments assigning them and configuring accordingly
        for (int i =0; i< args.length;i++) {
            if( args[i].equals( "-o")){
                obsPos = args[i+1];
            }else if( args[i].equals("-s")){
                size = Integer.parseInt(args[i+1]);
            } else if (args[i].equals("-p")) {
                port = Integer.parseInt(args[i + 1]);
            }
        }


        String[] obs =  obsPos.split(",");
        xcoord = Integer.parseInt(obs[0]);
        ycoord = Integer.parseInt(obs[1]);


        myIp();

        fileConfig();
        ServerSocket serverSocket = new ServerSocket(port);
        RobotServer server = new RobotServer(serverSocket);
        System.out.println("Server configuration successful starting server. \nServer listening for connections on port: "+port);
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
