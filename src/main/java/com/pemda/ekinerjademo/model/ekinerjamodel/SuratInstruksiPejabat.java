package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bagus on 22/11/17.
 */
@Entity
@Table(name = "surat_instruksi_pejabat")
public class SuratInstruksiPejabat {
    @Id
    @Column(name = "kd_instruksi")
    private String kdInstruksi;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "kd_instruksi", referencedColumnName = "kd_instruksi")
    private SuratInstruksi suratInstruksi;

    @Column(name = "kd_jabatan")
    private String kdJabatan;

    public SuratInstruksi getSuratInstruksi() {
        return suratInstruksi;
    }

    public void setSuratInstruksi(SuratInstruksi suratInstruksi) {
        this.suratInstruksi = suratInstruksi;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    public String getKdInstruksi() {
        return kdInstruksi;
    }

    public void setKdInstruksi(String kdInstruksi) {
        this.kdInstruksi = kdInstruksi;
    }
}
