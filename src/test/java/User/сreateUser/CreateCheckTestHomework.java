package User.сreateUser;

import dto.OtusOrder;
import dto.OtusUser;
import dto.UserOut;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.OrderOtusApi;
import services.OtusUserApi;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CreateCheckTestHomework {

    private OtusUserApi userApi = new OtusUserApi();
    private OrderOtusApi orderApi = new OrderOtusApi();

    //Создание пользователя Ивана Иванова
    @Test
    public void createUser() {

        OtusUser user = OtusUser.builder()
                .userStatus(101L)
                .email("email")
                .firstName("Ivan")
                .id(101L)
                .lastName("Ivanov")
                .password("Ivan777")
                .phone("4576898")
                .username("Ivan")
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

    //Создание пользователя без почты
    @Test
    public void createUserWithoutEmail() {

        OtusUser user = OtusUser.builder()
                .userStatus(101L)
                .firstName("Petr")
                .id(101L)
                .lastName("Petrov")
                .password("Test555")
                .phone("6543278")
                .username("Petr")
                .build();

        Response response = userApi.createUser(user);
        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);

        String messageExpected = response.jsonPath().get("email");

        Assertions.assertEquals(null, messageExpected);
    }

    //Создание заказа
    @Test
    public void createOrder() {
        OtusOrder order = OtusOrder.builder()
                .id(101L)
                .petId(101L)
                .quantity(0)
                .shipDate("2021-07-24T15:28:14.437Z")
                .status("placed")
                .complete(true)
                .build();

        Response response = orderApi.createOrder(order);
        response
                .then()
                .log().all()
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_OK);

    }

    //Попытка получить заказ, которого нет
    @Test
    public void checkUnknownOrder() {
        Response response = orderApi.checkOrder(0L);
        response
                .then()
                .log().all()
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    //Получение заказа
    @Test
    public void checkOrder() {
       OtusOrder order = OtusOrder.builder()
               .id(101L)
               .petId(101L)
               .quantity(0)
               .shipDate("2021-07-24T15:28:14.437Z")
               .status("placed")
               .complete(true)
               .build();

        Response response = orderApi.createOrder(order);
        response
                .then()
                .log().all()
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_OK);

        response = orderApi.checkOrder(101L);
        response
                .then()
                .log().all()
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_OK);

    }
}
