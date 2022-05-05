package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class WishListTest {

    @Test
    void addItemToWishList() {

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_28_7_10=25&product_attribute_28_1_11=29&addtocart_28.EnteredQuantity=2")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/28/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"))
                .body("updatetopwishlistsectionhtml", is("(1)"));
    }

    @Test
    void addItemToWishListWithCookie() {
        String EnteredQuantity = "1";

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_28_7_10=25&product_attribute_28_1_11=29&addtocart_28.EnteredQuantity="
                        + EnteredQuantity)
                .cookie("Nop.customer=574cc1d3-bae2-4a4d-ab69-856b5f65aa74;")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/28/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"))
                .body("updatetopwishlistsectionhtml", is("(783)"));
    }
}
