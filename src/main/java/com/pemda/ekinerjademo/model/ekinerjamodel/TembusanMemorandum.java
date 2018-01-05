package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by bagus on 04/01/18.
 */
@Entity
@Table(name = "tembusan_memorandum")
public class TembusanMemorandum {
    private TembusanMemorandumId tembusanMemorandumId;
    @Column(name = "status_diterima")
    private Integer statusDiterima;
    @Column(name = "status_baca")
    private Integer statusBaca;

    public TembusanMemorandumId getTembusanMemorandumId() {
        return tembusanMemorandumId;
    }

    public void setTembusanMemorandumId(TembusanMemorandumId tembusanMemorandumId) {
        this.tembusanMemorandumId = tembusanMemorandumId;
    }

    public Integer getStatusDiterima() {
        return statusDiterima;
    }

    public void setStatusDiterima(Integer statusDiterima) {
        this.statusDiterima = statusDiterima;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }
}
