package Main.Form;

import Main.WebElementsHandler;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class Field {

    /* This is -2 actually */
    public static final int captchaId = 2;
    public static final int submitButtonId = 10000;

    public static String fieldIdIdentifier = "myitemlist_myitemlist";
    private int id;
    private String type;
    private List<Option> options = new ArrayList<Option>();

    Field(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Object getOptions(){
        return this.options.toArray();
    }

    public Object getOption(int id){
        return this.options.get(id);
    }

    public void setOptions(String property, String value){
        for(int i=0; i<=this.options.size(); i++) {
            if(this.options.get(i).getName().equals(property)){
                this.options.get(i).setValue(value);
            }
        }
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
