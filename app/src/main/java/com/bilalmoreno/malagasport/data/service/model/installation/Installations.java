
package com.bilalmoreno.malagasport.data.service.model.installation;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Installations {

    @SerializedName("crs")
    @Expose
    private Crs crs;
    @SerializedName("totalFeatures")
    @Expose
    private Integer totalFeatures;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("features")
    @Expose
    private List<Feature> features = null;

    public Crs getCrs() {
        return crs;
    }

    public void setCrs(Crs crs) {
        this.crs = crs;
    }

    public Integer getTotalFeatures() {
        return totalFeatures;
    }

    public void setTotalFeatures(Integer totalFeatures) {
        this.totalFeatures = totalFeatures;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

}
