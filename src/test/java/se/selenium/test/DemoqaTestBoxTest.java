package se.selenium.test;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;//import for head
import org.openqa.selenium.chrome.ChromeOptions;//new import for headless


public class DemoqaTestBoxTest {
    WebDriver driver;

    @BeforeEach
    void setup() {
        //driver = new ChromeDriver(); // normal browser-only this changes for headless

        // headless Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");              // no browser window
        options.addArguments("--no-sandbox");            // needed for Linux/GitHub
        options.addArguments("--disable-dev-shm-usage"); // prevents memory issues
        options.addArguments("--window-size=1920,1080"); // sets screen size

        // Pass options into ChromeDriver
        driver = new ChromeDriver(options); //add this for headless
        driver.get("https://demoqa.com/text-box");
    }
    @Test
    void openPageTest() {
        System.out.println("Page opened successfully");

    }

    @Test
    void testTextBox() {
        //  Nothing changed here — exactly same head code!

        driver.findElement(By.id("userName")).sendKeys("John Doe");
        driver.findElement(By.id("userEmail")).sendKeys("john@test.com");
        driver.findElement(By.id("currentAddress")).sendKeys("Street 1");
        driver.findElement(By.id("permanentAddress")).sendKeys("Street 2");

        // Find submit button
        WebElement submitButton = driver.findElement(By.id("submit"));

        // JavaScript click instead of normal click
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
        js.executeScript("arguments[0].click();", submitButton);

        // normal click
        //submitButton.click();

        // Verify output
        String output = driver.findElement(By.id("output")).getText();

        Assertions.assertTrue(output.contains("John Doe")," Name not found in output!");

        Assertions.assertTrue(output.contains("john@test.com"),"Email not found in output!");
        Assertions.assertTrue(output.contains("Street 1"),"Current address not found in output!");
        Assertions.assertTrue(output.contains("Street 2"), " Permanent address not found in output!");


        System.out.println("TEST PASSED - All 4 fields verified!!");
        System.out.println("TEST PASSED - sure file");

    }
    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
