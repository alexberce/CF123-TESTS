package Main.Form.FieldOptions;

import Main.Form.Option;
import Main.WebElementsHandler;
import Main.WindowHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class LabelModifier extends Option {

    private String HTMLElementIDActiveClass = "active";
    String HTMLElementID = "";

    LabelModifier(String name, String value, int fieldId){
        super(name, value, fieldId);
    }

    public Boolean enabled(){
        return this.getValue().equals("true");
    }

    public Boolean disabled(){
        return !this.getValue().equals("true");
    }

    public void enable(){
        if(!this.getOptionElement().getAttribute("class").contains(this.HTMLElementIDActiveClass)){
            WebElementsHandler.getInstance().click(By.id(this.HTMLElementID));
            WindowHandler.getInstance().wait(1);
            if(this.labelIsDecorated()){
                this.setValue("true");
            }
        }
    }

    public void disable(){
        if(this.getOptionElement().getAttribute("class").contains(this.HTMLElementIDActiveClass)) {
            WebElementsHandler.getInstance().click(By.id(this.HTMLElementID));
            WindowHandler.getInstance().wait(1);
            if(!this.labelIsDecorated()){
                this.setValue("false");
            }
        }
    }

    private WebElement getOptionElement(){
        return WebElementsHandler.getInstance().findElement(By.id(this.HTMLElementID));
    }

    abstract Boolean labelIsDecorated();

}
