package aldjinn;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Message hello() {
        return new Message("Hello, world! " + UUID.randomUUID());
    }

    private record Message(String body) {
    }

}