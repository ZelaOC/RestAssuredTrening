import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class BaseAPITest {
    private static String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private static String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private static String url = "http://fakestore.local/wp-json/wc/v3/";
    protected static String productsEndpoint = "products";
    private static String uri = "http://fakestore.local";
    private static String basePath = "/wp-json/wc/v3/";
    private static int port = 80;

    @BeforeAll
    public static void setUp(){
        baseURI = uri;
        RestAssured.basePath = basePath;
        RestAssured.port = port;
        authentication = oauth(username, password, "", "");
    }
}
