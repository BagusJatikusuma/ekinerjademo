package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 27/11/17.
 */
@Entity
@Table(name = "target_surat_tugas_pejabat")
public class TargetSuratTugasPejabat {
    @EmbeddedId
    private TargetSuratTugasPegawaiId targetSuratTugasPegawaiId;
    @Column(name = "approve_status")
    private Integer approveStatus;
    @ManyToOne
    @JoinColumn(
            name = "kd_surat_tugas",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_surat_tugas")
    private SuratInstruksi suratInstruksi;

    public TargetSuratTugasPegawaiId getTargetSuratTugasPegawaiId() {
        return targetSuratTugasPegawaiId;
    }

    public void setTargetSuratTugasPegawaiId(TargetSuratTugasPegawaiId targetSuratTugasPegawaiId) {
        this.targetSuratTugasPegawaiId = targetSuratTugasPegawaiId;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public SuratInstruksi getSuratInstruksi() {
        return suratInstruksi;
    }

    public void setSuratInstruksi(SuratInstruksi suratInstruksi) {
        this.suratInstruksi = suratInstruksi;
    }
}
