package ru.praktikum.stellarburgers.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private WebDriver driver;

    // Кнопка "войти в аккаунт" в разделе "Конструктор"
    private final By signInLink = By.xpath(".//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кликабельному тексту 'Войти' на странице восстановления пароля")
    public ForgotPasswordPage clickSignInLink() {
        driver.findElement(signInLink).click();
        return this;
    }
}
