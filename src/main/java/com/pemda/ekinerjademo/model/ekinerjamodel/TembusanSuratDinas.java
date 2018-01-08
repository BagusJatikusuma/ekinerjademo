package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bayu on 07/01/18.
 */
@Entity
@Table(name = "tembusan_surat_dinas")
public class TembusanSuratDinas {
    @EmbeddedId
    private TembusanSuratDinasId tembusanSuratDinasId;

    @Column(name = "status_diterima")
    private Integer statusDiterima;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_surat_dinas",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_surat_dinas")
    private SuratDinas suratDinas;

    public TembusanSuratDinasId getTembusanSuratDinasId() {
        return tembusanSuratDinasId;
    }

    public void setTembusanSuratDinasId(TembusanSuratDinasId tembusanSuratDinasId) {
        this.tembusanSuratDinasId = tembusanSuratDinasId;
    }

    public Integer getStatusDiterima() {
        return statusDiterima;
    }

    public void setStatusDiterima(Integer statusDiterima) {
        this.statusDiterima = statusDiterima;
    }

    public SuratDinas getSuratDinas() {
        return suratDinas;
    }

    public void setSuratDinas(SuratDinas suratDinas) {
        this.suratDinas = suratDinas;
    }
}
