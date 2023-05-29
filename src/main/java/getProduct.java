import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class getProduct {
    private String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private String url = "http://fakestore.local/wp-json/wc/v3/";
    private String productsEndpoint = "products";
    private String productID = "393";
    private String productCreated = "979";





    @Test
    public void FirstGetTest(){
        Response response = given()
                .port(80)
                .auth()
                .oauth(username,password,"","")

                .when()
                .get(url+productsEndpoint+"/"+productCreated);

//        System.out.println(response.prettyPeek()); // Shows full information about request.
//        System.out.println(response.getBody().prettyPrint()); // Shows only body of the request
        System.out.println(response.jsonPath().getString("name")); // Shows only value of path from request
        Assertions.assertEquals(200, response.statusCode());
    }
}
