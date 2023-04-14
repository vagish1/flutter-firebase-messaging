package model;

import java.util.ArrayList;

public class UserLocation {
    private String type;
    ArrayList<Object> coordinates = new ArrayList<Object>();


    // Getter Methods

    public String getType() {
        return type;
    }

    // Setter Methods

    public void setType(String type) {
        this.type = type;
    }
}
