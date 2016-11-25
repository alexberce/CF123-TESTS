package Main;

import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class WindowHandler {
    private WebDriver driver;

    public WindowHandler(WebDriver driver){
        this.driver = driver;

        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public void iAmOnPage(String title){
        String parentWindowId = this.driver.getWindowHandle();

        try {
            String pageTitle = this.driver.switchTo().window(parentWindowId).getTitle();
            if (pageTitle.equals(title)) {
                assertEquals(title, this.driver.getTitle());
            }
        } finally {
            this.driver.switchTo().window(parentWindowId);
        }
    }

    public void goToPage(String url){
        this.driver.get(url);
    }

    public void switchToIframe(String iframeId){
        this.driver.switchTo().frame(iframeId);
    }

    public void switchToDefault(){
        this.driver.switchTo().defaultContent();
    }
}
