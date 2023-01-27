package helpers;

import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User() {
        Faker faker = new Faker();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = faker.internet().emailAddress();
        // password with length between 8 & 16, uppercase & special chars are included
        password = faker.internet().password(8, 16, true, true);
    }
}
