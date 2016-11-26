package TCommon;


import Main.*;
import org.junit.After;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class TCommonMain {

    protected static WebDriver driver;

    protected static WindowHandler windowHandler;

    protected static WebElementsHandler webElementsHandler;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");

        TCommonMain.driver = new ChromeDriver();
        TCommonMain.windowHandler = new WindowHandler(TCommonMain.driver);
        TCommonMain.webElementsHandler = new WebElementsHandler(TCommonMain.driver);
    }

    @Before
    public abstract void runBeforeTestMethod();

    @After
    public abstract void runAfterTestMethod();

    @AfterClass
    public static void tearDown() throws Exception {
        TCommonMain.driver.quit();
    }

    public WebDriver getDriver(){
        return TCommonMain.driver;
    }

    public WindowHandler getWindowHandler(){
        return TCommonMain.windowHandler;
    }

    public WebElementsHandler getWebElementsHandler(){
        return TCommonMain.webElementsHandler;
    }
}
