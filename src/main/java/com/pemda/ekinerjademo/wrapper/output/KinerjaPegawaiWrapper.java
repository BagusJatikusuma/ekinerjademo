package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 27/12/17.
 */
public class KinerjaPegawaiWrapper {
    private String kdSurat;
    private String jenisSurat;
    private Integer kdJenisSurat;
    private Integer durasiPengerjaan;

    public KinerjaPegawaiWrapper() {
    }
    public KinerjaPegawaiWrapper(String kdSurat, String jenisSurat, Integer kdJenisSurat, Integer durasiPengerjaan) {
        this.kdSurat = kdSurat;
        this.jenisSurat = jenisSurat;
        this.kdJenisSurat = kdJenisSurat;
        this.durasiPengerjaan = durasiPengerjaan;
    }

    public String getKdSurat() {
        return kdSurat;
    }

    public void setKdSurat(String kdSurat) {
        this.kdSurat = kdSurat;
    }

    public String getJenisSurat() {
        return jenisSurat;
    }

    public void setJenisSurat(String jenisSurat) {
        this.jenisSurat = jenisSurat;
    }

    public Integer getKdJenisSurat() {
        return kdJenisSurat;
    }

    public void setKdJenisSurat(Integer kdJenisSurat) {
        this.kdJenisSurat = kdJenisSurat;
    }

    public Integer getDurasiPengerjaan() {
        return durasiPengerjaan;
    }

    public void setDurasiPengerjaan(Integer durasiPengerjaan) {
        this.durasiPengerjaan = durasiPengerjaan;
    }
}
