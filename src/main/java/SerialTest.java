import POJOs.Product;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class SerialTest extends BaseAPITest{
    @Test
    public void testDeserialization(){
        Product product = when()
                .get(productsEndpoint+"/393/")
                .as(Product.class);

        System.out.println(product.toString());
    }

    @Test
    public void testSerialization(){
        Product product = new Product(0,"Test Fake Object","Test Slug","Test Description");
        given()
                .contentType("application/json")
                .body(product)
                .when()
                .post(productsEndpoint)
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .assertThat()
                .body("name",equalTo("Test Fake Object"));
    }
}
