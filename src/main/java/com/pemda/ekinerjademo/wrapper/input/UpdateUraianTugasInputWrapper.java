package com.pemda.ekinerjademo.wrapper.input;

/**
 * Created by bagus on 02/10/17.
 */
public class UpdateUraianTugasInputWrapper {
    private String kdUrtug;
    private String deskripsi;
    private String createdBy;

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }
}
