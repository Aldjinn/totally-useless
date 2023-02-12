package aldjinn;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(containsString("Hello, world!"));
    }

    @Test
    public void testIndexEndpoint() {
        given()
                .when().get("/")
                .then()
                .statusCode(200)
                .body(containsString("Under Maintenance"));
    }

}