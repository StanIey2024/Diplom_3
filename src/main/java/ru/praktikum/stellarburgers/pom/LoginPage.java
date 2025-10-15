package ru.praktikum.stellarburgers.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPage {

    private final WebDriver driver;

    // Поле "Email" по центру страницы
    private final By fieldEmail = By.xpath(".//label[text()='Email']/following-sibling::input");

    // Поле "Пароль" по центру страницы под полем "Email"
    private final By fieldPassword = By.name("Пароль");

    // Кнопка "Войти"
    private final By signInButton = By.xpath(".//button[text()='Войти']");

    // Кликабельный текст "Зарегистрироваться" под кнопкой "Войти"
    private final By signUpClickableText = By.xpath(".//a[text()='Зарегистрироваться']");

    // Кликабельный текст "Восстановить пароль" под кликабельным текстом "Зарегистрироваться"
    private final By recoverPasswordClickableText = By.xpath(".//a[text()='Восстановить пароль']");

    // Отображение заголовка "Вход"
    private final By titleEnter = By.xpath(".//h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Открыть страницу входа
    public LoginPage openLoginPage(){
        driver.get("https://stellarburgers.education-services.ru/login");
        return this;
    }

    @Step("Выполнить вход с email и паролем")
    public LoginPage fillLoginForm(String email, String password) {
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(signInButton).click();
        return this;
    }

    @Step("Проверить, что страница входа открылась")
    public LoginPage checkLoginOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(titleEnter));
        assertTrue(driver.findElement(titleEnter).isDisplayed(), "Страница входа не открыта");
        return this;
    }

    @Step("Нажать на кликабельный текст Восстановить пароль")
    public LoginPage clickSignUpClickableText() {
        driver.findElement(signUpClickableText).click();
        return this;
    }
    @Step("Нажать на кликабельный текст Восстановить пароль")
    public LoginPage clickRecoverPasswordClickableText() {
        driver.findElement(recoverPasswordClickableText).click();
        return this;
    }


}
