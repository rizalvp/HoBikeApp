package id.rizalprasetya.hobike.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rizal Prasetya on 16/07/2018.
 */

public class BikeResult {
    @SerializedName("bikes")
    @Expose
    private List<Bike> bikes = null;

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }
}
