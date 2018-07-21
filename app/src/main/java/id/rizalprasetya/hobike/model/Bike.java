package id.rizalprasetya.hobike.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rizal Prasetya on 13/07/2018.
 */

public class Bike {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kode_sepeda")
    @Expose
    private String kodeSepeda;
    @SerializedName("jenis_sepeda")
    @Expose
    private String jenisSepeda;
    @SerializedName("merek_sepeda")
    @Expose
    private String merekSepeda;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("mime")
    @Expose
    private String mime;
    @SerializedName("original_filename")
    @Expose
    private String originalFilename;
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

    public String getKodeSepeda() {
        return kodeSepeda;
    }

    public void setKodeSepeda(String kodeSepeda) {
        this.kodeSepeda = kodeSepeda;
    }

    public String getJenisSepeda() {
        return jenisSepeda;
    }

    public void setJenisSepeda(String jenisSepeda) {
        this.jenisSepeda = jenisSepeda;
    }

    public String getMerekSepeda() {
        return merekSepeda;
    }

    public void setMerekSepeda(String merekSepeda) {
        this.merekSepeda = merekSepeda;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
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
