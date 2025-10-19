package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;
    private final By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By incorrectPasswordError = By.xpath("//p[contains(text(),'Некорректный пароль')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void register(String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(registerButton).click();
    }

    public boolean isPasswordErrorVisible() {
        return driver.findElement(incorrectPasswordError).isDisplayed();
    }
}
