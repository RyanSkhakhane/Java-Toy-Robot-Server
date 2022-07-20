package za.co.wethinkcode.robotServer.Api;

import com.google.gson.Gson;
import io.javalin.Javalin;
import io.javalin.http.Context;
import za.co.wethinkcode.robotServer.RobotWorld.ClientCommands.ClientCommands;
import za.co.wethinkcode.robotServer.RobotWorld.Robot.Robot;
import za.co.wethinkcode.robotServer.RobotWorld.World.World;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class HTTPServer {
    private static Gson gson;

    public static void main(String[] args) {
        Javalin server = Javalin.create()
                .start(7000);
        server.routes(() -> {
            path("/forward", HTTPServer::forward);
            path("/back", HTTPServer::Back);
        });
    }

    public static void forward() {
        path("/", () -> get(HTTPServer::doForward));
    }

    private static void doForward(Context context) throws ClientCommands.CommandNotFoundException, FileNotFoundException {
        ClientCommands clientCommand = ClientCommands.create(context.body());
        ArrayList<Robot> robots = new ArrayList<>();
        World world = new World(robots);
        String message = clientCommand.execute(world, new String[]{"10"});
        context.json(message);
    }

    public static void Back() {
        path("/", () -> get(HTTPServer::doBack));
    }

    private static void doBack(Context context) throws ClientCommands.CommandNotFoundException, FileNotFoundException {
        ClientCommands clientCommand = ClientCommands.create(context.body());
        ArrayList<Robot> robots = new ArrayList<>();
        World world = new World(robots);
        String message = clientCommand.execute(world, new String[]{"10"});
        context.json(message);
    }
}