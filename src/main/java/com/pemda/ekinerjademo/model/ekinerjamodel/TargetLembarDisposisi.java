package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 18/11/17.
 */
@Entity
@Table(name = "target_lembar_disposisi")
public class TargetLembarDisposisi {
    @EmbeddedId
    private TargetLembarDisposisiId targetLembarDisposisiId;
    @Column(name = "approve_status")
    private Integer approveStatus;
    @Column(name = "status_baca")
    private Integer statusBaca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_lembar_disposisi",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_lembar_disposisi")
    private LembarDisposisi lembarDisposisi;

    public TargetLembarDisposisiId getTargetLembarDisposisiId() {
        return targetLembarDisposisiId;
    }

    public void setTargetLembarDisposisiId(TargetLembarDisposisiId targetLembarDisposisiId) {
        this.targetLembarDisposisiId = targetLembarDisposisiId;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public LembarDisposisi getLembarDisposisi() {
        return lembarDisposisi;
    }

    public void setLembarDisposisi(LembarDisposisi lembarDisposisi) {
        this.lembarDisposisi = lembarDisposisi;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }
}
