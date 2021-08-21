package services;

import dto.OtusOrder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class OrderOtusApi {
    private static final String BASE_URI = "https://petstore.swagger.io/v2/";
    private RequestSpecification spec;
    private static final String ORDER = "/store/order/";

    public OrderOtusApi() {
        spec = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
    }

    public Response createOrder(OtusOrder order) {
        return
                given(spec)
                        .with()
                        .body(order)
                        .when()
                        .post(ORDER);
    }

    public Response checkOrder(Long id) {
        return
                given(spec)
                        .with()
                        .when()
                        .get(ORDER + id);
    }
}
