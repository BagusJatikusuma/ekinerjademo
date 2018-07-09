package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 09/09/17.
 */
public class RincianEKinerjaWrapper {
    private UraianTugasWrapper uraianTugas;
    private String satuan;
    private Integer volume;
    private Integer normaWaktu;
    private Integer bebanKerja;
    private String peralatan;
    private String keterangan;

    public RincianEKinerjaWrapper() {}
    public RincianEKinerjaWrapper(
            UraianTugasWrapper uraianTugas,
            String satuan,
            Integer volume,
            Integer normaWaktu,
            Integer bebanKerja,
            String peralatan,
            String keterangan) {
        this.uraianTugas = uraianTugas;
        this.satuan = satuan;
        this.volume = volume;
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

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
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
