package za.co.wethinkcode.robotServer;

import com.google.gson.Gson;
import za.co.wethinkcode.robotServer.ClientCommands.ClientCommands;
import za.co.wethinkcode.robotServer.ClientCommands.RequestMessage;
import za.co.wethinkcode.robotServer.ServerCommands.Quit;
import za.co.wethinkcode.robotServer.ServerCommands.ServerCommand;
import za.co.wethinkcode.robotServer.World.World;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> users = new ArrayList<>();
    public static ArrayList<Robot> robots = new ArrayList<>();
    World world = new World(robots);
    Gson gson = new Gson();
    RequestMessage requestMessage;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    ServerCommand command;
    ClientCommands clientCommand;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // When a client connects their username is sent.
            this.clientUsername = bufferedReader.readLine();
            // Add the new client handler to the array, so they can receive messages from others.
            users.add(this);
        } catch (IOException e) {
            // Close everything more gracefully.
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

        // The client disconnected or an error occurred so remove them from the list so no message is broadcasted.
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
                System.out.println("reader closed");
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
                System.out.println("writer closed");
            }
            if (socket != null) {
                socket.close();
                System.out.println("socket closed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void run() {
        String commandFromClient;
        // Continue to listen for messages while a connection with the client is still established.
        while (socket.isConnected()) {
            try {
                // Read what the client sent and then send it to every other client.
                commandFromClient = bufferedReader.readLine();
                System.out.println(commandFromClient);
//                broadcastMessage(commandFromClient);
                if(adminCheck(this)) {
                    System.out.println("admin access requested granting admin commands");
                    try {
                        command = ServerCommand.create(commandFromClient);
                        if(command instanceof Quit){

                        }
                        command.execute(users, robots, world);
                    } catch (IllegalArgumentException e) {
                        bufferedWriter.write("This argument is not recognised)");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }
                else{
                        try {
                            clientCommand = ClientCommands.create(commandFromClient);
                            requestMessage = gson.fromJson(commandFromClient, RequestMessage.class);
                            String message = clientCommand.execute(this, world, requestMessage.arguments);
                            bufferedWriter.write(message);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        } catch (IllegalArgumentException e) {
                            try {
                                bufferedWriter.write("This argument is not recognised)");
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                            }catch(IOException f) {
                                System.out.println("ioexception f");
                        }

                }
                }
                 // here we can handleCommand
            } catch (IOException e) {
                // Close everything gracefully.
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String messageToBroadcast){
        for(ClientHandler clientHandler : users) {
            try {
                    clientHandler.bufferedWriter.write(messageToBroadcast);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
            }catch(IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }


    public void addRobot(Robot robot){
        robots.add(robot);
    }


    public ArrayList<Robot> getRobots(){
        return robots;
    }

    public String getClientUsername(){
        return clientUsername;
    }

    public Socket getSocket(){
        return socket;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public BufferedWriter getBufferedWriter(){
        return bufferedWriter;
    }

    public boolean adminCheck(ClientHandler clientHandler){
        if(clientHandler.getClientUsername().equals("admin")){
            return true;
        }
        return false;
    }
}
