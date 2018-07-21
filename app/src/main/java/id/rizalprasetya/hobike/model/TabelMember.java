package id.rizalprasetya.hobike.model;

import com.orm.SugarRecord;

/**
 * Created by Rizal Prasetya on 19/07/2018.
 */

public class TabelMember extends SugarRecord {

    public String id_member;
    public String nama;
    public String password;
    public String alamat;
    public String jenis_kelamin;
    public String telepon;
    public String pekerjaan;

    public TabelMember() {
    }

    public TabelMember(String id_member, String nama, String password, String alamat, String jenis_kelamin, String telepon, String pekerjaan) {
        this.id_member = id_member;
        this.nama = nama;
        this.password = password;
        this.alamat = alamat;
        this.jenis_kelamin = jenis_kelamin;
        this.telepon = telepon;
        this.pekerjaan = pekerjaan;
    }

    public String getId_member() {
        return id_member;
    }

    public void setId_member(String id_member) {
        this.id_member = id_member;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }
}
