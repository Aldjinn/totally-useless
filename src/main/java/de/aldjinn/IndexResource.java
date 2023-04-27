package de.aldjinn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.runtime.util.StringUtil;
import io.vertx.core.http.HttpServerRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashMap;
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
                "host", request.host(),
                "buildTimestamp", buildTimestamp);
        return index.data(attributes);
    }

    @GET
    @Path("/ip")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRemoteIp() {
        var xForwardedFor = request.getHeader("X-Forwarded-For");
        if (StringUtil.isNullOrEmpty(xForwardedFor)) {
            return "127.0.0.1";
        }
        return xForwardedFor;
    }

    @GET
    @Path("/ip/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRemoteIpJson() throws JsonProcessingException {
        var xForwardedFor = request.getHeader("X-Forwarded-For");
        var xRealIp = request.getHeader("X-Real-Ip");

        var map = new HashMap<String, String>();

        if (!StringUtil.isNullOrEmpty(xForwardedFor)) {
            map.put("X-Forwarded-For", xForwardedFor);
        }

        if (!StringUtil.isNullOrEmpty(xRealIp)) {
            map.put("X-Real-Ip", xRealIp);
        }

        var objectMapper = new ObjectMapper();
        var json = objectMapper.writeValueAsString(map);

        return json;
    }

}