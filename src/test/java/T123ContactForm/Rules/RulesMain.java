package T123ContactForm.Rules;

import T123ContactForm.T123ContactFormMain;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class RulesMain extends T123ContactFormMain{

    public RulesMain(){
        super();
    }

    @Test
    public void testRulesBasic(){
        this.getWindowHandler().goToPage("https://www.123contactform.com/index.php?p=settings&id=2312854&tab=6");
        WebElement checkRules = this.getDriver().findElement(By.id("rules_onof_checkbox_2"));

        if (!checkRules.isSelected()) {
            checkRules.click();
        }

        assertTrue(checkRules.isSelected());
    }
}
