package de.aldjinn;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.vertx.core.http.HttpServerRequest;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Map;
import java.util.UUID;


@Path("/")
public class IndexResource {

    @Context
    HttpServerRequest request;

    @Inject
    Template index;

    @Inject
    @ConfigProperty(name = "totally-useless.build.timestamp")
    String buildTimestamp;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        var attributes = Map.of("userAgent", request.getHeader("User-Agent"),
                "uuid", UUID.randomUUID(),
                "hostAddress", request.connection().remoteAddress().hostAddress(),
                "host", request.host(),
                "buildTimestamp", buildTimestamp);
        return index.data(attributes);
    }

}