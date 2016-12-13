package T123ContactForm.Editor.BasicFields;

import org.junit.Test;
import org.openqa.selenium.By;

import T123ContactForm.T123ContactFormMain;

public class ShortText extends T123ContactFormMain {

    @Test
    public void testDragAndDropShortText() {
        this.getWebElementsHandler().click(By.className("dropdown-toggle"));
        this.getDriver().findElement(By.className("dropdown-menu"));
        this.getDriver().findElement(By.xpath("/html/body/header/div[1]/div[3]/div[1]/div/ul/li[7]/a")).click();
        this.getWebElementsHandler().dragAndDrop(By.id("addtextbox"), By.id("col1list"), "Doesn't work");

        //Workaround until we have a more dynamic form object loading system
        this.goToPage(this.getDriver().getCurrentUrl());

        //Click on the first field of the form
        this.getForm().getField(0).click();

        //Wait 2 seconds so we can see the clicks
        this.getWindowHandler().wait(2);

        //Click on submit button
        this.getForm().getField(this.getForm().getFields().size()).click();
    }

}
