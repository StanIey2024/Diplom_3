package ru.praktikum.stellarburgers.utils;

import com.github.javafaker.Faker;
import ru.praktikum.stellarburgers.pom.CreateUser;

public class UserGenerator {

    private static final Faker faker = new Faker();

    public static CreateUser generateRandomUser() {
        String name = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 12);
        return new CreateUser(name, email, password);
    }


}
