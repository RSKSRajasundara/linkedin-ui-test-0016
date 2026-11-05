
package tests;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SearchPage;

public class LinkedInTest extends BaseTest {

    String email = System.getenv("LINKEDIN_EMAIL");
    String password = System.getenv("LINKEDIN_PASSWORD");

    // Test 1 - Valid Login
    @Test
    public void validLogin() {
        Assert.assertNotNull(email);
        Assert.assertNotNull(password);

        LoginPage login = new LoginPage(driver);
        login.login(email, password);

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(20));

        Assert.assertTrue(
                wait.until(ExpectedConditions.urlContains("/feed")),
                "Login failed");

        saveScreenshot("validLogin.png");
    }

    // Test 2 - Invalid Login
    @Test
    public void invalidLogin() {
        Assert.assertNotNull(email);

        LoginPage login = new LoginPage(driver);
        login.login(email, "WrongPassword123!");

        Assert.assertTrue(login.errorDisplayed(),
                "Error message not found");

        saveScreenshot("invalidLogin.png");
    }

    // Test 3 - Search
    @Test
    public void searchTest() {
        loginFirst();

        SearchPage search = new SearchPage(driver);
        search.search("software testing");

        Assert.assertTrue(search.resultsDisplayed(),
                "Search results not found");

        saveScreenshot("searchTest.png");
    }

    // Test 4 - Click Search Result
    @Test
    public void clickResultTest() {
        loginFirst();

        SearchPage search = new SearchPage(driver);
        search.search("software testing");

        Assert.assertTrue(search.resultsDisplayed());

        String link = search.clickResult();
        String path = java.net.URI.create(link).getPath();

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.urlContains(path));

        Assert.assertTrue(
                driver.getCurrentUrl().contains(path),
                "Wrong page opened");

        saveScreenshot("clickResult.png");
    }

    // Reusable login method
    public void loginFirst() {
        Assert.assertNotNull(email);
        Assert.assertNotNull(password);

        LoginPage login = new LoginPage(driver);
        login.login(email, password);

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.urlContains("/feed"));
    }

    // Save screenshots automatically
    public void saveScreenshot(String fileName) {
        try {
            File folder = new File("screenshots");
            folder.mkdirs();

            File image = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Files.copy(
                    image.toPath(),
                    new File(folder, fileName).toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println("Screenshot saved: " + fileName);

        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }
}
