package T123ContactForm.Forms;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Main.UnorderedList;
import T123ContactForm.T123ContactFormMain;

public class FormsMain extends T123ContactFormMain {

    @Test
    public void testDropdown() {

        this.getWebElementsHandler().click(By.className("dropdown-toggle"));

        UnorderedList newFormButton = new UnorderedList(this.getDriver().findElement(By.className("dropdown-menu")));

        List<String> expectedOptions = Arrays.asList("Contact & Lead Form", "Survey", "Event Registration Form",
                "Order Form", "Quiz", "Poll", "Blank Form", "Other Form");
        List<String> actualOptions = new ArrayList<String>();

        for (WebElement option : newFormButton.getOptions()) {
            actualOptions.add(option.getText());
        }

        assertEquals("The new form dropdown doesn't have 8 elements", 8, newFormButton.getOptions().size());

        assertArrayEquals("The form dropdown's values does not match the expected values", expectedOptions.toArray(), actualOptions.toArray());
    }
}
