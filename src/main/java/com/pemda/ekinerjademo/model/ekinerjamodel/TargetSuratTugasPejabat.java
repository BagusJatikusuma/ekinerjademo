package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 27/11/17.
 */
@Entity
@Table(name = "target_surat_tugas_pejabat")
public class TargetSuratTugasPejabat {
    @EmbeddedId
    private TargetSuratTugasPejabatId targetSuratTugasPejabatId;
    @Column(name = "approve_status")
    private Integer approveStatus;
    @Column(name = "status_diterima")
    private Integer statusDiterima;
    @ManyToOne
    @JoinColumn(
            name = "kd_surat_tugas",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_surat_tugas")
    private SuratTugas suratTugas;


    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public SuratTugas getSuratTugas() {
        return suratTugas;
    }

    public void setSuratTugas(SuratTugas suratTugas) {
        this.suratTugas = suratTugas;
    }

    public TargetSuratTugasPejabatId getTargetSuratTugasPejabatId() {
        return targetSuratTugasPejabatId;
    }

    public void setTargetSuratTugasPejabatId(TargetSuratTugasPejabatId targetSuratTugasPejabatId) {
        this.targetSuratTugasPejabatId = targetSuratTugasPejabatId;
    }

    public Integer getStatusDiterima() {
        return statusDiterima;
    }

    public void setStatusDiterima(Integer statusDiterima) {
        this.statusDiterima = statusDiterima;
    }
}
