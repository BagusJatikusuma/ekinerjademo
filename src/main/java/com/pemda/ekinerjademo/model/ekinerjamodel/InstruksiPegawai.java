package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 22/11/17.
 */
@Entity
@Table(name = "instruksi_pegawai")
public class InstruksiPegawai {
    @EmbeddedId
    private InstruksiPegawaiId instruksiPegawaiId;
    @Column(name = "approve_status")
    private Integer approveStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_instruksi",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_instruksi"
    )
    private SuratInstruksi suratInstruksi;

    public InstruksiPegawaiId getInstruksiPegawaiId() {
        return instruksiPegawaiId;
    }

    public void setInstruksiPegawaiId(InstruksiPegawaiId instruksiPegawaiId) {
        this.instruksiPegawaiId = instruksiPegawaiId;
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
