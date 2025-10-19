package ru.praktikum.stellarburgers.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Path;
import java.nio.file.Paths;

public class WebDriverCreator {

    /*
     * Переменные окружения, прописанные в системе:
     * WEBDRIVERS - путь к папке с драйверами
     * YANDEX_BROWSER_DRIVER_FILENAME - имя файла драйвера для Яндекс.Браузера
     * YANDEX_BROWSER_PATH - путь к исполняемому файлу Яндекс.Браузера
     *
     * Для выбора браузера можно передавать system property:
     * -Dbrowser=chrome
     * -Dbrowser=yandex
     */

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser");
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }

        switch (browser.toLowerCase()) {
            case "yandex":
                return createYandexDriver();
            case "chrome":
            default:
                return createChromeDriver();
        }
    }

    private static WebDriver createChromeDriver() {
        System.out.println("🔹 Запуск в Google Chrome");
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }

    private static WebDriver createYandexDriver() {
        System.out.println("🟡 Запуск в Яндекс.Браузере");

        String driverEnv = System.getenv("WEBDRIVERS");
        String driverFile = System.getenv("YANDEX_BROWSER_DRIVER_FILENAME");
        Path driverPath;

        if (driverFile != null && Paths.get(driverFile).isAbsolute()) {
            // если драйвер уже абсолютный путь
            driverPath = Paths.get(driverFile);
        } else {
            // иначе комбинируем с папкой
            driverPath = Paths.get(driverEnv, driverFile);
        }

        System.setProperty("webdriver.chrome.driver", driverPath.toString());

        ChromeOptions options = new ChromeOptions();

        String yandexBinary = System.getenv("YANDEX_BROWSER_PATH");
        if (yandexBinary != null && !yandexBinary.isEmpty()) {
            options.setBinary(yandexBinary);
        }

        return new ChromeDriver(options);
    }
}
