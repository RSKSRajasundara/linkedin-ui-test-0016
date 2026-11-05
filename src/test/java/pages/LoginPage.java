package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    
public void login(String email, String password) {

    driver.get("https://www.linkedin.com/login");

    // Enter email in the visible field
    wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("(//input[@type='email' and not(ancestor-or-self::*[@hidden])])[last()]")))
            .sendKeys(email);

    // Enter password in the visible field
    wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("(//input[@type='password' and not(ancestor-or-self::*[@hidden])])[last()]")))
            .sendKeys(password);

    // Click Sign in
    // Click the visible Sign in button
    wait.until(driver -> {
        for (org.openqa.selenium.WebElement button :
            driver.findElements(By.xpath(
                "//button[.//span[normalize-space()='Sign in']]"))) {
                
                if (button.isDisplayed() && button.isEnabled()) {
                    button.click();
                    return true;
                }
        }
    return false;
    });
}


public boolean errorDisplayed() {
    try {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(text(),'Wrong email or password')]")))
                .isDisplayed();
    } catch (Exception e) {
        return false;
    }
}
}
