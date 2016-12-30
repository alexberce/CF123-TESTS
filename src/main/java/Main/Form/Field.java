package Main.Form;

import Main.Form.FieldOptions.Bold;
import Main.Form.FieldOptions.Italic;
import Main.WebElementsHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Field {

    /* This is -2 actually */
    public static final int captchaId = 2;
    public static final int submitButtonId = 10000;

    public static String fieldIdIdentifier = "myitemlist_myitemlist";
    public static String outputTitleIdIdentifier = "id123-title";

    //TODO: Set this variable and return it on getOutputTitleElement call
    private WebElement outputTitleElement;

    private int id;
    private String type;
    private List<Object> options = new ArrayList<Object>();

    Field(int id){
        this.id = id;
        this.initOptions();
    }

    public int getId() {
        return id;
    }

    public Object getOptions(){
        return this.options.toArray();
    }

    public Object getOption(String optionName){
        for(Object o: this.options) {
            if (o instanceof Bold) {
                Bold option = (Bold) o;
                if (Objects.equals(option.getName(), optionName))
                    return option;
            } else if (o instanceof Italic){
                Italic option = (Italic) o;
                if (Objects.equals(option.getName(), optionName))
                    return option;
            }

        }
        throw new NotFoundException("Unable to locate field with id: " + id);
    }

    public void initOptions(){
        //TODO: Add the options in a dynamic way based on the field type (maybe?)
        Object fieldOption1 = new Bold(this.getId());
        this.options.add(fieldOption1);

        Object fieldOption2 = new Italic(this.getId());
        this.options.add(fieldOption2);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void click(){
        //TODO: Handle captcha because it has a differnt ID
        WebElementsHandler.getInstance().click(By.id(Field.fieldIdIdentifier + this.getId()));
    }

    public WebElement getOutputTitleElement(){
        return Field.getOutputTitleElement(this.getId());
    }

    public static WebElement getOutputTitleElement(int id){
        return WebElementsHandler.getInstance().findElement(By.id(Field.outputTitleIdIdentifier + id));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Field)) return false;

        Field field = (Field) o;

        return getId() == field.getId();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }
}
