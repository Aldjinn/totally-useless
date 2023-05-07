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
import org.jboss.logging.Logger;

import java.text.DateFormat;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;


@Path("/")
public class IndexResource {

    private static final Logger LOG = Logger.getLogger(IndexResource.class);

    @Context
    HttpServerRequest request;

    @Inject
    Template index;

    @Inject
    @ConfigProperty(name = "totally-useless.build.timestamp")
    String buildTimestamp;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getIndex() {
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
        var objectMapper = new ObjectMapper();
        var json = objectMapper.writeValueAsString(getHeaders());
        LOG.info(json);
        return json;
    }

    private Map<String, String> getHeaders() {
        var map = new TreeMap<String, String>();
        map.put("Timestamp", DateFormat.getDateTimeInstance().format(System.currentTimeMillis()));
        addToMapIfPresent(map, "User-Agent");
        addToMapIfPresent(map, "X-Forwarded-For");
        addToMapIfPresent(map, "X-Real-Ip");
        return map;
    }

    private void addToMapIfPresent(Map<String, String> map, String key) {
        if (!StringUtil.isNullOrEmpty(request.getHeader(key))) {
            map.put(key, request.getHeader(key));
        }
    }

}