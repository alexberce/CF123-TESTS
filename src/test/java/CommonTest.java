import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CommonTest {

    private WebDriver driver;

    private WindowHandler windowHandler;

    private WebElementsHandler webElementsHandler;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");

        this.driver = new ChromeDriver();
        this.windowHandler = new WindowHandler(this.driver);
        this.webElementsHandler = new WebElementsHandler(this.driver);

        this.windowHandler.goToPage("https://www.123contactform.com/");
    }

    @Test
    public void testBasic() {
        driver.findElement(By.id("headerLogin")).click();

        this.checkThatWeAreOnLoginPage();
        this.loginTo123CF();

        this.checkThatWeAreOnDashboard();

        this.webElementsHandler.clickElementOrFail(By.id("qa-forms"), "Forms are not there!");

        //Navigate to Rules Section and Test It
        this.windowHandler.goToPage("https://www.123contactform.com/index.php?p=settings&id=2312854&tab=6&from=firsttime");
        this.testRulesSection();
    }

    private void loginTo123CF() {
        this.webElementsHandler.fillElementOrFail(By.name("username"), "cirtogflorin@gmail.com", "Username doesn't exist!");
        this.webElementsHandler.fillElementOrFail(By.name("password"), "tz056889", "Password is not correct!");

        this.webElementsHandler.clickElementOrFail(By.name("submit-form-button"), "Submit button is not there!");
    }

    private void checkThatWeAreOnLoginPage() {
        this.windowHandler.iAmOnPage("Login");
        this.windowHandler.iAmOnPage("Login to your 123ContactForm account");
    }

    private void checkThatWeAreOnDashboard() {
        this.windowHandler.iAmOnPage("Dashboard");
        this.windowHandler.iAmOnPage("Dashboard - 123ContactForm");
    }

    private void testRulesSection() {
        WebElement checkRules = driver.findElement(By.id("rules_onof_checkbox_2"));

        if (!checkRules.isSelected()) {
            checkRules.click();
        }

        assertTrue(checkRules.isSelected());
    }

    @After
    public void tearDown() throws Exception {
        // Close the browser
        this.driver.quit();
    }
}
