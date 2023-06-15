import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.oauth;

public class requestesInLoop {

    private static String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private static String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private static String productsEndpoint = "products";
    private static String uri = "http://fakestore.local";
    private static String basePath = "/wp-json/wc/v3/";
    private static int port = 80;
    private static String atribute = "?per_page=100";

    @Test
    public void getAllProducts(){
        Response response =
                when()
                .get(productsEndpoint + atribute);
        Assertions.assertEquals(200, response.statusCode());

        List<Integer> idList = response.jsonPath().get("id");
        int elementNumber = 1;
        int lastElement = idList.size();

        for(int id : idList){
            response = when()
                    .get(productsEndpoint +"/"+id);
            Assertions.assertEquals(200, response.statusCode());
            System.out.println("Tested " + elementNumber +" of "+ lastElement + " named " + response.jsonPath().getString("name"));
            elementNumber++;
        }

    }

    @BeforeAll
    public static void setUp(){
        baseURI = uri;
        RestAssured.port = port;
        RestAssured.basePath = basePath;
        authentication = oauth(username, password, "", "");
    }

}
