package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 14/11/17.
 */
@Entity
@Table(name = "target_surat_perintah_non_pejabat")
public class TargetSuratPerintahNonPejabat {
    @EmbeddedId
    private TargetSuratPerintahNonPejabatId targetSuratPerintahNonPejabatId;

    @Column(name = "approve_status")
    private Integer approveStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_surat_perintah",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_surat_perintah")
    private SuratPerintahNonPejabat suratPerintahNonPejabat;

    public TargetSuratPerintahNonPejabatId getTargetSuratPerintahNonPejabatId() {
        return targetSuratPerintahNonPejabatId;
    }

    public void setTargetSuratPerintahNonPejabatId(TargetSuratPerintahNonPejabatId targetSuratPerintahNonPejabatId) {
        this.targetSuratPerintahNonPejabatId = targetSuratPerintahNonPejabatId;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public SuratPerintahNonPejabat getSuratPerintahNonPejabat() {
        return suratPerintahNonPejabat;
    }

    public void setSuratPerintahNonPejabat(SuratPerintahNonPejabat suratPerintahNonPejabat) {
        this.suratPerintahNonPejabat = suratPerintahNonPejabat;
    }
}
