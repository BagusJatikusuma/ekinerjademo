package com.pemda.ekinerjademo.wrapper.input;

/**
 * Created by bagus on 11/09/17.
 */
public class RincianEKinerjaInputWrapper {
    private String nipPegawai;
    private String kdUrtug;
    private Integer statusEkinerja;
    private Integer capaianMenit;

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
}
