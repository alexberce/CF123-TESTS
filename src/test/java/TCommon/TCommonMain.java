package TCommon;


import Main.*;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TCommonMain {

    protected WebDriver driver;

    protected WindowHandler windowHandler;

    protected WebElementsHandler webElementsHandler;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");

        this.driver = new ChromeDriver();
        this.windowHandler = new WindowHandler(this.driver);
        this.webElementsHandler = new WebElementsHandler(this.driver);

        this.windowHandler.goToPage("https://www.123contactform.com/");
    }


    @After
    public void tearDown() throws Exception {
        // Close the browser
        this.driver.quit();
    }
}
