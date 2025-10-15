package ru.praktikum.stellarburgers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.stellarburgers.pom.AccountPage;
import ru.praktikum.stellarburgers.pom.ConstructorPage;
import ru.praktikum.stellarburgers.pom.LoginPage;

import static ru.praktikum.stellarburgers.driver.WebDriverCreator.createDriver;

public class AccountTests {

    private WebDriver driver;
    private LoginPage loginPage;
    private AccountPage accountPage;

    @BeforeEach
    public void setUp() {
        driver = createDriver("chrome");
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        loginPage.openLoginPage()
                        .fillLoginForm("Mouse123@local.net", "Mouse172839");
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
    }
}
