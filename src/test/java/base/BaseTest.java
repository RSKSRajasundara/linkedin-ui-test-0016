package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.nio.file.Files;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void close(ITestResult result) {

        if (driver == null) return;

        if (!result.isSuccess()) {
            try {
                File screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

                File folder = new File("screenshots");
                folder.mkdirs();

                File saved = new File(folder, result.getName() + ".png");

                Files.copy(screenshot.toPath(), saved.toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                System.out.println("Screenshot failed");
            }
        }

        driver.quit();
    }
}

