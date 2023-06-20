import POJOs.Product;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class SerialTest extends BaseAPITest{
    @Test
    public void testDeserialization(){
        Product product = when()
                .get(productsEndpoint+"/393")
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

    @Test
    public void excerciseSerialization(){

        Product product = when()
                .get(productsEndpoint+"/1000")
                .as(Product.class);

        product.setName("Extra Name of Product");
        product.setSlug("Changed slug for product");

        given()
                .contentType("application/json")
                .body(product)
                .when()
                .put(productsEndpoint+"/"+ product.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .assertThat()
                .body("name", equalTo("Extra Name of Product"));

    }
}
