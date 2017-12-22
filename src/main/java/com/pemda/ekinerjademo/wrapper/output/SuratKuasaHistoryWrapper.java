package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bayu on 22/12/17.
 */
public class SuratKuasaHistoryWrapper {
    private String kdSuratKuasa;
    private String createdDate;
    private Integer statusBaca;

    public SuratKuasaHistoryWrapper() {
    }

    public SuratKuasaHistoryWrapper(String kdSuratKuasa, String createdDate, Integer statusBaca) {
        this.kdSuratKuasa = kdSuratKuasa;
        this.createdDate = createdDate;
        this.statusBaca = statusBaca;
    }

    public String getKdSuratKuasa() {
        return kdSuratKuasa;
    }

    public void setKdSuratKuasa(String kdSuratKuasa) {
        this.kdSuratKuasa = kdSuratKuasa;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }
}
