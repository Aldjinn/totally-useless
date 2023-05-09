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

    @Test
    public void testIpEndpoint() {
        given()
                .when().get("/ip")
                .then()
                .statusCode(200)
                .body(containsString("127.0.0.1"));
    }

    @Test
    public void testIpJsonEndpoint() {
        given()
                .when().get("/ip/json")
                .then()
                .statusCode(200)
                .body(containsString("Apache-HttpClient"));
    }

    @Test
    public void testHeadersJsonEndpoint() {
        given()
                .when().get("/headers")
                .then()
                .statusCode(200)
                .body(containsString("Apache-HttpClient"));
    }

    @Test
    public void testNotFoundEndpoint() {
        given()
                .when().get("/not/found")
                .then()
                .statusCode(404);
    }

}