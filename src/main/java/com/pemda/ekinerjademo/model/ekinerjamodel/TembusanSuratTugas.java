package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 27/11/17.
 */
@Entity
@Table(name = "tembusan_surat_tugas")
public class TembusanSuratTugas {
    @EmbeddedId
    private TembusanSuratTugasId tembusanSuratTugasId;
    @Column(name = "status_diterima")
    private Integer statusDiterima;
    @Column(name = "status_baca")
    private Integer statusBaca;

    @ManyToOne
    @JoinColumn(
            name = "kd_surat_tugas",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_surat_tugas")
    private SuratTugas suratTugas;

    public TembusanSuratTugasId getTembusanSuratTugasId() {
        return tembusanSuratTugasId;
    }

    public void setTembusanSuratTugasId(TembusanSuratTugasId tembusanSuratTugasId) {
        this.tembusanSuratTugasId = tembusanSuratTugasId;
    }

    public SuratTugas getSuratTugas() {
        return suratTugas;
    }

    public void setSuratTugas(SuratTugas suratTugas) {
        this.suratTugas = suratTugas;
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
