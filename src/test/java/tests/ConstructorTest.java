package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.ConstructorPage;

public class ConstructorTest {
    private WebDriver driver;
    private final String baseUrl = "https://stellarburgers.education-services.ru/";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @Test
    @DisplayName("Проверка вкладок 'Булки', 'Соусы', 'Начинки' в конструкторе")
    public void checkConstructorTabs() {
        ConstructorPage constructorPage = new ConstructorPage(driver);

        constructorPage.clickSaucesTab();
        Assert.assertTrue("Раздел 'Соусы' не выбран", constructorPage.isSaucesTabActive());

        constructorPage.clickFillingsTab();
        Assert.assertTrue("Раздел 'Начинки' не выбран", constructorPage.isFillingsTabActive());

        constructorPage.clickBunsTab();
        Assert.assertTrue("Раздел 'Булки' не выбран", constructorPage.isBunsTabActive());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
