package T123ContactForm.Editor.BasicFields;

import T123ContactForm.T123ContactFormMain;
import org.junit.Test;
import org.openqa.selenium.By;

public class ShortText extends T123ContactFormMain {

    @Test
    public void testDragAndDropShortText() {
        this.getWebElementsHandler().click(By.className("dropdown-toggle"));
        this.getDriver().findElement(By.className("dropdown-menu"));
        this.getDriver().findElement(By.xpath("/html/body/header/div[1]/div[3]/div[1]/div/ul/li[7]/a")).click();
        this.getWebElementsHandler().dragAndDrop(By.id("addtextbox"), By.id("col1list"), "Doesn't work");

        this.setForm();

        this.getWindowHandler().wait(1);

        //Click on the first field of the form
        this.getForm().getFieldByPosition(0).click();
        this.getWindowHandler().wait(1);

        // Click on the bold button
        this.getWebElementsHandler().clickElementOrFail(By.id("bold_label"), "The bold button doesn't work");
        this.getWindowHandler().wait(1);

        // Check the label to see if it is bold
        this.getWebElementsHandler().checkToSeeIfIsBold(By.cssSelector(".fontbold"), "The label is not bold");
        this.getWindowHandler().wait(1);

        // Click again to unbold the label
        this.getWebElementsHandler().clickElementOrFail(By.id("bold_label"), "The bold button doesn't work");
        this.getWindowHandler().wait(1);

        // Click on the italic button
        this.getWebElementsHandler().clickElementOrFail(By.id("italic_label"), "The italic button doesn't work");
        this.getWindowHandler().wait(1);

        // Check the label to see if it is italic
        this.getWebElementsHandler().checkToSeeIfIsItalic(By.cssSelector(".fontitalic"), "The label is not italic");
        this.getWindowHandler().wait(1);

        // Click again on the button to make non-italic
        this.getWebElementsHandler().clickElementOrFail(By.id("italic_label"), "The italic button doesn't work");
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
