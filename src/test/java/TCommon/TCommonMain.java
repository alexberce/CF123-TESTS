package TCommon;


import Main.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
