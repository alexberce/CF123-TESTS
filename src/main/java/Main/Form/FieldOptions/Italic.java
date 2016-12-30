package Main.Form.FieldOptions;

/**
 * Created by alexa on 12/30/2016.
 */

public class Italic extends LabelModifier {

    public Italic(int fieldId){
        super("Italic", "false", fieldId);
        this.HTMLElementID = "italic_label";
    }

    Boolean labelIsDecorated(){
        return this.getOutputTitleCssProperty("font-style").equals("italic");
    }
}
