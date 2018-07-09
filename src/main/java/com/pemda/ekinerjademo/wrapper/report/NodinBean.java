package com.pemda.ekinerjademo.wrapper.report;

/**
 * Created by bagus on 17/10/17.
 */
public class NodinBean {
    private String kepada;
    private String dari;
    private String nomor;
    private String tanggal;
    private String sifat;
    private String lampiran;
    private String perihal;
    private String isiSurat;
    private String isiPenutup;

    public NodinBean() {
    }
    public NodinBean(
            String kepada,
            String dari,
            String nomor,
            String tanggal,
            String sifat,
            String lampiran,
            String perihal,
            String isiSurat,
            String isiPenutup) {
        this.kepada = kepada;
        this.dari = dari;
        this.nomor = nomor;
        this.tanggal = tanggal;
        this.sifat = sifat;
        this.lampiran = lampiran;
        this.perihal = perihal;
        this.isiSurat = isiSurat;
        this.isiPenutup = isiPenutup;
    }

    public String getKepada() {
        return kepada;
    }

    public void setKepada(String kepada) {
        this.kepada = kepada;
    }

    public String getDari() {
        return dari;
    }

    public void setDari(String dari) {
        this.dari = dari;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getSifat() {
        return sifat;
    }

    public void setSifat(String sifat) {
        this.sifat = sifat;
    }

    public String getLampiran() {
        return lampiran;
    }

    public void setLampiran(String lampiran) {
        this.lampiran = lampiran;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getIsiSurat() {
        return isiSurat;
    }

    public void setIsiSurat(String isiSurat) {
        this.isiSurat = isiSurat;
    }

    public String getIsiPenutup() {
        return isiPenutup;
    }

    public void setIsiPenutup(String isiPenutup) {
        this.isiPenutup = isiPenutup;
    }
}
