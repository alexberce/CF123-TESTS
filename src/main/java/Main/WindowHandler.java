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
    private static WindowHandler instance;
    private static WebDriver driver;

    public WindowHandler(WebDriver driver){
        WindowHandler.driver = driver;

        this.getDriver().manage().window().maximize();
        this.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WindowHandler.instance = this;
    }

    public WindowHandler getInstance(){
        return WindowHandler.instance;
    }

    private WebDriver getDriver(){
        return WindowHandler.driver;
    }

    public void iAmOnPage(String title){
        String parentWindowId = this.getDriver().getWindowHandle();

        try {
            String pageTitle = this.getDriver().switchTo().window(parentWindowId).getTitle();
            if (pageTitle.equals(title)) {
                assertEquals(title, this.getDriver().getTitle());
            }
        } finally {
            this.getDriver().switchTo().window(parentWindowId);
        }
    }

    public void goToPage(String url){
        this.getDriver().get(url);
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
        this.getDriver().switchTo().frame(iframeId);
    }

    public void switchToDefault(){
        this.getDriver().switchTo().defaultContent();
    }
}
