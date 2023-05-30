import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class CRUD {

    private static String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private static String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private static String url = "http://fakestore.local/wp-json/wc/v3/";
    private static String productsEndpoint = "products";
    private static String uri = "http://fakestore.local";
    private static String basePath = "/wp-json/wc/v3/";

    @Test
    public void testGetAgain(){
        Response response =
                when()
                .get( productsEndpoint + "/393");

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void testCreateAgain(){
        Response response = given()
                .contentType("application/json")
                .body("{\"name\": \"This product is created\"}")

                .when()
                .post(productsEndpoint);

        Assertions.assertEquals(201, response.statusCode());
    }

    @Test
    public void testUpdateAgain(){

        Response response = given()
                .contentType("application/json")
                .body("{\"name\": \"This product is created to test Update\"}")

                .when()
                .post(productsEndpoint);

        String createdID = response.jsonPath().getString("id");

        response = given()
                .contentType("application/json")
                .body("{\"name\": \"This product is Updated\"}")

                .when()
                .put(productsEndpoint+"/"+createdID);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("This product is Updated", response.jsonPath().getString("name"));
    }

    @Test
    public void testDeleteAgain(){
        Response response = given()
                .contentType("application/json")
                .body("{\"name\": \"This product is created to test Delete\"}")

                .when()
                .post(productsEndpoint);

        String createdID = response.jsonPath().getString("id");

        response =
                when()
                        .delete(productsEndpoint+"/"+createdID);
        Assertions.assertEquals(200, response.statusCode());
    }

    @BeforeAll
    public static void setUp(){
        baseURI = uri;
        RestAssured.port = 80;
        RestAssured.basePath = basePath;
        authentication = oauth(username, password, "", "");
    }

}
