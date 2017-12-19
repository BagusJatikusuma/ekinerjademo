package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 14/11/17.
 */
@Entity
@Table(name = "tembusan_surat_perintah")
public class TembusanSuratPerintah {
    @EmbeddedId
    private TembusanSuratPerintahId tembusanSuratPerintahId;

    @Column(name = "status_diterima")
    private Integer statusDiterima;

    @Column(name = "status_baca")
    private Integer statusBaca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_surat_perintah",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_surat_perintah")
    private SuratPerintah suratPerintah;

    public TembusanSuratPerintahId getTembusanSuratPerintahId() {
        return tembusanSuratPerintahId;
    }

    public void setTembusanSuratPerintahId(TembusanSuratPerintahId tembusanSuratPerintahId) {
        this.tembusanSuratPerintahId = tembusanSuratPerintahId;
    }

    public SuratPerintah getSuratPerintah() {
        return suratPerintah;
    }

    public void setSuratPerintah(SuratPerintah suratPerintah) {
        this.suratPerintah = suratPerintah;
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
