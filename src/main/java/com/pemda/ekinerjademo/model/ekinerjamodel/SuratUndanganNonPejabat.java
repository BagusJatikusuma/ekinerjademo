package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bayu on 09/01/18.
 */
@Entity
@Table(name = "surat_undangan_non_pejabat")
public class SuratUndanganNonPejabat {
    @Id
    @Column(name = "kd_surat_undangan")
    private String kdSuratUndangan;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "kd_surat_undangan", referencedColumnName = "kd_surat_undangan")
    private SuratUndangan suratUndangan;

    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;

    public String getKdSuratUndangan() {
        return kdSuratUndangan;
    }

    public void setKdSuratUndangan(String kdSuratUndangan) {
        this.kdSuratUndangan = kdSuratUndangan;
    }

    public SuratUndangan getSuratUndangan() {
        return suratUndangan;
    }

    public void setSuratUndangan(SuratUndangan suratUndangan) {
        this.suratUndangan = suratUndangan;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }
}
