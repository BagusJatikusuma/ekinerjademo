package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bayu on 10/01/18.
 */
@Entity
@Table(name = "target_surat_keterangan")
public class TargetSuratKeterangan {
    @EmbeddedId
    private TargetSuratKeteranganId targetSuratKeteranganId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_surat_keterangan",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_surat_keterangan")
    private SuratKeterangan suratKeterangan;

    @Column(name = "approve_status")
    private Integer approveStatus;
    @Column(name = "status_diterima")
    private Integer statusDiterima;

    public TargetSuratKeteranganId getTargetSuratKeteranganId() {
        return targetSuratKeteranganId;
    }

    public void setTargetSuratKeteranganId(TargetSuratKeteranganId targetSuratKeteranganId) {
        this.targetSuratKeteranganId = targetSuratKeteranganId;
    }

    public SuratKeterangan getSuratKeterangan() {
        return suratKeterangan;
    }

    public void setSuratKeterangan(SuratKeterangan suratKeterangan) {
        this.suratKeterangan = suratKeterangan;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Integer getStatusDiterima() {
        return statusDiterima;
    }

    public void setStatusDiterima(Integer statusDiterima) {
        this.statusDiterima = statusDiterima;
    }
}
