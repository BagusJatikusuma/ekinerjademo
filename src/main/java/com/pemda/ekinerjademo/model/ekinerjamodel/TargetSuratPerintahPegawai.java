package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 14/11/17.
 */
@Entity
@Table(name = "target_surat_perintah_pegawai")
public class TargetSuratPerintahPegawai {
    @EmbeddedId
    private TargetSuratPerintahPegawaiId targetSuratPerintahPegawaiId;

    @Column(name = "kd_unit_kerja_target")
    private String kdUnitKerjaTarget;

    @Column(name = "approve_status")
    private Integer approveStatus;

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

    public TargetSuratPerintahPegawaiId getTargetSuratPerintahPegawaiId() {
        return targetSuratPerintahPegawaiId;
    }

    public void setTargetSuratPerintahPegawaiId(TargetSuratPerintahPegawaiId targetSuratPerintahPegawaiId) {
        this.targetSuratPerintahPegawaiId = targetSuratPerintahPegawaiId;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
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

    public String getKdUnitKerjaTarget() {
        return kdUnitKerjaTarget;
    }

    public void setKdUnitKerjaTarget(String kdUnitKerjaTarget) {
        this.kdUnitKerjaTarget = kdUnitKerjaTarget;
    }
}
