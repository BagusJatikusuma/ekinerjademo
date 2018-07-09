package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 22/11/17.
 */
@Entity
@Table(name = "instruksi_pejabat")
public class InstruksiPejabat {
    @EmbeddedId
    private InstruksiPejabatId instruksiPejabatId;
    @Column(name = "approve_status")
    private Integer approveStatus;
    @Column(name = "status_baca")
    private Integer statusBaca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_instruksi",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_instruksi"
    )
    private SuratInstruksi suratInstruksi;

    public InstruksiPejabatId getInstruksiPejabatId() {
        return instruksiPejabatId;
    }

    public void setInstruksiPejabatId(InstruksiPejabatId instruksiPejabatId) {
        this.instruksiPejabatId = instruksiPejabatId;
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

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }
}
