package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 09/09/17.
 */
public class UraianTugasWrapper {
    private String kdUrtug;
    private String deskripsi;

    public UraianTugasWrapper() {}
    public UraianTugasWrapper(String kdUrtug, String deskripsi) {
        this.kdUrtug = kdUrtug;
        this.deskripsi = deskripsi;
    }

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
