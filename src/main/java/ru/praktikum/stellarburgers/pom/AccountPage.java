package ru.praktikum.stellarburgers.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountPage {

    private WebDriver driver;

    // Кнопка "Выход" в личном кабинете
    private final By logoutButton = By.xpath(".//button[text()='Выход']");

    // Кнопка "Сохранить" в личном кабинете (когда пользователь авторизован)
    private final By saveButton = By.xpath(".//button[text() = 'Сохранить']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверить, что страница профиля открыта")
    public AccountPage checkProfileOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(saveButton));
        assertTrue(driver.findElement(saveButton).isDisplayed(), "Страница профиля не открыта");
        return this;
    }

    @Step("Нажать кнопку 'Выход'")
    public AccountPage clickLogout() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        driver.findElement(logoutButton).click();
        return this;
    }
}
