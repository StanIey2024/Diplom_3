package ru.praktikum.stellarburgers.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.praktikum.stellarburgers.pom.CreateUser;

import static io.restassured.RestAssured.given;
import static ru.praktikum.stellarburgers.utils.UserGenerator.generateRandomUser;

public class UserClient {

    static {
        RestAssured.baseURI = "https://stellarburgers.education-services.ru/";
    }

    // Создание пользователя через API
    public static CreateUser createUserApi() {
        CreateUser user = generateRandomUser();

        Response response = given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/register");

        if (response.statusCode() == 200) {
            user.setAccessToken(response.jsonPath().getString("accessToken"));
        } else {
            throw new RuntimeException("Не удалось создать пользователя: " + response.statusLine());
        }

        return user;
    }

    // Удаление пользователя через API
    public static void deleteUserApi(CreateUser user) {
        if (user.getAccessToken() != null) {
            given()
                    .header("Authorization", user.getAccessToken())
                    .when()
                    .delete("/api/auth/user")
                    .then()
                    .statusCode(202);
        }
    }
}
