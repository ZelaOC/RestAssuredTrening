package EndpointsConfig;

import POJOs.Product;

import static io.restassured.RestAssured.get;

public class ProductsEndpoints extends BasicEndpointConfiguration{
    private final String productsEndpoint = "products";

    public Product getProduct(int id){
        lastResponse = get(productsEndpoint+"/"+id);
        return lastResponse.as(Product.class);
    }
}
