package Main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

public class UnorderedList {
	private final WebElement element;

	public UnorderedList(WebElement element) {
		this.element = element;
		String tagName = element.getTagName();

		if (null == tagName || !"ul".equals(tagName.toLowerCase())) {
			throw new UnexpectedTagNameException("ul", tagName);
		}

	}

	/**
	 * @return All options belonging to this ul tag
	 */
	public List<WebElement> getOptions() {
		return element.findElements(By.tagName("li"));
	}
}
