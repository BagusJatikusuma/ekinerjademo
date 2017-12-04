package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 28/11/17.
 */
@Entity
@Table(name = "target_surat_perintah_pejabat")
public class TargetSuratPerintahPejabat {
    @EmbeddedId
    private TargetSuratPerintahPejabatId targetSuratPerintahPejabatId;
    @Column(name = "approve_Status")
    private Integer approveStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_surat_perintah",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_surat_perintah")
    private SuratPerintah suratPerintah;

    public TargetSuratPerintahPejabatId getTargetSuratPerintahPejabatId() {
        return targetSuratPerintahPejabatId;
    }

    public void setTargetSuratPerintahPejabatId(TargetSuratPerintahPejabatId targetSuratPerintahPejabatId) {
        this.targetSuratPerintahPejabatId = targetSuratPerintahPejabatId;
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
}
