package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.LoginPage;
import pageobject.MainPage;

public class LoginTest extends BaseTest {
    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.education-services.ru/");
    }

    @Test
    @DisplayName("Вход через кнопку «Войти в аккаунт»")
    public void loginFromMainPage() {
        MainPage main = new MainPage(driver);
        main.clickLoginButton();

        LoginPage login = new LoginPage(driver);
        login.login("valid_user@mail.ru", "123456");
        Assert.assertTrue(driver.getCurrentUrl().contains("/"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
