import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class firstExercise {
    private String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private String url = "http://fakestore.local/wp-json/wc/v3/";
    private String productsEndpoint = "products";





    @Test
    public void firstExerciseCreateNewObject() {
        String sampleBody = "{\"name\": \"Źołądek Byczorka\", \"slug\": \"RazdwaTrzy\"}";

        Response response = given()
                .port(80)
                .auth()
                .oauth(username, password, "", "")
                .contentType("application/json")
                .body(sampleBody)

                .when()
                .post(url + productsEndpoint);

        Assertions.assertEquals(201, response.statusCode());
        String createdID = response.jsonPath().getString("id");
        Assertions.assertEquals("Źołądek Byczorka", response.jsonPath().getString("name"));

        response = given()
                .port(80)
                .auth()
                .oauth(username,password,"","")

                .when()
                .get(url+productsEndpoint+"/"+createdID);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Źołądek Byczorka", response.jsonPath().getString("name"));

        response = given()
                .port(80)
                .auth()
                .oauth(username,password,"","")
                .queryParam("per_page", 100)

                .when()
                .get(url+productsEndpoint);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertTrue(response.prettyPrint().contains(createdID));
        Assertions.assertTrue(response.prettyPrint().contains("Źołądek Byczorka"));
    }
}