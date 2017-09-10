package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 09/09/17.
 */
public class UraianTugasWrapper {
    private String kdUrtug;
    private String deskripsi;
    private String satuan;
    private Integer volumeKerja;
    private Integer normaWaktu;
    private Integer bebanKerja;
    private String peralatan;
    private  String keterangan;

    public UraianTugasWrapper() {}
    public UraianTugasWrapper(
            String kdUrtug,
            String deskripsi,
            String satuan,
            Integer volumeKerja,
            Integer normaWaktu,
            Integer bebanKerja,
            String peralatan,
            String keterangan) {
        this.kdUrtug = kdUrtug;
        this.deskripsi = deskripsi;
        this.satuan = satuan;
        this.volumeKerja = volumeKerja;
        this.normaWaktu = normaWaktu;
        this.bebanKerja = bebanKerja;
        this.peralatan = peralatan;
        this.keterangan = keterangan;
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
