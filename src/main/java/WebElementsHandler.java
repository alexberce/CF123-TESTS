import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

class WebElementsHandler {
    private WebDriver driver;

    WebElementsHandler(WebDriver driver){
        this.driver = driver;
    }

    boolean isElementPresent(By by) {
        try {
            this.driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    void fillElementOrFail(By element, String value, String failText){
        if (this.isElementPresent(element)) {
            WebElement webElement = this.driver.findElement(element);
            if (webElement.isDisplayed()) {
                webElement.sendKeys(value);
            } else {
                fail(failText);
            }
        }
    }

    void clickElementOrFail(By element, String failText){
        if (this.isElementPresent(element)) {
            WebElement webElement = this.driver.findElement(element);
            if (webElement.isDisplayed()) {
                webElement.click();
            } else {
                fail(failText);
            }
        }
    }

    void click(By element) {
        this.driver.findElement(element).click();
    }

    void fill(By element, String value) {
        this.driver.findElement(element).sendKeys(value);
    }

    String getValue(By element) {
        return this.driver.findElement(element).getAttribute("value");
    }
}