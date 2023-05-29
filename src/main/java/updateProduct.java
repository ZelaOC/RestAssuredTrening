import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class updateProduct {
    private String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private String url = "http://fakestore.local/wp-json/wc/v3/";
    private String productsEndpoint = "products";

    @Test
    public void createUpdateProduct() {
        String sampleBody = "{\"name\": \"This product should be changed\", \"slug\": \"RazdwaTrzy\"}";

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
        Assertions.assertEquals("This product should be changed", response.jsonPath().getString("name"));

        response = given()
                .port(80)
                .auth()
                .oauth(username, password, "", "")
                .contentType("application/json")
                .body("{\"name\": \"This product was changed\"}")

                .when()
                .put(url + productsEndpoint + "/" + createdID);
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertTrue(response.prettyPrint().contains("This product was changed"));
    }
}