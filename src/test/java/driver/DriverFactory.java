package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.nio.file.Paths;

public class DriverFactory {

    public static WebDriver createDriver(String browserName) {
        WebDriver driver;

        switch (browserName.toLowerCase()) {
            case "yandex":
                // Пусть путь к Яндекс.Браузеру указан вручную
                WebDriverManager.chromedriver().setup();
                ChromeOptions yandexOptions = new ChromeOptions();

                // Путь к Яндекс.Браузеру (можно поменять под свою систему)
                String yandexPath = "/Applications/Yandex.app/Contents/MacOS/Yandex";
                yandexOptions.setBinary(Paths.get(yandexPath).toFile());
                driver = new ChromeDriver(yandexOptions);
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        return driver;
    }
}
