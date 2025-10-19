package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.linkText("Зарегистрироваться");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }
}
