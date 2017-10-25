package com.pemda.ekinerjademo.wrapper.input;

import java.util.List;

/**
 * Created by bagus on 11/09/17.
 */
public class UraianTugasJabatanInputWrapper {
    private String kdUrtug;
    private String kdJabatan;
    private Integer kuantitas;
    private String satuanKuantitas;
    private Integer kualitas;
    private Integer waktu;
    private Integer biaya;
    private String createdBy;
    private String nipPenilai;

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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public String getNipPenilai() {
        return nipPenilai;
    }

    public void setNipPenilai(String nipPenilai) {
        this.nipPenilai = nipPenilai;
    }
}
