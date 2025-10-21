package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;
    private final By loginButton = By.xpath("//button[contains(text(),'Войти')]");
    private final By accountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private final By logoButton = By.className("AppHeader_header__logo__2D0X2");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public By clickAccountButton() {
        driver.findElement(accountButton).click();
        return null;
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickLogo() {
        driver.findElement(logoButton).click();
    }
}
