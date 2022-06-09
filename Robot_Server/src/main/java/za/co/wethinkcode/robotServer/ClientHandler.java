package za.co.wethinkcode.robotServer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import za.co.wethinkcode.robotServer.ClientCommands.*;
import za.co.wethinkcode.robotServer.Robot.Robot;
import za.co.wethinkcode.robotServer.World.World;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> users = new ArrayList<>();
    public static ArrayList<Robot> robots = new ArrayList<>();
    public static World world;
    public Gson gsonPretty = new GsonBuilder()
            .setPrettyPrinting().create();
    static {
        try {
            world = new World(robots);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Gson gson = new Gson();
    RequestMessage requestMessage;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    public String clientUsername;
    ClientCommands clientCommand;
    LocalTime localTime;
    private final int shieldRepairTime = readShieldRepairTime();
    private final int reloadTime = readReloadTime();
    private boolean shieldRepairCheck;
    private boolean reloadCheck;

    public ClientHandler(Socket socket) throws FileNotFoundException {

        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            this.clientUsername = bufferedReader.readLine();
            this.shieldRepairCheck = false;
            this.reloadCheck = false;
            users.add(this);
            System.out.println("CLIENTHANDLER CALLED");
//            this.run();
//            System.out.println("CLIENTHANDLER CALLED2");

//            System.out.println("clientUsername " +clientUsername);
//            clientCommand = ClientCommands.create(bufferedReader.readLine());
//            String message = clientCommand.execute(world, requestMessage.arguments);
//            System.out.println("1");
//            broadcastMessage(message);
//            System.out.println("2");

        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
//        } catch (ClientCommands.CommandNotFoundException e) {
//            throw new RuntimeException(e);
        }
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("TOP RUN CALLED");
        String commandFromClient;
        System.out.println("Top run below");
        while (socket.isConnected()) {
            System.out.println("Inside while loop");
            try {
                System.out.println("inside try");
//                System.out.println(bufferedReader.read());
//                System.out.println(bufferedReader.readLine());

                 commandFromClient = bufferedReader.readLine(); // (receive message from RobotClient) stucks here
                System.out.println("below bufferedReader");
                System.out.println("CommandFromClient: " + commandFromClient);

//                String commandFromClient = "{\"robot\":\"HAL\",\"command\":\"lanach\",\"arguments\":[\"sniper\"]}";
                if(!repairFinished() || !reloadFinished()){
                    continue;
                }
                try {
                    clientCommand = ClientCommands.create(commandFromClient);
                    requestMessage = gson.fromJson(commandFromClient, RequestMessage.class);
                    String message = clientCommand.execute(world, requestMessage.arguments);
//                    String message = "{\"result\":\"OK\",\"data\":{\"visibility\":1,\"position\":[0,0],\"objects\":[]},\"state\":{\"position\":[0,0],\"direction\":\"NORTH\",\"shields\":0,\"shots\":0,\"status\":\"TODO\"}}";
                    bufferedWriter.write(message);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    reloadAndRepairTimerCheck();
                    if(clientCommand instanceof Quit){
                        this.closeEverything(socket,bufferedReader,bufferedWriter);
                    }
                    } catch (IllegalArgumentException e) {
                    try {
                        Forward.DataJson dataJson = new Forward.DataJson("Could not parse arguments");
                        ErrorResponseJson errorResponseJson = new ErrorResponseJson("ERROR", dataJson);
                        bufferedWriter.write(gsonPretty.toJson(errorResponseJson));
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        }catch(IOException f) {
                            System.out.println("ioexception f");
                    }
                } catch (ClientCommands.CommandNotFoundException e) {
                    Forward.DataJson dataJson = new Forward.DataJson("Unsupported command");
                    ErrorResponseJson errorResponseJson = new ErrorResponseJson("ERROR", dataJson);
                    bufferedWriter.write(gsonPretty.toJson(errorResponseJson));
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public static void broadcastMessage(String messageToBroadcast){
        for(ClientHandler clientHandler : users) {
            try {
                    clientHandler.bufferedWriter.write(messageToBroadcast);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
            }catch(IOException e) {
            }
        }
    }

    public int readReloadTime(){
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("Config.json");
            ConfigFileJson json = gson.fromJson(fileReader, ConfigFileJson.class);
            return json.getReloadTime();
        } catch (FileNotFoundException e) {
            System.out.println("No config file present");
        }
        return 5;
    }

    public int readShieldRepairTime(){
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("Config.json");
            ConfigFileJson json = gson.fromJson(fileReader, ConfigFileJson.class);
            return json.getShieldRepairTime();
        } catch (FileNotFoundException e) {
            System.out.println("No config file present");
        }
        return 5;
    }

    public boolean reloadFinished() throws IOException {
        if(reloadCheck){
            if(LocalTime.now().compareTo(localTime.plusSeconds(reloadTime)) == -1) {
                String stillReloading = "Your robot is still reloading please wait";
                bufferedWriter.write(stillReloading);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                return false;
            }
        }
        reloadCheck = false;
        return true;
    }

    public boolean repairFinished() throws IOException{
        if(shieldRepairCheck){
            if(LocalTime.now().compareTo(localTime.plusSeconds(shieldRepairTime)) == -1){
                String stillRepairing = "Your robot is still repairing please wait";
                bufferedWriter.write(stillRepairing);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                return false;
            }
        }
        shieldRepairCheck = false;
        return true;
    }

    public void reloadAndRepairTimerCheck(){
        if(clientCommand instanceof Reload){
            localTime = LocalTime.now();
            reloadCheck = true;
        }
        if(clientCommand instanceof Repair){
            localTime = LocalTime.now();
            shieldRepairCheck = true;
        }
    }

    public ArrayList<Robot> getRobots(){
        return robots;
    }

}
