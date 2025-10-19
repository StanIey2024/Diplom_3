package ru.praktikum.stellarburgers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.stellarburgers.client.UserClient;
import ru.praktikum.stellarburgers.pom.AccountPage;
import ru.praktikum.stellarburgers.pom.ConstructorPage;
import ru.praktikum.stellarburgers.pom.CreateUser;
import ru.praktikum.stellarburgers.pom.LoginPage;

import static ru.praktikum.stellarburgers.driver.WebDriverCreator.createDriver;

public class AccountTests {

    private WebDriver driver;
    private LoginPage loginPage;
    private AccountPage accountPage;
    private CreateUser user;

    @BeforeEach
    public void setUp() {
        driver = createDriver();
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        user = UserClient.createUserApi();
        loginPage.openLoginPage()
                        .fillLoginForm(user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void goToAccount() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickPersonalAccount();
        accountPage.checkProfileOpened();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор через кнопку 'Конструктор'")
    public void goToConstructorFromAccount() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickPersonalAccount()
                        .clickConstructor()
                        .checkConstructorOpened();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор через логотип")
    public void goToConstructorViaLogo() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickLogo()
                        .checkConstructorOpened();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void logoutFromAccount() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickPersonalAccount();
        accountPage.clickLogout();
        loginPage.checkLoginOpened();

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        UserClient.deleteUserApi(user);
    }
}
