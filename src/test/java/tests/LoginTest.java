package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.LoginPage;
import pageobject.MainPage;

import java.time.Duration;

public class LoginTest extends BaseTest {
    private WebDriver driver;
    private final String BASE_URL = "https://stellarburgers.education-services.ru/";

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test
    @DisplayName("Позитивный тест — вход через кнопку «Личный кабинет» и переход в профиль")
    public void loginFromMainPage() {
        MainPage main = new MainPage(driver);
        main.clickLoginButton();

        LoginPage login = new LoginPage(driver);
        login.login("jdanyaeva@yandex.ru", "123456");

        main.clickAccountButton();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/account/profile"));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after clicking Account button: " + currentUrl);

        Assert.assertTrue(
                "URL после нажатия на 'Личный кабинет' некорректный: " + currentUrl,
                currentUrl.contains(BASE_URL + "account/profile")
        );
    }

    @Test
    @DisplayName("Негативный тест — неуспешный вход с неверным паролем")
    public void loginUnsuccessFromMainPage() {
        MainPage main = new MainPage(driver);
        main.clickLoginButton();

        LoginPage login = new LoginPage(driver);
        login.login("jdanyaeva@yandex.ru", "123qwe"); // неверный пароль

        // Ждём, что останемся на странице логина (или получим редирект на нее)
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/login"));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after failed login: " + currentUrl);

        Assert.assertTrue(
                "URL при неуспешной авторизации некорректный: " + currentUrl,
                currentUrl.contains(BASE_URL + "login")
        );
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
