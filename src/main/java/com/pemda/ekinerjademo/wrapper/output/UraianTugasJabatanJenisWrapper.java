package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 03/11/17.
 */
public class UraianTugasJabatanJenisWrapper {
    private String urtug;
    private String kdUrtug;
    private String kdJabatan;
    private String kdJenisUrtug;
    private Integer tahunUrtug;
    private Integer kuantitas;
    private String satuanKuantitas;
    private Integer kualitas;
    private Integer waktu;
    private Integer biaya;

    public UraianTugasJabatanJenisWrapper() {
    }
    public UraianTugasJabatanJenisWrapper(
            String urtug,
            String kdUrtug,
            String kdJabatan,
            String kdJenisUrtug,
            Integer tahunUrtug,
            Integer kuantitas,
            String satuanKuantitas,
            Integer kualitas,
            Integer waktu,
            Integer biaya) {
        this.urtug = urtug;
        this.kdUrtug = kdUrtug;
        this.kdJabatan = kdJabatan;
        this.kdJenisUrtug = kdJenisUrtug;
        this.tahunUrtug = tahunUrtug;
        this.kuantitas = kuantitas;
        this.satuanKuantitas = satuanKuantitas;
        this.kualitas = kualitas;
        this.waktu = waktu;
        this.biaya = biaya;
    }

    public String getUrtug() {
        return urtug;
    }

    public void setUrtug(String urtug) {
        this.urtug = urtug;
    }

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    public Integer getTahunUrtug() {
        return tahunUrtug;
    }

    public void setTahunUrtug(Integer tahunUrtug) {
        this.tahunUrtug = tahunUrtug;
    }

    public Integer getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(Integer kuantitas) {
        this.kuantitas = kuantitas;
    }

    public String getSatuanKuantitas() {
        return satuanKuantitas;
    }

    public void setSatuanKuantitas(String satuanKuantitas) {
        this.satuanKuantitas = satuanKuantitas;
    }

    public Integer getKualitas() {
        return kualitas;
    }

    public void setKualitas(Integer kualitas) {
        this.kualitas = kualitas;
    }

    public Integer getWaktu() {
        return waktu;
    }

    public void setWaktu(Integer waktu) {
        this.waktu = waktu;
    }

    public Integer getBiaya() {
        return biaya;
    }

    public void setBiaya(Integer biaya) {
        this.biaya = biaya;
    }

    public String getKdJenisUrtug() {
        return kdJenisUrtug;
    }

    public void setKdJenisUrtug(String kdJenisUrtug) {
        this.kdJenisUrtug = kdJenisUrtug;
    }
}
