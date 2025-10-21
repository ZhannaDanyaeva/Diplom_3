package tests;

import driver.DriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        // Читаем параметр браузера из системного свойства
        String browser = System.getProperty("browser", "chrome");
        driver = DriverFactory.createDriver(browser);
        driver.get("https://stellarburgers.education-services.ru/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
