package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 11/09/17.
 */
public class RincianKinerjaWrapper {
    private String kdUrtug;
    private String namaUrtug;
    private Integer capaianMenit;

    public RincianKinerjaWrapper() {}
    public RincianKinerjaWrapper(String kdUrtug, String namaUrtug, Integer capaianMenit) {
        this.kdUrtug = kdUrtug;
        this.namaUrtug = namaUrtug;
        this.capaianMenit = capaianMenit;
    }

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }

    public String getNamaUrtug() {
        return namaUrtug;
    }

    public void setNamaUrtug(String namaUrtug) {
        this.namaUrtug = namaUrtug;
    }

    public Integer getCapaianMenit() {
        return capaianMenit;
    }

    public void setCapaianMenit(Integer capaianMenit) {
        this.capaianMenit = capaianMenit;
    }
}
