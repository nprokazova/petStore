package User.otus;

import dto.UserOut;
import io.restassured.response.Response;
import lesson.UserOtus;
import lesson.UserOtusApi;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;


/**
 * На уроке написали классы
 * CreateUserOtusTest
 * UserOtus
 * UserOtusApi
 */
public class CreateUserOtusTest {
    private UserOtusApi userOtusApi = new UserOtusApi();

    @Test
    public void checkUser() {
        UserOtus userOtus = UserOtus.builder()
                .userStatus(101L)
                .email("email")
                .firstName("Name")
                .id(101L)
                .lastName("LastName")
                .password("Pas")
                .phone("33445")
                .username("2334")
                .build();

        Response response = userOtusApi.createUser(userOtus);

        response
                .then()
                .log().all()
                .body("type", equalTo("unknown"))
                .body("message", equalTo("101"))
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_OK);

        String messageExpected = response.jsonPath().get("message");

        String messageExpectedDto = response.as(UserOut.class).getMessage();

        Assertions.assertEquals("101", messageExpectedDto);

    }
}
