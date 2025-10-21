package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.ConstructorPage;

public class ConstructorTest extends BaseTest {
    private WebDriver driver;
    private final String baseUrl = "https://stellarburgers.education-services.ru/";
    private ConstructorPage constructorPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        constructorPage = new ConstructorPage(driver);
    }

    @Test
    @DisplayName("Проверка перехода на вкладку 'Соусы'")
    public void checkSaucesTab() {
        constructorPage.clickSaucesTab();
        Assert.assertTrue("Раздел 'Соусы' не выбран", constructorPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Проверка перехода на вкладку 'Начинки'")
    public void checkFillingsTab() {
        constructorPage.clickFillingsTab();
        Assert.assertTrue("Раздел 'Начинки' не выбран", constructorPage.isFillingsTabActive());
    }

    @Test
    @DisplayName("Проверка перехода на вкладку 'Булки'")
    public void checkBunsTab() {
        // Так как вкладка 'Булки' выбрана по умолчанию, сначала переключаемся на другую
        constructorPage.clickSaucesTab();
        constructorPage.clickBunsTab();
        Assert.assertTrue("Раздел 'Булки' не выбран", constructorPage.isBunsTabActive());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
