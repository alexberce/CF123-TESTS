package Main.Form.FieldOptions;

/**
 * Created by alexa on 12/30/2016.
 */

public class Bold extends LabelModifier {

    public Bold(int fieldId){
        super("Bold", "false", fieldId);
        this.HTMLElementID = "bold_label";
    }

    Boolean labelIsDecorated(){
        return this.getOutputTitleCssProperty("font-weight").equals("bold");
    }

}
