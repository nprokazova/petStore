package User.сreateUser;

import dto.OtusUser;
import dto.UserOut;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import services.OtusUserApi;

public class CreateUserTest1 {

    private OtusUserApi userApi = new OtusUserApi();

    @Test
    public void checkUser() {
        OtusUser user = OtusUser.builder()
                .userStatus(101L)
                .email("email")
                .firstName("Name")
                .id(101L)
                .lastName("LastName")
                .password("Pas")
                .phone("33445")
                .username("2334")
                .build();

        Response response = userApi.createUser(user);
        response
                .then()
                .log().all()
                .body("type", equalTo("unknown"))
                .body("message", equalTo("101"))
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_OK);


        String messageExpected = response.jsonPath().get("message");
        String messageExpectedDto = response.as(UserOut.class).getMessage();

        Assertions.assertEquals("101", messageExpected);
        Assertions.assertEquals("101", messageExpectedDto);

    }
}
