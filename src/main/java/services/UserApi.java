package services;

import dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String BASE_URI = "https://petstore.swagger.io/v2";
    private static final String USER = "/user";
    private RequestSpecification spec;


     /*   public void exampleTest() {
        given()
                .baseUri("https://petstore.swagger.io/v2")  //https://petstore.swagger.io/v2{userName}
                .header("Content-Type", "application/json")
                .basePath("user")
                .param("userName", "testUser")
                .log().all()
                .body("")
                .when()
                .post()
                .then()
                .log().all();
    }*/





    public UserApi () {
        spec = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
    }

    public Response createUser(User user) {
        return
                given(spec)
                        .body(user)
                        .when()
                        .log().all()
                        .post(USER);
    }
}
