package ru.praktikum.stellarburgers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.stellarburgers.pom.ConstructorPage;
import ru.praktikum.stellarburgers.pom.LoginPage;
import ru.praktikum.stellarburgers.pom.RegisterPage;

import static ru.praktikum.stellarburgers.driver.WebDriverCreator.createDriver;

public class RegisterTests {

    private WebDriver driver;
    private ConstructorPage constructorPage;
    private RegisterPage registerPage;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
//        driver = new ChromeDriver();
        driver = createDriver("chrome");
        constructorPage = new ConstructorPage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Проверить успешную регистрацию пользователя")
    public void successfulRegistration() {
        String email = "user" + System.currentTimeMillis() + "@local.net";
        constructorPage.openMainPage()
                        .clickLoginButton();
        loginPage.clickSignUpClickableText();
        registerPage.fillRegistrationForm("Иван", email, "qwerty123");

        loginPage.checkLoginOpened();
    }

    @Test
    @DisplayName("Проверить ошибку при коротком пароле")
    public void registrationWithShortPassword() {
        String email = "short" + System.currentTimeMillis() + "@local.net";
        constructorPage.openMainPage()
                        .clickLoginButton();
        loginPage.clickSignUpClickableText();
        registerPage.fillRegistrationForm("Петр", email, "123")
                        .checkPasswordError();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
