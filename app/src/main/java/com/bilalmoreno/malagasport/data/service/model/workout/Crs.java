
package com.bilalmoreno.malagasport.data.service.model.workout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Crs {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("properties")
    @Expose
    private Properties_ properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Properties_ getProperties() {
        return properties;
    }

    public void setProperties(Properties_ properties) {
        this.properties = properties;
    }

}
