package Main.Form;

import Main.WebElementsHandler;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public abstract class Option {
    /* TODO: Make it a factory (maybe?) */
    private int fieldId;
    private String name = "";
    private String value = "";

    public Option(String name, String value, int fieldId) {
        this.name = name;
        this.value = value;
        this.fieldId = fieldId;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    protected void setValue(String value) {
        this.value = value;
    }

    private int getFieldId() {
        return fieldId;
    }

    public WebElement getOutputTitleElement(){
        return Field.getOutputTitleElement(this.getFieldId());
    }

    public String getOutputTitleValue(){
        return this.getOutputTitleElement().getText();
    }

    protected String getOutputTitleCssProperty(String propertyName){
        return WebElementsHandler.getInstance().getElementCSSProperty(this.getOutputTitleElement(), propertyName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Field)) return false;

        Option option = (Option) o;

        return Objects.equals(getName(), option.getName());
    }

    @Override
    public int hashCode() {
        int result = getFieldId();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
