import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testLogin() {
        driver.get("https://demo.netbox.dev/login/");
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        usernameField.sendKeys("admin");
        passwordField.sendKeys("admin");
        loginButton.click();
        assertEquals(2, driver.findElements(By.id("navbar_user")).size());
    }
    @Test
    public void testUnsuccessfulLogin() {
        driver.get("https://demo.netbox.dev/login/");
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        usernameField.sendKeys("admin");
        passwordField.sendKeys("nimda");
        loginButton.click();
        assertThrows(org.openqa.selenium.NoSuchElementException.class,()->{
            driver.findElement(By.id("navbar_user")).click();
        });
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}