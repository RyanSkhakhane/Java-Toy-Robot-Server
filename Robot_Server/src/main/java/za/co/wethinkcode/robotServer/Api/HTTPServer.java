package za.co.wethinkcode.robotServer.Api;

import com.google.gson.Gson;
import io.javalin.Javalin;
import io.javalin.http.Context;
import za.co.wethinkcode.robotServer.Database.DbConnect;
import za.co.wethinkcode.robotServer.Database.Restore;
import za.co.wethinkcode.robotServer.RobotWorld.ClientCommands.ClientCommands;
import za.co.wethinkcode.robotServer.RobotWorld.Robot.Robot;
import za.co.wethinkcode.robotServer.RobotWorld.World.World;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class HTTPServer {
    private static Gson gson;

    public static void main(String[] args) {


        String database = "-f world.db";
        String[] dbh=database.split(" ");
        DbConnect db=new DbConnect(dbh);

        Restore r=new Restore();
        try {
            r.useTheDb(db.dbConnection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Javalin server = Javalin.create()
                .start(7000);
        server.routes(() -> {
            path("/launch", HTTPServer::luanch);
            path("/forward", HTTPServer::forward);
            path("/back", HTTPServer::Back);
            path("/fire", HTTPServer::Fire);
            path("/look", HTTPServer::Look);
            path("/reload", HTTPServer::Reload);
            path("/repair", HTTPServer::Repair);
            path("/state", HTTPServer::State);
            path("/turn", HTTPServer::Turn);
            path("/unsupportedcommand", HTTPServer::UnsupportedCommand);
            path("/requestmessage", HTTPServer::RequestMessage);
            path("/quit", HTTPServer::Quit);

        });
    }

    public static void forward() {
        path("/", () -> get(HTTPServer::doForward));
    }

    private static void doForward(Context context) throws ClientCommands.CommandNotFoundException, FileNotFoundException {
        ClientCommands clientCommand = ClientCommands.create(context.body());
        ArrayList<Robot> robots = new ArrayList<>();
        World world = new World(robots);
        String message = clientCommand.execute(world, new String[]{"1"});
        context.json(message);
    }
    public static void luanch() {path("/", () -> get(HTTPServer::doLuanch));
    }

    private static void doLuanch(Context context) throws ClientCommands.CommandNotFoundException, FileNotFoundException {
        ClientCommands clientCommand = ClientCommands.create(context.body());
        ArrayList<Robot> robots = new ArrayList<>();
        World world = new World(robots);
        String message = clientCommand.execute(world, new String[]{"1"});
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
    public static void Fire() {
        path("/", () -> get(HTTPServer::doFire));
    }

    private static void doFire(Context context) throws ClientCommands.CommandNotFoundException, FileNotFoundException {
        ClientCommands clientCommand = ClientCommands.create(context.body());
        ArrayList<Robot> robots = new ArrayList<>();
        World world = new World(robots);
        String message = clientCommand.execute(world, new String[]{"10"});
        context.json(message);
    }

    public static void Look() {
        path("/", () -> get(HTTPServer::doLook));
    }

    private static void doLook(Context context) throws ClientCommands.CommandNotFoundException, FileNotFoundException {
        ClientCommands clientCommand = ClientCommands.create(context.body());
        ArrayList<Robot> robots = new ArrayList<>();
        World world = new World(robots);
        String message = clientCommand.execute(world, new String[]{"10"});
        context.json(message);
    }

    public static void Reload() {
        path("/", () -> get(HTTPServer::doReload));
    }

    private static void doReload(Context context) throws ClientCommands.CommandNotFoundException, FileNotFoundException {
        ClientCommands clientCommand = ClientCommands.create(context.body());
        ArrayList<Robot> robots = new ArrayList<>();
        World world = new World(robots);
        String message = clientCommand.execute(world, new String[]{"10"});
        context.json(message);
    }
    public static void Repair() {
        path("/", () -> get(HTTPServer::doRepair));
    }

    private static void doRepair(Context context) throws ClientCommands.CommandNotFoundException, FileNotFoundException {
        ClientCommands clientCommand = ClientCommands.create(context.body());
        ArrayList<Robot> robots = new ArrayList<>();
        World world = new World(robots);
        String message = clientCommand.execute(world, new String[]{"10"});
        context.json(message);
    }
    public static void State() {
        path("/", () -> get(HTTPServer::doState));
    }

    private static void doState(Context context) throws ClientCommands.CommandNotFoundException, FileNotFoundException {
        ClientCommands clientCommand = ClientCommands.create(context.body());
        ArrayList<Robot> robots = new ArrayList<>();
        World world = new World(robots);
        String message = clientCommand.execute(world, new String[]{"10"});
        context.json(message);
    }
    public static void Turn() {
        path("/", () -> get(HTTPServer::doTurn));
    }

    private static void doTurn(Context context) throws ClientCommands.CommandNotFoundException, FileNotFoundException {
        ClientCommands clientCommand = ClientCommands.create(context.body());
        ArrayList<Robot> robots = new ArrayList<>();
        World world = new World(robots);
        String message = clientCommand.execute(world, new String[]{"10"});
        context.json(message);
    }
    public static void UnsupportedCommand() {
        path("/", () -> get(HTTPServer::doUnsupportedCommand));
    }

    private static void doUnsupportedCommand(Context context) throws ClientCommands.CommandNotFoundException, FileNotFoundException {
        ClientCommands clientCommand = ClientCommands.create(context.body());
        ArrayList<Robot> robots = new ArrayList<>();
        World world = new World(robots);
        String message = clientCommand.execute(world, new String[]{"10"});
        context.json(message);
    }
    public static void RequestMessage() {
        path("/", () -> get(HTTPServer::doRequestMessage));
    }

    private static void doRequestMessage(Context context) throws ClientCommands.CommandNotFoundException, FileNotFoundException {
        ClientCommands clientCommand = ClientCommands.create(context.body());
        ArrayList<Robot> robots = new ArrayList<>();
        World world = new World(robots);
        String message = clientCommand.execute(world, new String[]{"10"});
        context.json(message);
    }
    public static void Quit() {
        path("/", () -> get(HTTPServer::doQuit));
    }

    private static void doQuit(Context context) throws ClientCommands.CommandNotFoundException, FileNotFoundException {
        ClientCommands clientCommand = ClientCommands.create(context.body());
        ArrayList<Robot> robots = new ArrayList<>();
        World world = new World(robots);
        String message = clientCommand.execute(world, new String[]{"10"});
        context.json(message);
    }
}