package de.aldjinn;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class IndexResourceTest {

    @Test
    public void testIndexEndpoint() {
        given()
                .when().get("/")
                .then()
                .statusCode(200)
                .body(containsString("Under Maintenance"));
    }

    public void testIpEndpoint() {
        given()
                .when().get("/ip")
                .then()
                .statusCode(200);
    }

    public void testIpJsonEndpoint() {
        given()
                .when().get("/ip/json")
                .then()
                .statusCode(200);
    }

    public void testNotFoundEndpoint() {
        given()
                .when().get("/not/found")
                .then()
                .statusCode(404);
    }

}