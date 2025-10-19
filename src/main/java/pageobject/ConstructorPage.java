package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConstructorPage {
    private WebDriver driver;
    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");
    private final By activeTab = By.xpath("//div[contains(@class,'tab_tab_type_current')]");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    public boolean isBunsTabActive() {
        WebElement active = driver.findElement(activeTab);
        return active.getText().equals("Булки");
    }

    public boolean isSaucesTabActive() {
        WebElement active = driver.findElement(activeTab);
        return active.getText().equals("Соусы");
    }

    public boolean isFillingsTabActive() {
        WebElement active = driver.findElement(activeTab);
        return active.getText().equals("Начинки");
    }
}
