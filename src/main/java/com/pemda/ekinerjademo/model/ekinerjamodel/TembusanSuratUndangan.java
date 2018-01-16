package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by bayu on 09/01/18.
 */
@Entity
@Table(name = "tembusan_surat_undangan")
public class TembusanSuratUndangan {
    @EmbeddedId
    private TembusanSuratUndanganId tembusanSuratUndanganId;
    @Column(name = "status_diterima")
    private Integer statusDiterima;
    @Column(name = "status_baca")
    private Integer statusBaca;

    public TembusanSuratUndanganId getTembusanSuratUndanganId() {
        return tembusanSuratUndanganId;
    }

    public void setTembusanSuratUndanganId(TembusanSuratUndanganId tembusanSuratUndanganId) {
        this.tembusanSuratUndanganId = tembusanSuratUndanganId;
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
