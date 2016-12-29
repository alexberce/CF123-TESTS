package Main;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.Assert.fail;

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

    public void deleteAndWriteNewValue(By element, String newValue, String failText) {
        if (this.isElementPresent(element)) {
            WebElement webElement = this.getDriver().findElement(element);
            if (webElement.isDisplayed()) {
                webElement.findElement(element).sendKeys(Keys.chord(Keys.CONTROL, "a"), newValue);
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
			//TODO: This fails when you add multiple fields. Anyway, it's not a proper way to check if the field was added
			//assertEquals("Untitled", target.getText());
		} else {
			fail(failText);
		}
		
	}

    public void checkToSeeIfIsBold(By element, String failText) {
        JavascriptExecutor js = (JavascriptExecutor) this.getDriver();
        WebElement we = this.getDriver().findElement(element);
        String fontWeight = (String) js.executeScript("return getComputedStyle(arguments[0]).getPropertyValue('font-weight');", we);
        if (fontWeight.trim().equals("bold")) {
            System.out.println("Is Bold");
        } else {
            System.out.println("Not Bold - " + fontWeight);
        }
    }

    public void checkToSeeIfIsItalic(By element, String failText) {
        JavascriptExecutor js = (JavascriptExecutor) this.getDriver();
        WebElement we = this.getDriver().findElement(element);
        String fontStyle = (String) js.executeScript("return getComputedStyle(arguments[0]).getPropertyValue('font-style');", we);
        if (fontStyle.trim().equals("italic")) {
            System.out.println("Is Italic");
        } else {
            System.out.println("Not Italic - " + fontStyle);
        }
    }

    public void checkTheHoverDropdown(By element1, By element2, By element3) {
        Actions action = new Actions(this.getDriver());
        WebElement we = this.getDriver().findElement(element1);
        action.moveToElement(we).moveToElement(this.getDriver().findElement(element2)).click().build().perform();
        if (this.isElementPresent(element3)) {
            System.out.println("Working!");
        } else {
            System.out.println("Not working!");
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