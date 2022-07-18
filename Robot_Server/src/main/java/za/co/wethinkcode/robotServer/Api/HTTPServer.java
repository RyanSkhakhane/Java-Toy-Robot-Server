package za.co.wethinkcode.robotServer.Api;

import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class HTTPServer {
    public static void main(String[] args) {
        Javalin server = Javalin.create()
                .start(7000);
        server.routes(() -> path("/forward", () -> get(Route::forward)));
    }
}
