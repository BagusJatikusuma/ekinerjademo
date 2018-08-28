package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 09/09/17.
 */
public class UraianTugasWrapper {
    private String kdUrtug;
    private String deskripsi;
    private Integer volume;
    private String satuanVolume;
    private String jabatan;
    private String unitKerja;

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
            Integer volume,
            String satuanVolume) {
        this.kdUrtug = kdUrtug;
        this.deskripsi = deskripsi;
        this.volume = volume;
        this.satuanVolume = satuanVolume;
    }

    public UraianTugasWrapper(String kdUrtug, String deskripsi, Integer volume, String satuanVolume, String jabatan) {
        this.kdUrtug = kdUrtug;
        this.deskripsi = deskripsi;
        this.volume = volume;
        this.satuanVolume = satuanVolume;
        this.jabatan = jabatan;
    }

    public UraianTugasWrapper(String kdUrtug, String deskripsi, Integer volume, String satuanVolume, String jabatan, String unitKerja) {
        this.kdUrtug = kdUrtug;
        this.deskripsi = deskripsi;
        this.volume = volume;
        this.satuanVolume = satuanVolume;
        this.jabatan = jabatan;
        this.unitKerja = unitKerja;
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

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getSatuanVolume() {
        return satuanVolume;
    }

    public void setSatuanVolume(String satuanVolume) {
        this.satuanVolume = satuanVolume;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getUnitKerja() {
        return unitKerja;
    }

    public void setUnitKerja(String unitKerja) {
        this.unitKerja = unitKerja;
    }
}
