package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void search(String word) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(
                    "input[placeholder*='Search'], input[aria-label*='Search']")))
                .sendKeys(word + Keys.ENTER);
    }

    public boolean resultsDisplayed() {
        try {
            wait.until(ExpectedConditions.urlContains("/search/"));

            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(
                        "main a[href*='/in/'], main a[href*='/company/']")))
                    .isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public String clickResult() {

        WebElement result = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.cssSelector(
                        "main a[href*='/in/'], main a[href*='/company/']")));

        String link = result.getAttribute("href");

        result.click();
        return link;
    }
}
