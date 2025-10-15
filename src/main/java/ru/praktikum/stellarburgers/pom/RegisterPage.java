package ru.praktikum.stellarburgers.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterPage {

    private final WebDriver driver;

    // Поле "Имя" по центру страницы
    private final By fieldName = By.xpath(".//label[text()='Имя']/following-sibling::input");

    // Поле "Email" по центру страницы под полем "Имя"
    private final By fieldEmail = By.xpath(".//label[text()='Email']/following-sibling::input");

    // Поле "Пароль" по центру страницы под полем "Email"
    private final By fieldPassword = By.name("Пароль");

    // Ошибка "Некорректный пароль" под полем "Пароль" (если длина пароля меньше 6-ти символов)
    private final By incorrectPasswordError = By.xpath(".//p[text()='Некорректный пароль']");

    // Кнопка "Зарегистрироваться"
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    // Кликабельный текст "Войти" под кнопкой "Зарегистрироваться"
    private final By signInClickableText = By.xpath(".//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Открыть страницу регистрации
    public RegisterPage openRegisterPage(){
        driver.get("https://stellarburgers.education-services.ru/register");
        return this;
    }

    @Step("Заполнить поля регистрации: имя, email и пароль")
    public RegisterPage fillRegistrationForm(String name, String email, String password) {
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(registerButton).click();
        return this;
    }

    @Step("Нажать на текст 'Войти' для перехода на страницу входа")
    public RegisterPage clickSignInClickableText() {
        driver.findElement(signInClickableText).click();
        return this;
    }

    @Step("Проверить отображение ошибки для короткого пароля")
    public RegisterPage checkPasswordError() {
        assertTrue(driver.findElement(incorrectPasswordError).isDisplayed(), "Ошибка для короткого пароля не отображается");
        return this;
    }

}
