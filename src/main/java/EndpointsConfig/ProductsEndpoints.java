package EndpointsConfig;

import POJOs.Product;

import static io.restassured.RestAssured.*;

public class ProductsEndpoints extends BasicEndpointConfiguration{
    private final String productsEndpoint = "products";

    public Product getProduct(int id){
        lastResponse = get(productsEndpoint+"/"+id);
        return lastResponse.as(Product.class);
    }

    public void createProduct(Product product){
        lastResponse = given()
                .contentType(contentType)
                .body(product)
                .when()
                .post(productsEndpoint);
    }

    public void deleteProduct(int id){
        lastResponse = given()
                .param("force","true")
                .when()
                .delete(productsEndpoint+"/"+id);
    }

}
