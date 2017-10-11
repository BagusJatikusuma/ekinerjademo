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

    public UraianTugasJabatanJenisUrtugWrapper() {
    }
    public UraianTugasJabatanJenisUrtugWrapper(
            String kdUrtug,
            String urtug,
            String kdJabatan,
            String kdJenisUrtug,
            String jenisUrtug,
            Integer tahunUrtug) {
        this.kdUrtug = kdUrtug;
        this.urtug = urtug;
        this.kdJabatan = kdJabatan;
        this.kdJenisUrtug = kdJenisUrtug;
        this.jenisUrtug = jenisUrtug;
        this.tahunUrtug = tahunUrtug;
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
}
