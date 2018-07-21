package id.rizalprasetya.hobike.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rizal Prasetya on 15/07/2018.
 */

public class Posisi {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("bike_id")
    @Expose
    private Integer bikeId;
    @SerializedName("hotel_id")
    @Expose
    private String hotelId;
    @SerializedName("hotel_id_awal")
    @Expose
    private String hotelIdAwal;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sewas_id")
    @Expose
    private Integer sewasId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBikeId() {
        return bikeId;
    }

    public void setBikeId(Integer bikeId) {
        this.bikeId = bikeId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelIdAwal() {
        return hotelIdAwal;
    }

    public void setHotelIdAwal(String hotelIdAwal) {
        this.hotelIdAwal = hotelIdAwal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSewasId() {
        return sewasId;
    }

    public void setSewasId(Integer sewasId) {
        this.sewasId = sewasId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
