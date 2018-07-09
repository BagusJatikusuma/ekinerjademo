package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 10/10/17.
 */
public class UraianTugasJabatanOutputWrapper {
    private String urtug;
    private String kdUrtug;
    private String kdJabatan;
    private Integer kuantitas;
    private String satuanKuantitas;
    private Integer kualitas;
    private Integer waktu;
    private Integer biaya;

    public UraianTugasJabatanOutputWrapper() {
    }
    public UraianTugasJabatanOutputWrapper(
            String urtug,
            String kdUrtug,
            String kdJabatan,
            Integer kuantitas,
            String satuanKuantitas,
            Integer kualitas,
            Integer waktu,
            Integer biaya) {
        this.urtug = urtug;
        this.kdUrtug = kdUrtug;
        this.kdJabatan = kdJabatan;
        this.kuantitas = kuantitas;
        this.satuanKuantitas = satuanKuantitas;
        this.kualitas = kualitas;
        this.waktu = waktu;
        this.biaya = biaya;
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

    public String getUrtug() {
        return urtug;
    }

    public void setUrtug(String urtug) {
        this.urtug = urtug;
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
}
