package ru.praktikum.stellarburgers;

import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.stellarburgers.pom.ConstructorPage;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.praktikum.stellarburgers.driver.WebDriverCreator.createDriver;


public class ConstructorTests {

    private WebDriver driver;
    private ConstructorPage constructorPage;

    @BeforeEach
    public void setUp() {
        driver = createDriver();
        constructorPage = new ConstructorPage(driver);
        constructorPage.openMainPage();
    }

    @Test
    @DisplayName("Переход на вкладку 'Булки'")
    @Description("Проверить активность вкладки 'Булки' после перехода на другую вкладку, и что можно вернуться в обратно 'Булки'")
    public void shouldSwitchToBunsSection() {
        constructorPage.clickFillings();  // переходим в "Начинки"
        constructorPage.clickBuns();      // возвращаемся в "Булки"
        assertTrue(constructorPage.isBunsSectionActive(),
                "Вкладка 'Булки' не стала активной после клика");
    }

    @Test
    @DisplayName("Переход на вкладку 'Соусы'")
    @Description("Проверить активность вкладки 'Соусы'")
    public void shouldSwitchToSaucesSection() {
        constructorPage.clickSauces();
        assertTrue(constructorPage.isSaucesSectionActive(), "Вкладка 'Соусы' не стала активной после клика");

    }

    @Test
    @DisplayName("Переход на вкладку 'Начинки'")
    @Description("Проверить активность вкладки 'Начинки'")
    public void shouldSwitchToFillingsSection() {
        constructorPage.clickFillings();
        assertTrue(constructorPage.isFillingsSectionActive(),
                "Вкладка 'Начинки' не стала активной после клика");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
