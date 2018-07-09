package com.pemda.ekinerjademo.wrapper.input;

import java.util.Date;

/**
 * Created by bagus on 11/09/17.
 */
public class RincianEKinerjaInputWrapper {
    private String nipPegawai;
    private String kdUrtug;
    private Integer statusEkinerja;
    private Integer capaianMenit;
    private Date tglSubmit;

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }

    public Integer getCapaianMenit() {
        return capaianMenit;
    }

    public void setCapaianMenit(Integer capaianMenit) {
        this.capaianMenit = capaianMenit;
    }

    public Integer getStatusEkinerja() {
        return statusEkinerja;
    }

    public void setStatusEkinerja(Integer statusEkinerja) {
        this.statusEkinerja = statusEkinerja;
    }

    public Date getTglSubmit() {
        return tglSubmit;
    }

    public void setTglSubmit(Date tglSubmit) {
        this.tglSubmit = tglSubmit;
    }
}
