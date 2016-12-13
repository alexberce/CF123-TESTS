package Main.Form;

import Main.WebElementsHandler;
import Main.WindowHandler;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Form {

    private int id = 0;
    private WebElementsHandler webElementsHandlerObject;
    private List<Field> fields = new ArrayList<>();

    public Form(){
        this.id = 0;
    }

    public Form(int id, WebElementsHandler webElementsHandlerObject){
        this.id = id;
        this.webElementsHandlerObject = webElementsHandlerObject;

        this.init();
    }

    public int getId(){
        return  this.id;
    }

    public int getIdFromUrl(){
        String url = WindowHandler.getInstance().getCurrentUrl();

        if(url.toLowerCase().contains("&id="))
            return Integer.valueOf(WindowHandler.getQueryParams(url).get("id").get(0));

        return 0;
    }

    private void init(){
        List<WebElement> editorFields = this.webElementsHandlerObject.findElementsByPartialId("form-editor-fields-container", Field.fieldIdIdentifier);

        for(WebElement field: editorFields){
            this.addField(field);
        }
    }

    private void addField(WebElement field){
        String fieldId =  field.getAttribute("id").split(Field.fieldIdIdentifier)[1];

        if(fieldId.contains("-")){
            fieldId = fieldId.split("-")[1];
        }

        String type = field.getTagName();

        /* I did not use .isEmpty because it triggers @deprecated error in IntelliJ IDEA */
        if(!"".equals(fieldId.trim()) && 0 < Integer.valueOf(fieldId) && !this.fields.contains(new Field(Integer.valueOf(fieldId)))){

            Field fieldObject = new Field(Integer.valueOf(fieldId));
            fieldObject.setType(type);

            this.fields.add(fieldObject);
        }
    }

    public List<Field> getFields(){
        return this.fields;
    }

    public Field getField(int id){
        return this.fields.get(id);
    }

    public void setFields(){

    }

}
