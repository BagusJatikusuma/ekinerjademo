package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bayu on 22/12/17.
 */
public class BeritaAcaraHistoryWrapper {
    private String kdBeritaAcara;
    private String createdDate;
    private Integer statusBaca;

    public BeritaAcaraHistoryWrapper() {
    }

    public BeritaAcaraHistoryWrapper(String kdBeritaAcara, String createdDate, Integer statusBaca) {
        this.kdBeritaAcara = kdBeritaAcara;
        this.createdDate = createdDate;
        this.statusBaca = statusBaca;
    }

    public String getKdBeritaAcara() {
        return kdBeritaAcara;
    }

    public void setKdBeritaAcara(String kdBeritaAcara) {
        this.kdBeritaAcara = kdBeritaAcara;
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
