package Main;

import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public static Map<String, List<String>> getQueryParams(String url) {
        try {
            Map<String, List<String>> params = new HashMap<String, List<String>>();
            String[] urlParts = url.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }

                    List<String> values = params.get(key);
                    if (values == null) {
                        values = new ArrayList<String>();
                        params.put(key, values);
                    }
                    values.add(value);
                }
            }

            return params;
        } catch (UnsupportedEncodingException ex) {
            throw new AssertionError(ex);
        }
    }

    public void switchToIframe(String iframeId){
        this.driver.switchTo().frame(iframeId);
    }

    public void switchToDefault(){
        this.driver.switchTo().defaultContent();
    }
}
