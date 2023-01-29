import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class SearchTest {
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
    public void testClickFirstResult() {
        driver.get("https://duckduckgo.com/");
        // Znajdź pole wyszukiwania i wprowadź tekst do wyszukania
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("selenium webdriver\n");
        searchField.submit();
        List<WebElement> searchResults = driver.findElements(By.xpath("//article[@id='r1-0']/div[2]/h2/a/span"));
        searchResults.get(0).click();
        assertTrue(driver.getTitle().contains("Selenium"));
    }
    @Test
    public void testClickThirdResult() {
        driver.get("https://duckduckgo.com/");
        // Znajdź pole wyszukiwania i wprowadź tekst do wyszukania
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("selenium webdriver\n");
        searchField.submit();
        List<WebElement> searchResults = driver.findElements(By.xpath("//article[@id='r1-2']/div[2]/h2/a/span"));
        searchResults.get(0).click();
        assertTrue(driver.getTitle().contains("Selenium"));
    }
    @Test
    public void testClickAlternativeMethod() {
        driver.get("https://duckduckgo.com/");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Selenium WebDriver");
        searchField.submit();
        WebElement firstResult = driver.findElement(By.id("r1-0"));
        firstResult.click();
        assertTrue(driver.getTitle().contains("Selenium"));
    }
    @Test
    public void testClickAlternativeMethod2() {
        driver.get("https://duckduckgo.com/");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Selenium WebDriver");
        searchField.submit();
        WebElement firstResult = driver.findElement(By.partialLinkText("selenium"));
        firstResult.click();
        assertTrue(driver.getTitle().contains("Selenium"));
    }
    @Test
    public void testClickAlternativeMethod3() {
        driver.get("https://duckduckgo.com/");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Selenium WebDriver");
        searchField.submit();
        WebElement firstResult = driver.findElement(By.cssSelector("article"));
        firstResult.click();
        assertTrue(driver.getTitle().contains("Selenium"));
    }
    @Test
    public void testElementNotFound() {
        driver.get("https://duckduckgo.com/");
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("Selenium WebDriver");
        driver.findElement(By.id("search_button_homepage")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        assertThrows(org.openqa.selenium.NoSuchElementException.class,()->{
            WebElement firstResult = driver.findElement(By.cssSelector("non-exist"));
            firstResult.click();
        });
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}