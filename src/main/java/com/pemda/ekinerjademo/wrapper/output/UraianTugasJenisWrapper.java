package com.pemda.ekinerjademo.wrapper.output;

import javax.persistence.Column;

/**
 * Created by bagus on 04/10/17.
 */
public class UraianTugasJenisWrapper {
    private UraianTugasWrapper uraianTugas;
    private JenisUrtugWrapper jenisUraianTugas;
    private String satuan;
    private Integer volumeKerja;
    private Integer normaWaktu;
    private Integer bebanKerja;
    private String peralatan;
    private  String keterangan;

    public UraianTugasJenisWrapper() {}
    public UraianTugasJenisWrapper(
            UraianTugasWrapper uraianTugas,
            JenisUrtugWrapper jenisUraianTugas,
            String satuan,
            Integer volumeKerja,
            Integer normaWaktu,
            Integer bebanKerja,
            String peralatan,
            String keterangan) {
        this.uraianTugas = uraianTugas;
        this.jenisUraianTugas = jenisUraianTugas;
        this.satuan = satuan;
        this.volumeKerja = volumeKerja;
        this.normaWaktu = normaWaktu;
        this.bebanKerja = bebanKerja;
        this.peralatan = peralatan;
        this.keterangan = keterangan;
    }

    public UraianTugasWrapper getUraianTugas() {
        return uraianTugas;
    }

    public void setUraianTugas(UraianTugasWrapper uraianTugas) {
        this.uraianTugas = uraianTugas;
    }

    public JenisUrtugWrapper getJenisUraianTugas() {
        return jenisUraianTugas;
    }

    public void setJenisUraianTugas(JenisUrtugWrapper jenisUraianTugas) {
        this.jenisUraianTugas = jenisUraianTugas;
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
}
