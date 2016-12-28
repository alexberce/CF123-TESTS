package TCommon;


import Main.*;
import Main.Form.Form;
import org.junit.After;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class TCommonMain {

    private static WebDriver driver;

    protected static WindowHandler windowHandler;

    private static WebElementsHandler webElementsHandler;

    private static Form Form = new Form();

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

    protected WebDriver getDriver(){
        return TCommonMain.driver;
    }

    protected WindowHandler getWindowHandler(){
        return TCommonMain.windowHandler;
    }

    protected WebElementsHandler getWebElementsHandler(){
        return TCommonMain.webElementsHandler;
    }

    protected Form getForm(){
        return TCommonMain.Form;
    }

    /* This method should be called only when we have a form ID in the URL */
    protected void setForm(){
        String url = this.getDriver().getCurrentUrl();
        if(url.toLowerCase().contains("&id=")) {
            this.getWindowHandler().goToPage(url + "&p=edit_fields");
            this.getWindowHandler().wait(1);
            int urlFormId = Integer.valueOf(WindowHandler.getQueryParams(url).get("id").get(0));
            TCommonMain.Form = new Form(urlFormId, this.getWebElementsHandler());
        }

        this.getWindowHandler().goToPage(url);
    }

    protected void goToPage(String url){
        try {
            int urlFormId = Integer.valueOf(WindowHandler.getQueryParams(url).get("id").get(0));
            if(url.toLowerCase().contains("&id=") && (this.getForm().getId() == 0 || urlFormId != this.getForm().getId())) {
                this.setForm();
            }
        }
        catch (NumberFormatException e){
            //We don't have a form ID in the URL
        }

        this.getWindowHandler().goToPage(url);
    }
}
