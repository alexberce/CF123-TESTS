package Main.Form;

import java.util.ArrayList;
import java.util.List;

public class Field {
    public static String fieldIdIdentifier = "id123-control";
    private int ID;
    private String type;
    private List<Option> options = new ArrayList<Option>();

    Field(int id){
        this.ID = id;
    }

    public int getID() {
        return ID;
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
}
