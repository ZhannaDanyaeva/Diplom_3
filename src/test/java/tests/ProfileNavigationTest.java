package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import pageobject.LoginPage;

import java.time.Duration;

public class ProfileNavigationTest extends BaseTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String BASE_URL = "https://stellarburgers.education-services.ru/";
    private final String EMAIL = "jdanyaeva@yandex.ru";
    private final String PASSWORD = "123456";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    private void login() {
        driver.findElement(By.xpath("//p[text()='Личный Кабинет']")).click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(EMAIL, PASSWORD);
        wait.until(ExpectedConditions.urlContains("/"));
    }

    @Test
    @DisplayName("Проверка перехода в личный кабинет после авторизации")
    public void checkProfileNavigationAfterLogin() {
        login();

        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[text()='Личный Кабинет']")));
        profileButton.click();

        // Проверяем наличие кнопки "Выход"
        By logoutButton = By.xpath("//button[contains(text(),'Выход')]");
        WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        Assert.assertTrue("Кнопка выхода не отображается после перехода в профиль", logout.isDisplayed());
    }

    @Test
    @DisplayName("Проверка выхода из аккаунта")
    public void checkLogoutFromProfile() {
        login();

        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[text()='Личный Кабинет']")));
        profileButton.click();

        By logoutButton = By.xpath("//button[contains(text(),'Выйти') or contains(text(),'Выход')]");
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logout.click();

        // Проверяем редирект на страницу логина
        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(
                "Не произошло выхода из аккаунта — URL некорректный: " + driver.getCurrentUrl(),
                driver.getCurrentUrl().contains("/login")
        );
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
