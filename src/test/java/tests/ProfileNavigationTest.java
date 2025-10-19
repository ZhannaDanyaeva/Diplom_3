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

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://stellarburgers.education-services.ru/");
    }

    @Test
    @DisplayName("Переход в профиль и выход из аккаунта")
    public void checkProfileNavigationAndLogout() {
        // Переход на страницу логина
        driver.findElement(By.xpath("//p[text()='Личный Кабинет']")).click();

        // Логин
        LoginPage loginPage = new LoginPage(driver);
        String email = "jdanyaeva@yandex.ru";
        String password = "123456";
        loginPage.login(email, password);

        // Ожидание перехода после логина
        wait.until(ExpectedConditions.urlContains("/"));

        // Переход в профиль
        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[text()='Личный Кабинет']")));
        profileButton.click();

        // Проверяем, что кнопка «Выйти» есть
        By logoutButton = By.xpath("//button[contains(text(),'Выйти') or contains(text(),'Выход')]");
        try {
            WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
            Assert.assertTrue("Кнопка выхода не отображается после логина", logout.isDisplayed());

            // Клик по кнопке выхода
            logout.click();
            wait.until(ExpectedConditions.urlContains("/login"));
            Assert.assertTrue("Не произошло выхода из аккаунта", driver.getCurrentUrl().contains("/login"));

        } catch (TimeoutException e) {
            Assert.fail("Кнопка выхода не найдена!");
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
