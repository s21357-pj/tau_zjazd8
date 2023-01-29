import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopTest {
    private WebDriver driverChrome;
    private WebDriver driverOpera;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        System.setProperty("webdriver.opera.driver", "resources/operadriver");
        //driverOpera = new OperaDriver();
        //driverOpera.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driverChrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void testLinkCount_opera() {
        driverOpera = new OperaDriver();
        driverOpera.get("http://automationpractice.pl/index.php");
        int linkCount = driverOpera.findElements(By.xpath("//a")).size();
        assertEquals(95, linkCount);
        driverOpera.quit();
    }

    @Test
    public void testImageCount_opera() {
        driverOpera = new OperaDriver();
        driverOpera.get("http://automationpractice.pl/index.php");
        int linkCount = driverOpera.findElements(By.xpath("//img")).size();
        assertEquals(22, linkCount);
        driverOpera.quit();
    }

    @Test
    public void testPCount_opera() {
        driverOpera = new OperaDriver();
        driverOpera.get("http://automationpractice.pl/index.php");
        int linkCount = driverOpera.findElements(By.xpath("//p")).size();
        assertEquals(23, linkCount);
        driverOpera.quit();
    }

    @Test
    public void testLinkCount_chrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driverChrome = new ChromeDriver(options);
        driverChrome.get("http://automationpractice.pl/index.php");
        int linkCount = driverChrome.findElements(By.xpath("//a")).size();
        assertEquals(95, linkCount);
        driverChrome.quit();
    }

    @Test
    public void testImageCount_chrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driverChrome = new ChromeDriver(options);
        driverChrome.get("http://automationpractice.pl/index.php");
        int linkCount = driverChrome.findElements(By.xpath("//img")).size();
        assertEquals(22, linkCount);
        driverChrome.quit();
    }

    @Test
    public void testPCount_chrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driverChrome = new ChromeDriver(options);
        driverChrome.get("http://automationpractice.pl/index.php");
        int linkCount = driverChrome.findElements(By.xpath("//p")).size();
        assertEquals(23, linkCount);
        driverChrome.quit();
    }

    @AfterEach
    public void tearDown() {
        //driverOpera.quit();
    }
}