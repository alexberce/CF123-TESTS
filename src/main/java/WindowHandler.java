import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

class WindowHandler {
    private WebDriver driver;

    WindowHandler(WebDriver driver){
        this.driver = driver;

        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    void iAmOnPage(String title){
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

    void goToPage(String url){
        this.driver.get(url);
    }

    void switchToIframe(String iframeId){
        this.driver.switchTo().frame(iframeId);
    }

    void switchToDefault(){
        this.driver.switchTo().defaultContent();
    }
}
