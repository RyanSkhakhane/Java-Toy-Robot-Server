package za.co.wethinkcode.RobotClient;

import za.co.wethinkcode.RobotClient.AdminCommands.AdminCommands;
import za.co.wethinkcode.RobotClient.ClientCommands.ClientCommands;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class RobotClient {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;


    public RobotClient(Socket socket, String userName) {
        try {
            this.socket = socket;
            this.userName = userName;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            // Gracefully close everything.
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        // Note you only need to close the outer wrapper as the underlying streams are closed when you close the wrapper.
        // Note you want to close the outermost wrapper so that everything gets flushed.
        // Note that closing a socket will also close the socket's InputStream and OutputStream.
        // Closing the input stream closes the socket. You need to use shutdownInput() on socket to just close the input stream.
        // Closing the socket will also close the socket's input stream and output stream.
        // Close the socket after closing the streams.
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
                    if(userName.equals("admin")){
                        AdminCommands adminCommand = AdminCommands.create(messageToSend);
                         message = adminCommand.execute();
                    }else{
                        ClientCommands command = ClientCommands.create(messageToSend);
                         message = command.execute();
                    }
                    System.out.println(message);
                    bufferedWriter.write(message);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }catch(IllegalArgumentException e){
                    System.out.println("invalid command");
                    continue;
                }

            }
        }catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    public static void main(String[] args) throws IOException {

        // Get a username for the user and a socket connection.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username for the group chat: ");
        String username = scanner.nextLine();
        // Create a socket to connect to the server.
        Socket socket = new Socket("10.200.108.209", 1234);

        // Pass the socket and give the client a username.
        RobotClient robotClient = new RobotClient(socket, username);
        // Infinite loop to read and send messages.
        robotClient.sendCommand();
    }

}
