package Main.Form;

import Main.WebElementsHandler;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Form {

    private int ID = 0;
    private WebElementsHandler webElementsHandlerObject;
    private List<Field> fields = new ArrayList<Field>();

    public Form(){
        this.ID = 0;
    }

    public Form(int id, WebElementsHandler webElementsHandlerObject){
        this.ID = id;
        this.webElementsHandlerObject = webElementsHandlerObject;

        this.init();
    }

    private void init(){
        List<WebElement> editorFields = this.webElementsHandlerObject.findElementsByPartialId(Field.fieldIdIdentifier);

        for(int i=0; i < editorFields.size(); i++){
            WebElement field = editorFields.get(i);
            String fieldId =  field.getAttribute("id")
                                .split(Field.fieldIdIdentifier)[1]
                                .split("_")[0]
                                .split("-")[0];

            String type = field.getTagName();

            /* I did not use .isEmpty because it triggers @deprecated error in IntelliJ IDEA */
            if(!"".equals(fieldId.trim())){
                Field fieldObject = new Field(Integer.valueOf(fieldId));
                fieldObject.setType(type);

                this.fields.add(fieldObject);
            }
        }
    }

    public int getID(){
        return  this.ID;
    }

    public Object getFields(){
        return this.fields.toArray();
    }

    public Object getField(int id){
        return this.fields.get(id);
    }

    public void setFields(){

    }

}