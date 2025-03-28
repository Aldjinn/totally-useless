package de.aldjinn;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasKey;

@QuarkusTest
class IndexResourceTest {

    @Test
    void testIndexEndpoint() {
        given()
                .when().get("/")
                .then()
                .statusCode(200)
                .body(containsString("Under Maintenance"));
    }

    @Test
    void testIpEndpoint() {
        given()
                .when().get("/ip")
                .then()
                .statusCode(200)
                .body(containsString("127.0.0.1"));
    }

    @Test
    void testIpJsonEndpoint() {
        given()
                .when().get("/ip/json")
                .then()
                .statusCode(200)
                .body(containsString("Timestamp"))
                .body(containsString("User-Agent"));
    }

    @Test
    void testHeadersJsonEndpoint() {
        given()
                .when().get("/headers")
                .then()
                .statusCode(200)
                .body("$", hasKey("User-Agent"))
                .body("$", hasKey("Host"));
    }

    @Test
    void testNotFoundEndpoint() {
        given()
                .when().get("/not/found")
                .then()
                .statusCode(404);
    }

}