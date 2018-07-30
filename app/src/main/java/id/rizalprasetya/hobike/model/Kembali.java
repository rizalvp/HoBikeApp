package id.rizalprasetya.hobike.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rizal Prasetya on 30/07/2018.
 */

public class Kembali {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("member_id")
    @Expose
    private Integer memberId;
    @SerializedName("bike_id")
    @Expose
    private Integer bikeId;
    @SerializedName("hotel_id_awal")
    @Expose
    private String hotelIdAwal;
    @SerializedName("hotel_id_kembali")
    @Expose
    private Object hotelIdKembali;
    @SerializedName("total")
    @Expose
    private Object total;
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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getBikeId() {
        return bikeId;
    }

    public void setBikeId(Integer bikeId) {
        this.bikeId = bikeId;
    }

    public String getHotelIdAwal() {
        return hotelIdAwal;
    }

    public void setHotelIdAwal(String hotelIdAwal) {
        this.hotelIdAwal = hotelIdAwal;
    }

    public Object getHotelIdKembali() {
        return hotelIdKembali;
    }

    public void setHotelIdKembali(Object hotelIdKembali) {
        this.hotelIdKembali = hotelIdKembali;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
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
