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
		driver.findElement(By.className("dropdown-toggle")).click();
		// Get the Dropdown as a UnorderedList using is's class Name
		UnorderedList newForm = new UnorderedList(driver.findElement(By.className("dropdown-menu")));

		// Verify Dropdown has eight forms for selection
		assertEquals(8, newForm.getOptions().size());

		// Verify Dropdown has expected values as listed in a array
		List<String> exp_options = Arrays.asList("Contact & Lead Form", "Survey", "Event Registration Form",
				"Order Form", "Quiz", "Poll", "Blank Form", "Other Form");
		List<String> act_options = new ArrayList<String>();

		// Retrive the option values from Dropdown using getOptions() method
		for (WebElement option : newForm.getOptions()) {
			act_options.add(option.getText());
		}

		// Verify expected options array and actual options array match
		assertArrayEquals(exp_options.toArray(), act_options.toArray());
	}
}
