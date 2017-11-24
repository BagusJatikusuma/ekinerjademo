package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bagus on 22/11/17.
 */
@Entity
@Table(name = "surat_instruksi_non_pejabat")
public class SuratInstruksiNonPejabat {
    @Id
    @Column(name = "kd_instruksi")
    private String kdInstruksi;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "kd_instruksi", referencedColumnName = "kd_instruksi")
    private SuratInstruksi suratInstruksi;

    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;

    public SuratInstruksi getSuratInstruksi() {
        return suratInstruksi;
    }

    public void setSuratInstruksi(SuratInstruksi suratInstruksi) {
        this.suratInstruksi = suratInstruksi;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public String getKdInstruksi() {
        return kdInstruksi;
    }

    public void setKdInstruksi(String kdInstruksi) {
        this.kdInstruksi = kdInstruksi;
    }
}
