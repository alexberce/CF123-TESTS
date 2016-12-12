package Main;

import org.openqa.selenium.WebDriver;

public class Common {
    private static WebDriver driver;

    Common(WebDriver driver){
        Common.driver = driver;
    }

    protected WebDriver getDriver(){
        return Common.driver;
    }
}