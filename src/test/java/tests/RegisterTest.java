package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobject.RegisterPage;
import java.time.Duration;

public class RegisterTest {
    private WebDriver driver;
    private RegisterPage registerPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://stellarburgers.education-services.ru/register");
        registerPage = new RegisterPage(driver);
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successfulRegister() {
        String email = "user" + System.currentTimeMillis() + "@mail.ru";
        registerPage.register("Zhanna", email, "123456");

        // Ждём, пока откроется страница логина
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("/login"));

        Assert.assertTrue("После регистрации не открылся экран логина",
                driver.getCurrentUrl().contains("/login"));
    }

    @Test
    @DisplayName("Ошибка при коротком пароле")
    public void registerWithShortPasswordShowsError() {
        String email = "user" + System.currentTimeMillis() + "@mail.ru";
        registerPage.register("Zhanna", email, "123");

        Assert.assertTrue("Сообщение об ошибке не отображается",
                registerPage.isPasswordErrorVisible());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
