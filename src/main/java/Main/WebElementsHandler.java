package Main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

public class WebElementsHandler {
    private static WebDriver driver;
    private static WebElementsHandler instance = null;

    private WebElementsHandler(){
        // Exists only to defeat instantiation.
    }

    public WebElementsHandler(WebDriver driver){
        WebElementsHandler.driver = driver;
        WebElementsHandler.instance = this;
    }

    public static WebElementsHandler getInstance(){
        if(WebElementsHandler.instance == null) {
            WebElementsHandler.instance = new WebElementsHandler();
        }
        return WebElementsHandler.instance;
    }

    public WebDriver getDriver(){
        return driver;
    }

    public boolean isElementPresent(By by) {
        try {
            this.getDriver().findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void fillElementOrFail(By element, String value, String failText){
        if (this.isElementPresent(element)) {
            WebElement webElement = this.getDriver().findElement(element);
            if (webElement.isDisplayed()) {
                webElement.sendKeys(value);
            } else {
                fail(failText);
            }
        }
    }

    public void clickElementOrFail(By element, String failText){
        if (this.isElementPresent(element)) {
            WebElement webElement = this.getDriver().findElement(element);
            if (webElement.isDisplayed()) {
                webElement.click();
            } else {
                fail(failText);
            }
        }
    }
    
	public void dragAndDrop(By elementDragged, By elementTarget, String failText) {
		if (isElementPresent(elementDragged) && isElementPresent(elementTarget)) {
			WebElement source = this.getDriver().findElement(elementDragged);
			WebElement target = this.getDriver().findElement(elementTarget);

			Actions builder = new Actions(this.getDriver());
			builder.dragAndDrop(source, target).perform();
			assertEquals("Untitled", target.getText());
		} else {
			fail(failText);
		}
		
	}

    public void click(By element) {
        if(!this.getDriver().findElements(element).isEmpty()){
            this.getDriver().findElement(element).click();
        }
        else {
            fail("NoSuchElementException: " + element.toString());
        }
    }

    public List findElementsByPartialId(String id){
        return this.getDriver().findElements(By.xpath("//*[contains(@id, '" + id + "')]"));
    }

    public List findElementsByPartialId(String parentClass, String id){
        return this.getDriver().findElements(By.xpath("//*[contains(@class, '" + parentClass + "')] //*[contains(@id, '" + id + "')]"));
    }

    public void fill(By element, String value) {
        this.getDriver().findElement(element).sendKeys(value);
    }

    public String getValue(By element) {
        return this.getDriver().findElement(element).getAttribute("value");
    }
}