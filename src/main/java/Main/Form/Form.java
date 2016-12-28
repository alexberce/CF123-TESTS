package Main.Form;

import Main.WebElementsHandler;
import Main.WindowHandler;
import org.openqa.selenium.NotFoundException;
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

        try {
            int fieldIdInt = Integer.valueOf(fieldId);

            if(0 < fieldIdInt && !this.fields.contains(new Field(fieldIdInt))){

                Field fieldObject = new Field(Integer.valueOf(fieldId));
                fieldObject.setType(type);

                this.fields.add(fieldObject);
            }
        }
        catch(NumberFormatException e){
            System.out.println("Field ID Is not integer");
        }
    }

    public List<Field> getFields(){
        return this.fields;
    }

    public Field getFieldById(int id) throws NotFoundException{
        for(Field field: this.fields){
            if(field.getId() == id)
                return field;
        }

        throw new NotFoundException("Unable to locate field with id: " + id);
    }

    public Field getFieldByPosition(int position){
        return this.fields.get(position);
    }

    public Field getFieldByType(String type){
        Field field;
        switch(type){
            case "submit":
                field = this.getFieldById(Field.submitButtonId);
                break;
            case "captcha":
                /* This does not work for now */
                field = this.getFieldById(Field.captchaId);
                break;
            default:
                field = this.getFieldByPosition(0);
                break;
        }

        return field;
    }

    public void setFields(){

    }

}
