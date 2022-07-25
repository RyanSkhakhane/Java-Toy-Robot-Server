package za.co.wethinkcode.robotServer.Api;

import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class ApiServer {
    private final Javalin server;

    public ApiServer() {
        ApiHandler apiHandler = new ApiHandler();
        server = Javalin.create(config -> {
            // Since we are only supporting JSON, we configure our Web API server
            // to set the content type in the HTTP response headers to default to application/json.
            config.defaultContentType = "application/json";
        });

        // This handles GET /quotes by asking QuoteApiHandler to do the work
        // of parsing the request and building the response.
        this.server.get("/world",
//                context -> ()
//                context -> context.result("All Objects in the current world returned as JSON"));
                context -> apiHandler.defaultWorld(context));

        // This handles GET /quote/{id} by delegating the work to ApiHandler. Notice the convention used to
        // specify a replacable parameter {id} that is part of the URI. This is peculiar to Javalin and is not part
        // of the Servlet API nor the HTTP specification. Though, many web API frameworks have adopted this convention.
        // For example, if we wanted to get the quote with the id of 3 then we will send an HTTP GET message
        // to /quote/3. The Javalin framework provides functionality to parse the URI to get the id of the quote.
        // We will see this in action shortly.
//        this.server.get("/world/{worldName}", // '{}' are very important to have
////                context -> context.result("Restore database objects for specified world and return as JSON {worldName}"));
//                context -> apiHandler.restoreWorld(context));

        // This handles POST /quotes with the assumption that the body of the HTTP request contains a JSON
        // representation of the quote to add. The QuoteServer doesn’t take on the responsibility of checking if the
        // HTTP request actually has a quote. That responsibility is taken on by ApiHandler.create().
//        this.server.post("/quotes", context -> ApiHandler.create(context));
    }

    public static void main(String[] args) {
        ApiServer server = new ApiServer();
        server.start(5000);
    }

    public void start(int port) {
        this.server.start(port);
    }

    public void stop() {
        this.server.stop();
    }
}
