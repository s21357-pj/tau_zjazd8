import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class CountTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "resources/geckodriver");
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("network.cookie.cookieBehavior", 0);
        options.setBinary(firefoxBinary);
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testLinkCount() {
        driver.get("https://www.example.com");
        int linkCount = driver.findElements(By.xpath("//a")).size();
        assertEquals(1, linkCount);
    }

    @Test
    public void testImageCount() {
        driver.get("https://www.example.com");
        int linkCount = driver.findElements(By.xpath("//img")).size();
        assertEquals(0, linkCount);
    }

    @Test
    public void testLinkNavigation() {
        driver.get("https://www.example.com");

        String originalWindow = driver.getWindowHandle();
        List<WebElement> urls = driver.findElements(By.xpath("//a"));

        for (int i = 0; i < urls.size(); i++) {
            urls.get(i).click();
            driver.switchTo().window(originalWindow);
        }
    }

    @Test
    public void testInputCount() {
        driver.get("https://demo.netbox.dev/login/");
        int formFieldCount = driver.findElement(By.xpath("//form[1]")).findElements(By.xpath(".//input")).size();
        assertEquals(3, formFieldCount);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}