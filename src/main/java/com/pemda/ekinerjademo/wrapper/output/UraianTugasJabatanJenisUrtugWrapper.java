package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 10/10/17.
 */
public class UraianTugasJabatanJenisUrtugWrapper {
    private String kdUrtug;
    private String urtug;
    private String kdJabatan;
    private String kdJenisUrtug;
    private String jenisUrtug;
    private Integer tahunUrtug;
    private Integer kuantitas;
    private String satuanKuantitas;
    private Integer kualitas;
    private Integer waktu;
    private Integer biaya;
    private Integer totalRealisasi;

    public UraianTugasJabatanJenisUrtugWrapper() {
    }
    public UraianTugasJabatanJenisUrtugWrapper(
            String kdUrtug,
            String urtug,
            String kdJabatan,
            String kdJenisUrtug,
            String jenisUrtug,
            Integer tahunUrtug,
            Integer kuantitas,
            String satuanKuantitas,
            Integer kualitas,
            Integer waktu,
            Integer biaya) {
        this.kdUrtug = kdUrtug;
        this.urtug = urtug;
        this.kdJabatan = kdJabatan;
        this.kdJenisUrtug = kdJenisUrtug;
        this.jenisUrtug = jenisUrtug;
        this.tahunUrtug = tahunUrtug;
        this.kuantitas = kuantitas;
        this.satuanKuantitas = satuanKuantitas;
        this.kualitas = kualitas;
        this.waktu = waktu;
        this.biaya = biaya;
    }

    public UraianTugasJabatanJenisUrtugWrapper(String kdUrtug, String urtug, String kdJabatan, String kdJenisUrtug, String jenisUrtug, Integer tahunUrtug, Integer kuantitas, String satuanKuantitas, Integer kualitas, Integer waktu, Integer biaya, Integer totalRealisasi) {
        this.kdUrtug = kdUrtug;
        this.urtug = urtug;
        this.kdJabatan = kdJabatan;
        this.kdJenisUrtug = kdJenisUrtug;
        this.jenisUrtug = jenisUrtug;
        this.tahunUrtug = tahunUrtug;
        this.kuantitas = kuantitas;
        this.satuanKuantitas = satuanKuantitas;
        this.kualitas = kualitas;
        this.waktu = waktu;
        this.biaya = biaya;
        this.totalRealisasi = totalRealisasi;
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

    public String getKdJenisUrtug() {
        return kdJenisUrtug;
    }

    public void setKdJenisUrtug(String kdJenisUrtug) {
        this.kdJenisUrtug = kdJenisUrtug;
    }

    public String getJenisUrtug() {
        return jenisUrtug;
    }

    public void setJenisUrtug(String jenisUrtug) {
        this.jenisUrtug = jenisUrtug;
    }

    public Integer getTahunUrtug() {
        return tahunUrtug;
    }

    public void setTahunUrtug(Integer tahunUrtug) {
        this.tahunUrtug = tahunUrtug;
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

    public Integer getTotalRealisasi() {
        return totalRealisasi;
    }

    public void setTotalRealisasi(Integer totalRealisasi) {
        this.totalRealisasi = totalRealisasi;
    }
}
