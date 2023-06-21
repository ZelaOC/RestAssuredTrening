package EndpointsConfig;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public abstract class BasicEndpointConfiguration {
    private static String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private static String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private static String url = "http://fakestore.local/wp-json/wc/v3/";

    private static String uri = "http://fakestore.local";
    private static String basePath = "/wp-json/wc/v3/";
    private static int port = 80;
    protected final String contentType = "application/json";

    protected Response lastResponse;

    public int getLastStatusCode(){
        return lastResponse.statusCode();
    }

    public String getLastCreateName(){
        return lastResponse.jsonPath().get("name");
    }

    public int getLastCreateID(){
        return lastResponse.jsonPath().get("id");
    }

    public Response getLastResponse() {
        return lastResponse;
    }

    public void setLastResponse(Response lastResponse) {
        this.lastResponse = lastResponse;
    }

    public BasicEndpointConfiguration(){
        baseURI = uri;
        RestAssured.basePath = basePath;
        RestAssured.port = port;
        authentication = oauth(username, password, "", "");
    }
}
