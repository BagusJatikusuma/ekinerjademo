package com.pemda.ekinerjademo.wrapper.output;

public class TugasTambahanWrapper {
    private String id;
    private Short bulan;
    private Short tahun;
    private String deskripsi;

    public TugasTambahanWrapper() {
    }

    public TugasTambahanWrapper(String id, Short bulan, Short tahun, String deskripsi) {
        this.id = id;
        this.bulan = bulan;
        this.tahun = tahun;
        this.deskripsi = deskripsi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Short getBulan() {
        return bulan;
    }

    public void setBulan(Short bulan) {
        this.bulan = bulan;
    }

    public Short getTahun() {
        return tahun;
    }

    public void setTahun(Short tahun) {
        this.tahun = tahun;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
