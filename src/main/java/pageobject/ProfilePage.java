package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;
    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLogoutButtonVisible() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
            return driver.findElement(logoutButton).isDisplayed();
        } catch (Exception e) {
            System.out.println("❌ Кнопка выхода не найдена!");
            return false;
        }
    }

    public void clickLogout() {
        driver.findElement(logoutButton).click();
    }
}
