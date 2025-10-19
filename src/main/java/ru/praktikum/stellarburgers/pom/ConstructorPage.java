package ru.praktikum.stellarburgers.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Главную страницу и конструктор объединил в один PageObject так как это одна страница
public class ConstructorPage {

    private WebDriver driver;

    // Кнопка "войти в аккаунт" в разделе "Конструктор"
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");

    // Кнопка для перехода в раздел "Личный кабинет" в шапке страницы в правом углу
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");

    // Лого "Stellar burger"
    private final By logo = By.className("AppHeader_header__logo__2D0X2");

    // Кнопка для перехода в раздел "Конструктор" в шапке страницы в левом углу
    private final By sectionConstructor = By.xpath(".//p[text()='Конструктор']");

    // Вкладка "Булки"
    private final By bunsTab = By.xpath(".//span[text()='Булки']");

    // Вкладка "Соусы"
    private final By saucesTab = By.xpath(".//span[text()='Соусы']");

    // Вкладка "Начинки"
    private final By fillingsTab = By.xpath(".//span[text()='Начинки']");

    // Отображение заголовка "Соберите бургер"
    private final By titleMakeBurger = By.xpath(".//h1[text()='Соберите бургер']");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть главную страницу/конструктор бургеров")
    public ConstructorPage openMainPage() {
        driver.get("https://stellarburgers.education-services.ru/");
        return this;
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public ConstructorPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return this;
    }

    @Step("Нажать 'Личный кабинет'")
    public ConstructorPage clickPersonalAccount() {
        driver.findElement(personalAccountButton).click();
        return this;
    }

    @Step("Нажать 'Конструктор'")
    public ConstructorPage clickConstructor() {
        driver.findElement(sectionConstructor).click();
        return this;
    }

    @Step("Нажать на логотип Stellar Burgers")
    public ConstructorPage clickLogo() {
        driver.findElement(logo).click();
        return this;
    }

    @Step("Клик по вкладке 'Булки'")
    public ConstructorPage clickBuns() {
        driver.findElement(bunsTab).click();
        return this;
    }

    @Step("Клик по вкладке 'Соусы'")
    public ConstructorPage clickSauces() {
        driver.findElement(saucesTab).click();
        return this;
    }

    @Step("Клик по вкладке 'Начинки'")
    public ConstructorPage clickFillings() {
        driver.findElement(fillingsTab).click();
        return this;
    }

    @Step("Проверить, что страница конструктора открылась")
    public ConstructorPage checkConstructorOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(titleMakeBurger));
        assertTrue(driver.findElement(titleMakeBurger).isDisplayed(), "Страница конструктора не открыта");
        return this;
    }

    public boolean isBunsSectionActive() {
        try {
            // находим родителя вкладки 'Булки' и ждём, пока его класс будет содержать нужное значение
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.attributeContains(
                            driver.findElement(By.xpath(".//span[text()='Булки']/parent::div")),
                            "class",
                            "tab_tab_type_current__2BEPc"
                    ));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isSaucesSectionActive() {
        try {
            // находим родителя вкладки 'Соусы' и ждём, пока его класс будет содержать нужное значение
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.attributeContains(
                            driver.findElement(By.xpath(".//span[text()='Соусы']/parent::div")),
                            "class",
                            "tab_tab_type_current__2BEPc"
                    ));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isFillingsSectionActive() {
        try {
            // находим родителя вкладки 'Начинки' и ждём, пока его класс будет содержать нужное значение
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.attributeContains(
                            driver.findElement(By.xpath(".//span[text()='Начинки']/parent::div")),
                            "class",
                            "tab_tab_type_current__2BEPc"
                    ));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
