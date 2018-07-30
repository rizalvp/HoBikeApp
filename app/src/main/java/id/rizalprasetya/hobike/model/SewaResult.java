package id.rizalprasetya.hobike.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rizal Prasetya on 16/07/2018.
 */

public class SewaResult {
    @SerializedName("sewa")
    @Expose
    private Sewa sewa;
    @SerializedName("pesan")
    @Expose
    private String pesan;

    public Sewa getSewa() {
        return sewa;
    }

    public void setSewa(Sewa sewa) {
        this.sewa = sewa;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
