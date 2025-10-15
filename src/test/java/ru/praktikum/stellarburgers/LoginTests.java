package ru.praktikum.stellarburgers;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import ru.praktikum.stellarburgers.pom.*;

import java.time.Duration;

import static ru.praktikum.stellarburgers.driver.WebDriverCreator.createDriver;

public class LoginTests {

    private WebDriver driver;
    private ConstructorPage constructorPage;
    private LoginPage loginPage;
    private AccountPage accountPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;

    @BeforeEach
    public void setUp() {
        driver = createDriver("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        constructorPage = new ConstructorPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной странице")
    public void loginInMainPageLoginButton() {
        constructorPage.openMainPage()
                .clickLoginButton();  // кнопка "Войти в аккаунт"
        loginPage.fillLoginForm("Mouse123@local.net", "Mouse172839");
        constructorPage.clickPersonalAccount();
        accountPage.checkProfileOpened();
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет' на главной странице")
    public void loginInPersonalAccountButton() {
        constructorPage.openMainPage()
                .clickPersonalAccount();  // кнопка "Личный кабинет"
        loginPage.fillLoginForm("Mouse123@local.net", "Mouse172839");
        constructorPage.clickPersonalAccount();
        accountPage.checkProfileOpened();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginInRegisterFormButton() {
        registerPage.openRegisterPage()
                .clickSignInClickableText();
        loginPage.fillLoginForm("Mouse123@local.net", "Mouse172839");
        constructorPage.clickPersonalAccount();
        accountPage.checkProfileOpened();
    }


    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginInPasswordRecoveryFormButton() {
        constructorPage.openMainPage()
                .clickLoginButton();
        loginPage.clickRecoverPasswordClickableText();  // переходит в форму восстановления
        forgotPasswordPage.clickSignInLink(); // кнопка "Войти" в форме восстановления
        loginPage.fillLoginForm("Mouse123@local.net", "Mouse172839");
        constructorPage.clickPersonalAccount();
        accountPage.checkProfileOpened();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
