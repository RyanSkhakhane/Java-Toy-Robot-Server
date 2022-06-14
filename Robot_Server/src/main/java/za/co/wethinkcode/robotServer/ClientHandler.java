package za.co.wethinkcode.robotServer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import za.co.wethinkcode.robotServer.ClientCommandsTest.*;
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
//            ClientHandler.robots.clear();


        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        System.out.println("closeEverything ran!");
        ClientHandler.robots.clear();
//        ClientHandler.robots.size() = 0;
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
        System.exit(0);

    }

    @Override
    public void run() {
        String commandFromClient;
        while (socket.isConnected()) {
            try {
                 commandFromClient = bufferedReader.readLine(); // (receive message from RobotClient) stucks here

                if(!repairFinished() || !reloadFinished()){
                    continue;
                }
                try {
                    clientCommand = ClientCommands.create(commandFromClient);
                    requestMessage = gson.fromJson(commandFromClient, RequestMessage.class);
                    String message = clientCommand.execute(world, requestMessage.arguments);
                    bufferedWriter.write(message);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    reloadAndRepairTimerCheck();
                    if(clientCommand instanceof Quit){
                        this.closeEverything(socket,bufferedReader,bufferedWriter);
                    } else if (clientCommand.equals(null)) {
                        this.closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                    } catch (IllegalArgumentException e) {
                    this.closeEverything(socket, bufferedReader, bufferedWriter);

                    try {
                        Forward.DataJson dataJson = new Forward.DataJson("Could not parse arguments");
                        ErrorResponseJson errorResponseJson = new ErrorResponseJson("ERROR", dataJson);
                        bufferedWriter.write(gsonPretty.toJson(errorResponseJson));
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        }catch(IOException f) {
                            System.out.println("ioexception f");
                            this.closeEverything(socket, bufferedReader, bufferedWriter);
                            break;
                    }
                } catch (ClientCommands.CommandNotFoundException e) {
                    Forward.DataJson dataJson = new Forward.DataJson("Unsupported command");
                    ErrorResponseJson errorResponseJson = new ErrorResponseJson("ERROR", dataJson);
                    bufferedWriter.write(gsonPretty.toJson(errorResponseJson));
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    this.closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }
            } catch (IOException e) {
                this.closeEverything(socket, bufferedReader, bufferedWriter);
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
//                this.closeEverything(socket, bufferedReader, bufferedWriter);
                System.out.println("BroadcastMessage catch");
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
