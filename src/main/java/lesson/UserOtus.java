
package lesson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * На уроке написали классы
 * CreateUserOtusTest
 * UserOtus
 * UserOtusApi
 */
@Data
@AllArgsConstructor
@Builder
@JsonSerialize
public class UserOtus {

    private String email;
    private String firstName;
    private Long id;
    private String lastName;
    private String password;
    private String phone;
    private Long userStatus;
    private String username;
}
