package com.pemda.ekinerjademo.wrapper.input;

import java.util.List;

/**
 * Created by bagus on 11/09/17.
 */
public class UraianTugasJabatanInputWrapper {
    private String kdUrtug;
    private String kdJabatan;
    private String kdJenisUrtug;
    private String satuan;
    private Integer volumeKerja;
    private Integer normaWaktu;
    private Integer bebanKerja;
    private String peralatan;
    private String keterangan;
    private String createdBy;

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

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public Integer getVolumeKerja() {
        return volumeKerja;
    }

    public void setVolumeKerja(Integer volumeKerja) {
        this.volumeKerja = volumeKerja;
    }

    public Integer getNormaWaktu() {
        return normaWaktu;
    }

    public void setNormaWaktu(Integer normaWaktu) {
        this.normaWaktu = normaWaktu;
    }

    public Integer getBebanKerja() {
        return bebanKerja;
    }

    public void setBebanKerja(Integer bebanKerja) {
        this.bebanKerja = bebanKerja;
    }

    public String getPeralatan() {
        return peralatan;
    }

    public void setPeralatan(String peralatan) {
        this.peralatan = peralatan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKdJenisUrtug() {
        return kdJenisUrtug;
    }

    public void setKdJenisUrtug(String kdJenisUrtug) {
        this.kdJenisUrtug = kdJenisUrtug;
    }

}
