import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class deleteProduct {

    private String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private String url = "http://fakestore.local/wp-json/wc/v3/";
    private String productsEndpoint = "products";

    @Test
    public void deleteCreatedProduct(){

        String sampleBody = "{\"name\": \"This product should be deleted\"}";
        Response response = given()
                .port(80)
                .auth()
                .oauth(username, password, "", "")
                .contentType("application/json")
                .body(sampleBody)

                .when()
                .post(url + productsEndpoint);

        Assertions.assertEquals(201, response.statusCode());
        String createdID = response.jsonPath().get("id").toString();
        Assertions.assertEquals("This product should be deleted", response.jsonPath().getString("name"));

        response = given()
                .port(80)
                .auth()
                .oauth(username, password, "", "")
                .queryParam("force", "true")

                .when()
                .delete(url + productsEndpoint + "/" + createdID);

        Assertions.assertEquals(200, response.statusCode());

        response = given()
                .port(80)
                .auth()
                .oauth(username, password, "", "")

                .when()
                .get(url + productsEndpoint + "/" + createdID);

        Assertions.assertEquals(404, response.statusCode());
        System.out.println(response.getBody().prettyPrint());
    }

}
