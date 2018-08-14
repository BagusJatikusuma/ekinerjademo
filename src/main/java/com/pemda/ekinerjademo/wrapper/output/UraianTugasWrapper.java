package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 09/09/17.
 */
public class UraianTugasWrapper {
    private String kdUrtug;
    private String deskripsi;
    private int volume;
    private String satuanVolume;

    public UraianTugasWrapper() {}
    public UraianTugasWrapper(
            String kdUrtug,
            String deskripsi) {
        this.kdUrtug = kdUrtug;
        this.deskripsi = deskripsi;
    }

    public UraianTugasWrapper(
            String kdUrtug,
            String deskripsi,
            int volume,
            String satuanVolume) {
        this.kdUrtug = kdUrtug;
        this.deskripsi = deskripsi;
        this.volume = volume;
        this.satuanVolume = satuanVolume;
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

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getSatuanVolume() {
        return satuanVolume;
    }

    public void setSatuanVolume(String satuanVolume) {
        this.satuanVolume = satuanVolume;
    }
}
