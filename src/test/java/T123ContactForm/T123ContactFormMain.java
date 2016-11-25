package T123ContactForm;

import TCommon.TCommonMain;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class T123ContactFormMain extends TCommonMain {

    @Test
    public void testBasic() {
        driver.findElement(By.id("headerLogin")).click();

        this.checkThatWeAreOnLoginPage();
        this.loginTo123CF();

        this.checkThatWeAreOnDashboard();

        this.webElementsHandler.clickElementOrFail(By.id("qa-forms"), "Forms are not there!");

        //Navigate to Rules Section and Test It
        this.windowHandler.goToPage("https://www.123contactform.com/index.php?p=settings&id=2312854&tab=6&from=firsttime");
        this.testRulesSection();
    }

    private void loginTo123CF() {
        this.webElementsHandler.fillElementOrFail(By.name("username"), "cirtogflorin@gmail.com", "Username doesn't exist!");
        this.webElementsHandler.fillElementOrFail(By.name("password"), "tz056889", "Password is not correct!");

        this.webElementsHandler.clickElementOrFail(By.id("submit-form-button"), "Submit button is not there!");
    }

    private void checkThatWeAreOnLoginPage() {
        this.windowHandler.iAmOnPage("Login");
        this.windowHandler.iAmOnPage("Login to your 123ContactForm account");
    }

    private void checkThatWeAreOnDashboard() {
        this.windowHandler.iAmOnPage("Dashboard");
        this.windowHandler.iAmOnPage("Dashboard - 123ContactForm");
    }

    private void testRulesSection() {
        WebElement checkRules = driver.findElement(By.id("rules_onof_checkbox_2"));

        if (!checkRules.isSelected()) {
            checkRules.click();
        }

        assertTrue(checkRules.isSelected());
    }

}
