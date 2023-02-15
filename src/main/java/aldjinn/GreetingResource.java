package aldjinn;

import io.quarkus.logging.Log;
import io.vertx.core.http.HttpServerRequest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/hello")
public class GreetingResource {

    @Context
    HttpServerRequest request;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HelloResponse hello() {
        Log.info(String.format("request from %s", request.connection().remoteAddress().hostAddress()));
        return new HelloResponse("Hello, world! ☕",
                UUID.randomUUID(),
                request.getHeader("User-Agent"),
                request.connection().remoteAddress().hostAddress(),
                request.host());
    }

    private record HelloResponse(String body, UUID uuid, String userAgent, String ip, String host) {
    }

}