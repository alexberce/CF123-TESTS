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

    protected void setForm(int id){
        TCommonMain.Form = new Form(id, this.getWebElementsHandler());
    }

    protected void goToPage(String url){
        if(url.toLowerCase().contains("&id=")) {
            int urlFormId = Integer.valueOf(WindowHandler.getQueryParams(url).get("id").get(0));
            if(url.toLowerCase().contains("p=edit_fields") && this.getForm().getID() == 0){
                this.setForm(urlFormId);
            }
            else
            {
                if(urlFormId != this.getForm().getID()){
                    this.getWindowHandler().goToPage(url + "&p=edit_fields");
                    this.setForm(urlFormId);
                }
            }
        }

        this.getWindowHandler().goToPage(url);
    }
}
