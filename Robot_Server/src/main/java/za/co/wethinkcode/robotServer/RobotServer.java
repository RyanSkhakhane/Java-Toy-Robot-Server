package za.co.wethinkcode.robotServer;

import za.co.wethinkcode.robotServer.ServerCommands.ServerCommand;

import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.Scanner;

public class RobotServer {

    private final Scanner scanner;
    private final ServerSocket serverSocket;


    public RobotServer(ServerSocket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
        this.scanner = new Scanner(System.in);
    }

    public void startServer(){
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("A new player has connected to the server.");
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

    public static void main(String[] args) throws IOException {
        int port = portChoice();
        myIp();
        ServerSocket serverSocket = new ServerSocket(port);
        RobotServer server = new RobotServer(serverSocket);
        System.out.println("Server configuration successful starting server :)");
        server.startServer();
    }

}
