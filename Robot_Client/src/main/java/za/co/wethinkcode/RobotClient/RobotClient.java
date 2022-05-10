package za.co.wethinkcode.RobotClient;


import za.co.wethinkcode.RobotClient.ClientCommands.ClientCommands;
import za.co.wethinkcode.RobotClient.ClientCommands.Launch;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class RobotClient {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;
    public String robotName;
    public static boolean launchCheck;


    public RobotClient(Socket socket, String userName) {
        try {
            this.socket = socket;
            this.userName = userName;
            this.robotName = "";
            this.launchCheck = false;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            // Gracefully close everything.
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        System.out.println("Server has terminated connection shutting down.");
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

    public void sendCommand(){
        String message;
        try{
            bufferedWriter.write(userName);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            Scanner scanner = new Scanner(System.in);
            while(socket.isConnected()){
                try {
                    String messageToSend = scanner.nextLine();
                    ClientCommands command = ClientCommands.create(messageToSend);
                    if(command instanceof Launch && !launchCheck){
                        robotName = command.getArgument2();
                        message = command.execute(robotName);
                        bufferedWriter.write(message);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                    else if(command instanceof Launch && launchCheck){
                        System.out.println("robot already launched");
                    }
                    else if(!(command instanceof Launch) && launchCheck){
                        message = command.execute(robotName);
                        bufferedWriter.write(message);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                    
                }catch(IllegalArgumentException e){
                    System.out.println("invalid command");
                    continue;
                }

            }
        }catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void listenForResponse(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;

                while(socket.isConnected()){
                    try{
                        msgFromGroupChat = bufferedReader.readLine();
                        if(msgFromGroupChat == null){
                            closeEverything(socket, bufferedReader, bufferedWriter);
                        }
                        System.out.println(msgFromGroupChat);

                    }catch(IOException e){
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        if (args.length< 2){
            return;
        }
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        System.out.print("Welcome to team CPT18 client please enter your username ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Thank you " + username + " please launch your robot when you are ready.");
        // Create a socket to connect to the server.
        Socket socket = new Socket(hostname, port);
        // Pass the socket and give the client a username.
        RobotClient robotClient = new RobotClient(socket, username);
        // Infinite loop to read and send messages.
        robotClient.listenForResponse();
        robotClient.sendCommand();
    }

}
