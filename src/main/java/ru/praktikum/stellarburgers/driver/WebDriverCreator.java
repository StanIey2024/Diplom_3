package ru.praktikum.stellarburgers.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverCreator {

    public static WebDriver createDriver(String browserName) {
        if (browserName == null || browserName.isEmpty()) {
            browserName = "chrome";
        }

        switch (browserName.toLowerCase()) {
            case "yandex":
                return createYandexDriver();
            case "chrome":
            default:
                return createChromeDriver();
        }
    }

    private static WebDriver createChromeDriver() {
        System.out.println("🔹 Запуск в Google Chrome");
        return new ChromeDriver();
    }

    private static WebDriver createYandexDriver() {
        System.out.println("🟡 Запуск в Яндекс.Браузере");

        // chromedriver Яндекс.Браузера
        System.setProperty("webdriver.chrome.driver",
                "C:/driver/chromedriver.exe");

        // исполняемый файл Яндекс.Браузера
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Users/sta03/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");

        return new ChromeDriver(options);
    }
}
