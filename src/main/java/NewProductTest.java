import EndpointsConfig.ProductsEndpoints;
import POJOs.Product;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class NewProductTest {

    private static ProductsEndpoints productsEndpoints;
    private static List<Integer> clearThatID = new ArrayList<>();

    @Test
    public void testGetProduct(){

        Product product = productsEndpoints.getProduct(393);
        Assertions.assertEquals(201, productsEndpoints.getLastStatusCode());
        Assertions.assertEquals("test", product.getName());
    }

    @Test
    public void testCreateNewProduct(){

        Product product = new Product(0,"Test Fake Object","Test Slug","Test Description");
        productsEndpoints.createProduct(product);
        Assertions.assertEquals(201, productsEndpoints.getLastStatusCode());
        clearThatID.add(productsEndpoints.getLastCreateID());
        System.out.println("Product has been added to list id: " + productsEndpoints.getLastCreateID());
        Assertions.assertEquals("Test Fake Object", productsEndpoints.getLastCreateName());
    }

    @BeforeAll
    public static void setUp(){
        productsEndpoints = new ProductsEndpoints();
    }

    @AfterAll
    public static void deleteCreatedProduct(){
        for (int id: clearThatID){
         productsEndpoints.deleteProduct(id);
         Assertions.assertEquals(200, productsEndpoints.getLastStatusCode());
            System.out.println("Product has been delted id: " + id);
        }
    }

}
