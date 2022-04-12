package za.co.wethinkcode.robotServer;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> users = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // When a client connects their username is sent.
            this.clientUsername = bufferedReader.readLine();
            // Add the new client handler to the array so they can receive messages from others.
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
        String commandFromClient;
        // Continue to listen for messages while a connection with the client is still established.
        while (socket.isConnected()) {
            try {
                // Read what the client sent and then send it to every other client.
                commandFromClient = bufferedReader.readLine();
                 // here we can handleCommand
            } catch (IOException e) {
                // Close everything gracefully.
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }
}
