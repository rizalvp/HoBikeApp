package id.rizalprasetya.hobike.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rizal Prasetya on 19/07/2018.
 */

public class LoginResult {
    @SerializedName("data")
    @Expose
    private List<Login> data = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Login> getData() {
        return data;
    }

    public void setData(List<Login> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
