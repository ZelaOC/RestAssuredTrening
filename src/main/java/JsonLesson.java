import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class JsonLesson {
    private static String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private static String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private static String url = "http://fakestore.local/wp-json/wc/v3/";
    private static String productsEndpoint = "products";
    private static String uri = "http://fakestore.local";
    private static String basePath = "/wp-json/wc/v3/";
    private static int port = 80;

    @Test
    public void jsonTest1(){
        Response response = when()
                .get(productsEndpoint + "/393");
        System.out.println(response.jsonPath().getString("id"));
    }

    @Test
    public void jsonTest2(){
        Response response = when()
                .get(productsEndpoint + "/393");
        //child of element in json can be choose by dot
        List<String> listOfValues = response.jsonPath().get("related_ids");
    }

    @Test
    public void jsonTest3(){
        Response response = when()
                .get(productsEndpoint + "/393");
        List<Integer> listOfValues = response.jsonPath().get("categories.id");
        System.out.println(listOfValues.get(0));
    }

    @Test
    public void jsonTest4(){
        Response response = when()
                .get(productsEndpoint + "/393");
        System.out.println(response.jsonPath().getString("categories[0].id"));
    }

//    @Test
//    public void xmlTest1() {
//        Response response = when()
//                .get(productsEndpoint + "/393");
//        response.xmlPath().get("")
//
//    }
        @BeforeAll
    public static void setUp(){
        baseURI = uri;
        RestAssured.port = port;
        RestAssured.basePath = basePath;
        authentication = oauth(username, password, "", "");
    }
}
