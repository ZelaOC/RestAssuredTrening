import EndpointsConfig.ProductsEndpoints;
import POJOs.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NewProductTest {

    @Test
    public void testGetProduct(){
        ProductsEndpoints productsEndpoints = new ProductsEndpoints();
        Product product = productsEndpoints.getProduct(393);
        Assertions.assertEquals(201, productsEndpoints.getLastStatusCode());
        Assertions.assertEquals("test", product.getName());
    }
}
