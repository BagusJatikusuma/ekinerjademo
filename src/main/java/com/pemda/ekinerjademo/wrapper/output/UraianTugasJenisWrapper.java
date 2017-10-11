package com.pemda.ekinerjademo.wrapper.output;

import javax.persistence.Column;

/**
 * Created by bagus on 04/10/17.
 */
public class UraianTugasJenisWrapper {
    private UraianTugasWrapper uraianTugas;
    private Integer kuantitas;
    private String satuanKuantitas;
    private Integer kualitas;
    private Integer waktu;
    private Integer biaya;

    public UraianTugasJenisWrapper() {}
    public UraianTugasJenisWrapper(
            UraianTugasWrapper uraianTugas,
            Integer kuantitas,
            String satuanKuantitas,
            Integer kualitas,
            Integer waktu,
            Integer biaya) {
        this.uraianTugas = uraianTugas;
        this.kuantitas = kuantitas;
        this.satuanKuantitas = satuanKuantitas;
        this.kualitas = kualitas;
        this.waktu = waktu;
        this.biaya = biaya;
    }

    public UraianTugasWrapper getUraianTugas() {
        return uraianTugas;
    }

    public void setUraianTugas(UraianTugasWrapper uraianTugas) {
        this.uraianTugas = uraianTugas;
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
