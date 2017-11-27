package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 27/11/17.
 */
public class LembarDisposisiWrapper {
    private String kdLembarDisposisi;
    private String path;
    private String tanggalPenerimaan;
    private Integer tktKeamanan;
    private String tglPenyelesaian;

    public LembarDisposisiWrapper() {
    }
    public LembarDisposisiWrapper(String kdLembarDisposisi, String path, String tanggalPenerimaan, Integer tktKeamanan, String tglPenyelesaian) {
        this.kdLembarDisposisi = kdLembarDisposisi;
        this.path = path;
        this.tanggalPenerimaan = tanggalPenerimaan;
        this.tktKeamanan = tktKeamanan;
        this.tglPenyelesaian = tglPenyelesaian;
    }

    public String getKdLembarDisposisi() {
        return kdLembarDisposisi;
    }

    public void setKdLembarDisposisi(String kdLembarDisposisi) {
        this.kdLembarDisposisi = kdLembarDisposisi;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTanggalPenerimaan() {
        return tanggalPenerimaan;
    }

    public void setTanggalPenerimaan(String tanggalPenerimaan) {
        this.tanggalPenerimaan = tanggalPenerimaan;
    }

    public Integer getTktKeamanan() {
        return tktKeamanan;
    }

    public void setTktKeamanan(Integer tktKeamanan) {
        this.tktKeamanan = tktKeamanan;
    }

    public String getTglPenyelesaian() {
        return tglPenyelesaian;
    }

    public void setTglPenyelesaian(String tglPenyelesaian) {
        this.tglPenyelesaian = tglPenyelesaian;
    }
}
