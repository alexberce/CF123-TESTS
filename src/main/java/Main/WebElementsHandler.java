package Main;

import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class WebElementsHandler {
    private WebDriver driver;

    public WebElementsHandler(WebDriver driver){
        this.driver = driver;
    }

    public boolean isElementPresent(By by) {
        try {
            this.driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void fillElementOrFail(By element, String value, String failText){
        if (this.isElementPresent(element)) {
            WebElement webElement = this.driver.findElement(element);
            if (webElement.isDisplayed()) {
                webElement.sendKeys(value);
            } else {
                fail(failText);
            }
        }
    }

    public void clickElementOrFail(By element, String failText){
        if (this.isElementPresent(element)) {
            WebElement webElement = this.driver.findElement(element);
            if (webElement.isDisplayed()) {
                webElement.click();
            } else {
                fail(failText);
            }
        }
    }

    public void click(By element) {
        if(!this.driver.findElements(element).isEmpty()){
            this.driver.findElement(element).click();
        }
        else {
            fail("NoSuchElementException: " + element.toString());
        }
    }

    public void fill(By element, String value) {
        this.driver.findElement(element).sendKeys(value);
    }

    public String getValue(By element) {
        return this.driver.findElement(element).getAttribute("value");
    }
}