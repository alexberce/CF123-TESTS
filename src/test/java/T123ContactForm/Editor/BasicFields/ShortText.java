package T123ContactForm.Editor.BasicFields;

import Main.Form.Field;
import Main.Form.FieldOptions.Bold;
import Main.Form.FieldOptions.Italic;
import T123ContactForm.T123ContactFormMain;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class ShortText extends T123ContactFormMain {

    @Test
    public void testDragAndDropShortText() {
        /* TODO: Remove the waiting where is not needed */
        this.getWebElementsHandler().click(By.className("dropdown-toggle"));
        this.getDriver().findElement(By.className("dropdown-menu"));
        this.getDriver().findElement(By.xpath("/html/body/header/div[1]/div[3]/div[1]/div/ul/li[7]/a")).click();
        this.getWebElementsHandler().dragAndDrop(By.id("addtextbox"), By.id("col1list"), "Doesn't work");

        this.setForm();

        /* Get the first field on the form */
        Field firstField = this.getForm().getFieldByPosition(0);

        /* Click the field */
        firstField.click();

        /* Initialize Bold and Italic options */
        /* TODO: This initialization should be done automatically */
        Bold boldOption = (Bold) firstField.getOption("Bold");
        Italic italicOption = (Italic) firstField.getOption("Italic");

        /* Test the bold Option */
        boldOption.enable();
        assertEquals("The bold option should be enabled", true, boldOption.enabled());

        this.getWindowHandler().wait(1);

        boldOption.disable();
        assertEquals("The bold option should be disabled", true, boldOption.disabled());
        this.getWindowHandler().wait(1);

        /* Test the italic Option */
        italicOption.enable();
        assertEquals("The italic option should be enabled", true, italicOption.enabled());

        this.getWindowHandler().wait(1);

        italicOption.disable();
        assertEquals("The italic option should be disabled", true, italicOption.disabled());
        this.getWindowHandler().wait(1);


        // Check to see that the label is hidden
        this.getWebElementsHandler().checkTheHoverDropdown(By.id("poz"), By.id("poz-anc-1"), By.cssSelector(".label-hidden"));
        this.getWindowHandler().wait(1);

        // Check to see that the label is removed
        this.getWebElementsHandler().checkTheHoverDropdown(By.id("poz"), By.id("poz-anc-2"), By.cssSelector(".label-removed"));
        this.getWindowHandler().wait(1);

        // Check to see that the label is visible again
        this.getWebElementsHandler().checkTheHoverDropdown(By.id("poz"), By.id("poz-anc-0"), By.id("poz-anc-0"));
        this.getWindowHandler().wait(1);

        // Delete the previous name and write other one
        this.getWebElementsHandler().clickElementOrFail(By.id("c_text"), "It didn't click");
        this.getWebElementsHandler().deleteAndWriteNewValue(By.xpath("//*[@id=\"c_text\"]"), "Name", "The label didn't modified.");
        this.getWindowHandler().wait(1);

        // Check to see that the compact field button works
        this.getWebElementsHandler().clickElementOrFail(By.id("c_compactfield"), "Didn't click the checkbox.");
        this.getWebElementsHandler().clickElementOrFail(By.cssSelector(".label-hidden"), "The label is hidden, which means the label is in the input field.");
        this.getWindowHandler().wait(1);

        // Uncheck the compact field
        this.getWebElementsHandler().clickElementOrFail(By.id("c_compactfield"), "Didn't click the checkbox.");


        //Click on submit button
        this.getForm().getFieldByType("submit").click();

        this.getWindowHandler().wait(2);
    }

}
